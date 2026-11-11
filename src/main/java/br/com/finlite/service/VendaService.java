package br.com.finlite.service;

import br.com.finlite.model.Venda;
import java.util.List;
import java.util.Optional;

public interface VendaService {

    Venda salvar(Venda venda);

    List<Venda> listarTodos();

    Optional<Venda> buscarPorId(Integer id);

    void excluir(Integer id);
}