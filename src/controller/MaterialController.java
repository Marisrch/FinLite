package controller;

import dao.MaterialDAO;

import model.Material;

public class MaterialController {

    public void salvarMaterial(
            String nome,
            String cor,
            String marca,
            double custo,
            double quantidade,
            String unidade
    ) {

        Material material
                = new Material();

        material.setNomeMaterial(nome);

        material.setCorMaterial(cor);

        material.setMarcaMaterial(marca);

        material.setCustoUnitarioMaterial(custo);

        material.setQuantidadeEstoqueMaterial(
                quantidade
        );

        material.setUnidadeMedidaMaterial(
                unidade
        );

        MaterialDAO dao
                = new MaterialDAO();

        dao.cadastrarMaterial(material);
    }

    public void atualizarMaterial(Material material) {

        MaterialDAO dao = new MaterialDAO();

        dao.atualizarMaterial(material);
    }

    public void excluirMaterial(int id) {

        MaterialDAO dao = new MaterialDAO();

        dao.excluirMaterial(id);
    }
}
