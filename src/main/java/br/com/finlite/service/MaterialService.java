package br.com.finlite.service;

import br.com.finlite.model.Material;
import java.util.List;
import java.util.Optional;

public interface MaterialService {

    Material salvar(Material material);

    List<Material> listarTodos();

    Optional<Material> buscarPorId(Integer id);

    void excluir(Integer id);
}