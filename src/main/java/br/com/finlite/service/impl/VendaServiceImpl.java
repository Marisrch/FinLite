package br.com.finlite.service.impl;

import br.com.finlite.model.Venda;
import br.com.finlite.repository.VendaRepository;
import br.com.finlite.service.VendaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    @Transactional
    public Venda salvar(Venda venda) {

        if (venda.getNomeCliente() == null ||
                venda.getNomeCliente().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do cliente é obrigatório."
            );
        }

        if (venda.getProdutoPeca() == null ||
                venda.getProdutoPeca().isBlank()) {
            throw new IllegalArgumentException(
                    "O produto ou peça é obrigatório."
            );
        }

        if (venda.getValorVenda() == null ||
                venda.getValorVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor da venda deve ser maior que zero."
            );
        }

        if (venda.getFormaPagamento() == null ||
                venda.getFormaPagamento().isBlank()) {
            throw new IllegalArgumentException(
                    "A forma de pagamento é obrigatória."
            );
        }

        if (venda.getStatusVenda() == null ||
                venda.getStatusVenda().isBlank()) {
            throw new IllegalArgumentException(
                    "O status da venda é obrigatório."
            );
        }

        if (venda.getDataEntrega() == null) {
            throw new IllegalArgumentException(
                    "A data de entrega é obrigatória."
            );
        }

        if (venda.getDataRegistro() == null) {
            throw new IllegalArgumentException(
                    "A data de registro é obrigatória."
            );
        }

        if (venda.getEmpreendedor() == null ||
                venda.getEmpreendedor().getIdEmpreendedor() == null) {
            throw new IllegalArgumentException(
                    "É necessário informar o empreendedor."
            );
        }

        return vendaRepository.save(venda);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venda> buscarPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {

        if (!vendaRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    "Venda não encontrada."
            );
        }

        vendaRepository.deleteById(id);
    }
}