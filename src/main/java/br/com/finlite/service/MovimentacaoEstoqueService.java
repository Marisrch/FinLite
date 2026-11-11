package br.com.finlite.service;

import br.com.finlite.model.MovimentacaoEstoque;
import java.util.List;
import java.util.Optional;

public interface MovimentacaoEstoqueService {

    MovimentacaoEstoque salvar(MovimentacaoEstoque movimentacao);

    List<MovimentacaoEstoque> listarTodos();

    Optional<MovimentacaoEstoque> buscarPorId(Integer id);

    void excluir(Integer id);
}