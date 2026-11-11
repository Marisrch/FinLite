package br.com.finlite.service;

import br.com.finlite.model.Empreendedor;
import java.util.List;
import java.util.Optional;

public interface EmpreendedorService {

    Empreendedor salvar(Empreendedor empreendedor);

    List<Empreendedor> listarTodos();

    Optional<Empreendedor> buscarPorId(Integer id);

    void excluir(Integer id);
}