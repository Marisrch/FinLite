package br.com.finlite.service.impl;

import br.com.finlite.model.Material;
import br.com.finlite.repository.MaterialRepository;
import br.com.finlite.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    @Transactional
    public Material salvar(Material material) {

        if (material.getNomeMaterial() == null ||
                material.getNomeMaterial().isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do material é obrigatório."
            );
        }

        if (material.getCategoriaMaterial() == null ||
                material.getCategoriaMaterial().isBlank()) {
            throw new IllegalArgumentException(
                    "A categoria do material é obrigatória."
            );
        }

        if (material.getUnidadeMedidaMaterial() == null ||
                material.getUnidadeMedidaMaterial().isBlank()) {
            throw new IllegalArgumentException(
                    "A unidade de medida é obrigatória."
            );
        }

        if (material.getQuantidadeMinimaMaterial() == null) {
            material.setQuantidadeMinimaMaterial(
                    new BigDecimal("5.00")
            );
        }

        if (material.getQuantidadeMinimaMaterial()
                .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "A quantidade mínima não pode ser negativa."
            );
        }

        if (material.getCustoUnitarioMaterial() != null &&
                material.getCustoUnitarioMaterial()
                        .compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "O custo unitário não pode ser negativo."
            );
        }

        // Novo material começa com estoque zerado.
        if (material.getIdMaterial() == null) {
            material.setQuantidadeEstoqueMaterial(BigDecimal.ZERO);
        } else {
            // Na edição, preserva o estoque atual.
            Material existente = materialRepository.findById(
                    material.getIdMaterial()
            ).orElseThrow(() -> new EntityNotFoundException(
                    "Material não encontrado."
            ));

            material.setQuantidadeEstoqueMaterial(
                    existente.getQuantidadeEstoqueMaterial()
            );
        }

        return materialRepository.save(material);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Material> buscarPorId(Integer id) {
        return materialRepository.findById(id);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        materialRepository.deleteById(id);
    }
}