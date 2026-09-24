package controller;

import dao.EmpreendedorDAO;

import model.Empreendedor;

public class EmpreendedorController {

    public void salvarEmpreendedor(
            String nome,
            String cpf,
            String email,
            String telefone
    ) {

        try {

            Empreendedor empreendedor
                    = new Empreendedor();

            empreendedor.setNomeEmpreendedor(nome);

            empreendedor.setCpfEmpreendedor(cpf);

            empreendedor.setEmailEmpreendedor(email);

            empreendedor.setTelefoneEmpreendedor(telefone);

            EmpreendedorDAO dao
                    = new EmpreendedorDAO();

            dao.cadastrarEmpreendedor(
                    empreendedor
            );

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar empreendedor: "
                    + e.getMessage()
            );
        }
    }
}
