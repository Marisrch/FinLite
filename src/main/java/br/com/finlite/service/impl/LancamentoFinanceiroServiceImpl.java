
package br.com.finlite.service.impl;

import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.MovimentacaoEstoque;
import br.com.finlite.repository.LancamentoFinanceiroRepository;
import br.com.finlite.service.LancamentoFinanceiroService;
import br.com.finlite.service.MovimentacaoEstoqueService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoFinanceiroServiceImpl
        implements LancamentoFinanceiroService {

    private final LancamentoFinanceiroRepository lancamentoRepository;
    private final MovimentacaoEstoqueService movimentacaoService;

    public LancamentoFinanceiroServiceImpl(
            LancamentoFinanceiroRepository lancamentoRepository,
            MovimentacaoEstoqueService movimentacaoService) {
        this.lancamentoRepository = lancamentoRepository;
        this.movimentacaoService = movimentacaoService;
    }

    @Override
    @Transactional
    public LancamentoFinanceiro salvar(LancamentoFinanceiro lancamento) {

        if (lancamento.getTipoLancamento() == null ||
                lancamento.getTipoLancamento().isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo do lançamento é obrigatório."
            );
        }

        if (lancamento.getDataLancamento() == null) {
            throw new IllegalArgumentException(
                    "A data do lançamento é obrigatória."
            );
        }

        if (lancamento.getDescricaoLancamento() == null ||
                lancamento.getDescricaoLancamento().isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do lançamento é obrigatória."
            );
        }

        if (lancamento.getCategoriaLancamento() == null ||
                lancamento.getCategoriaLancamento().isBlank()) {
            throw new IllegalArgumentException(
                    "A categoria do lançamento é obrigatória."
            );
        }

        if (lancamento.getValorTotalLancamento() == null ||
                lancamento.getValorTotalLancamento()
                        .compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor total deve ser maior que zero."
            );
        }

        if (lancamento.getQuantidadeLancamento() != null &&
                lancamento.getQuantidadeLancamento()
                        .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "A quantidade não pode ser negativa."
            );
        }

        if (lancamento.getCustoUnitarioLancamento() != null &&
                lancamento.getCustoUnitarioLancamento()
                        .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O custo unitário não pode ser negativo."
            );
        }

        String tipo = lancamento.getTipoLancamento()
                .trim().toUpperCase();

        if (!tipo.equals("DESPESA") && !tipo.equals("RECEITA")) {
            throw new IllegalArgumentException(
                    "O tipo deve ser DESPESA ou RECEITA."
            );
        }

        lancamento.setTipoLancamento(tipo);

        // Recupera a movimentação anterior em caso de edição.
        if (lancamento.getIdLancamento() != null) {

            LancamentoFinanceiro existente =
                    lancamentoRepository.findById(
                            lancamento.getIdLancamento()
                    ).orElseThrow(() -> new EntityNotFoundException(
                            "Lançamento financeiro não encontrado."
                    ));

            if (lancamento.getMovimentacao() == null) {
                lancamento.setMovimentacao(
                        existente.getMovimentacao()
                );
            }
        }

        boolean compraMaterial =
                tipo.equals("DESPESA") &&
                        lancamento.getMaterial() != null;

        if (tipo.equals("RECEITA") &&
                lancamento.getMaterial() != null) {
            throw new IllegalArgumentException(
                    "Receitas não podem estar vinculadas a materiais."
            );
        }

        if (compraMaterial &&
                (lancamento.getQuantidadeLancamento() == null ||
                        lancamento.getQuantidadeLancamento()
                                .compareTo(BigDecimal.ZERO) <= 0)) {
            throw new IllegalArgumentException(
                    "Informe uma quantidade maior que zero "
                            + "para a compra de material."
            );
        }

        // Primeiro salva o lançamento financeiro.
        LancamentoFinanceiro salvo =
                lancamentoRepository.save(lancamento);

        if (compraMaterial) {

            // Reutiliza a movimentação caso seja uma edição.
            MovimentacaoEstoque movimentacao =
                    salvo.getMovimentacao();

            if (movimentacao == null) {
                movimentacao = new MovimentacaoEstoque();
            }

            movimentacao.setMaterial(salvo.getMaterial());
            movimentacao.setTipoMovimentacao("ENTRADA");
            movimentacao.setQuantidadeMovimentacao(
                    salvo.getQuantidadeLancamento()
            );
            movimentacao.setMotivoOrigem(
                    ("Compra: " + salvo.getDescricaoLancamento())
                            .substring(
                                    0,
                                    Math.min(
                                            100,
                                            ("Compra: " +
                                                    salvo.getDescricaoLancamento()).length()
                                    )
                            )
            );
            movimentacao.setLancamento(salvo);

            MovimentacaoEstoque movimentacaoSalva =
                    movimentacaoService.salvar(movimentacao);

            salvo.setMovimentacao(movimentacaoSalva);

        } else if (salvo.getMovimentacao() != null) {

            // Se deixar de ser uma compra de material,
            // desfaz a movimentação anterior.
            movimentacaoService.excluir(
                    salvo.getMovimentacao().getIdMovimentacao()
            );

            salvo.setMovimentacao(null);
        }

        return salvo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LancamentoFinanceiro> listarTodos() {
        return lancamentoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LancamentoFinanceiro> buscarPorId(Integer id) {
        return lancamentoRepository.findById(id);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        LancamentoFinanceiro lancamento =
                lancamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Lançamento financeiro não encontrado."
                        ));

        // Desfaz a movimentação antes de excluir o lançamento.
        if (lancamento.getMovimentacao() != null) {
            movimentacaoService.excluir(
                    lancamento.getMovimentacao().getIdMovimentacao()
            );
        }

        lancamentoRepository.delete(lancamento);
    }
}
