package br.com.finlite.service;

import br.com.finlite.model.Venda;

import java.util.List;
import java.util.Optional;

public interface VendaService {

    List<Venda> listarTodas();

    Optional<Venda> buscarPorId(Integer id);

    Venda salvar(Venda venda);

    void excluir(Integer id);
}