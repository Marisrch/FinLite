
package br.com.finlite.service.impl;

import br.com.finlite.model.Material;
import br.com.finlite.model.MovimentacaoEstoque;
import br.com.finlite.repository.MaterialRepository;
import br.com.finlite.repository.MovimentacaoEstoqueRepository;
import br.com.finlite.service.MovimentacaoEstoqueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoEstoqueServiceImpl
        implements MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoRepository;
    private final MaterialRepository materialRepository;

    public MovimentacaoEstoqueServiceImpl(
            MovimentacaoEstoqueRepository movimentacaoRepository,
            MaterialRepository materialRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional
    public MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacao) {

        if (movimentacao.getMaterial() == null ||
                movimentacao.getMaterial().getIdMaterial() == null) {
            throw new IllegalArgumentException(
                    "O material da movimentação é obrigatório."
            );
        }

        if (movimentacao.getQuantidadeMovimentacao() == null ||
                movimentacao.getQuantidadeMovimentacao()
                        .compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero."
            );
        }

        if (movimentacao.getTipoMovimentacao() == null) {
            throw new IllegalArgumentException(
                    "O tipo da movimentação é obrigatório."
            );
        }

        String tipo = movimentacao.getTipoMovimentacao()
                .trim().toUpperCase();

        if (!tipo.equals("ENTRADA") && !tipo.equals("SAIDA")) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ENTRADA ou SAIDA."
            );
        }

        Material material = materialRepository.findById(
                movimentacao.getMaterial().getIdMaterial()
        ).orElseThrow(() -> new EntityNotFoundException(
                "Material não encontrado."
        ));

        // Se estiver editando, desfaz o efeito anterior.
        if (movimentacao.getIdMovimentacao() != null) {

            MovimentacaoEstoque anterior =
                    movimentacaoRepository.findById(
                            movimentacao.getIdMovimentacao()
                    ).orElseThrow(() -> new EntityNotFoundException(
                            "Movimentação não encontrada."
                    ));

            Material materialAnterior = anterior.getMaterial();

            atualizarEstoque(
                    materialAnterior,
                    anterior.getTipoMovimentacao(),
                    anterior.getQuantidadeMovimentacao(),
                    true
            );

            materialRepository.save(materialAnterior);
        }

        BigDecimal estoqueAtual =
                material.getQuantidadeEstoqueMaterial();

        if (estoqueAtual == null) {
            estoqueAtual = BigDecimal.ZERO;
        }

        BigDecimal novoEstoque;

        if (tipo.equals("ENTRADA")) {
            novoEstoque = estoqueAtual.add(
                    movimentacao.getQuantidadeMovimentacao()
            );
        } else {
            novoEstoque = estoqueAtual.subtract(
                    movimentacao.getQuantidadeMovimentacao()
            );
        }

        if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Estoque insuficiente para realizar esta saída."
            );
        }

        material.setQuantidadeEstoqueMaterial(novoEstoque);
        materialRepository.save(material);

        movimentacao.setMaterial(material);
        movimentacao.setTipoMovimentacao(tipo);

        if (movimentacao.getDataMovimentacao() == null) {
            movimentacao.setDataMovimentacao(LocalDateTime.now());
        }

        return movimentacaoRepository.save(movimentacao);
    }

    private void atualizarEstoque(
            Material material,
            String tipo,
            BigDecimal quantidade,
            boolean desfazer) {

        BigDecimal estoque = material.getQuantidadeEstoqueMaterial();

        if (estoque == null) {
            estoque = BigDecimal.ZERO;
        }

        boolean entrada = tipo.equalsIgnoreCase("ENTRADA");

        if (entrada == desfazer) {
            estoque = estoque.subtract(quantidade);
        } else {
            estoque = estoque.add(quantidade);
        }

        if (estoque.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException(
                    "Não é possível desfazer a movimentação: "
                            + "o estoque ficaria negativo."
            );
        }

        material.setQuantidadeEstoqueMaterial(estoque);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimentacaoEstoque> listarTodos() {
        return movimentacaoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovimentacaoEstoque> buscarPorId(Integer id) {
        return movimentacaoRepository.findById(id);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        MovimentacaoEstoque movimentacao =
                movimentacaoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Movimentação não encontrada."
                        ));

        atualizarEstoque(
                movimentacao.getMaterial(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getQuantidadeMovimentacao(),
                true
        );

        movimentacaoRepository.delete(movimentacao);
    }
}
