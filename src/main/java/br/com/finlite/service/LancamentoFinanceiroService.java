package br.com.finlite.service;

import br.com.finlite.model.LancamentoFinanceiro;
import java.util.List;
import java.util.Optional;

public interface LancamentoFinanceiroService {

    LancamentoFinanceiro salvar(LancamentoFinanceiro lancamento);

    List<LancamentoFinanceiro> listarTodos();

    Optional<LancamentoFinanceiro> buscarPorId(Integer id);

    void excluir(Integer id);
}