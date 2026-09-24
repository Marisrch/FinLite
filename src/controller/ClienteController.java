package controller;

import dao.ClienteDAO;

import model.Cliente;

public class ClienteController {

    public void salvarCliente(
            String nome,
            String telefone,
            String cpf
    ) {

        Cliente cliente =
                new Cliente();

        cliente.setNomeCliente(nome);

        cliente.setTelefoneCliente(telefone);

        cliente.setCpfCliente(cpf);

        ClienteDAO dao =
                new ClienteDAO();

        dao.cadastrarCliente(cliente);
    }
}