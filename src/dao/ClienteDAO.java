package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {

        this.connection =
                ConnectionFactory.getConnection();
    }

    // CADASTRAR

    public void cadastrarCliente(Cliente cliente) {

        String sql =
                "INSERT INTO Cliente "
                + "(nome_cliente, telefone_cliente, cpf_cliente) "
                + "VALUES (?, ?, ?)";

        try {

            PreparedStatement stmt =
                    connection.prepareStatement(sql);

            stmt.setString(1,
                    cliente.getNomeCliente());

            stmt.setString(2,
                    cliente.getTelefoneCliente());

            stmt.setString(3,
                    cliente.getCpfCliente());

            stmt.execute();

            stmt.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    // LISTAR

    public ArrayList<Cliente> listarClientes() {

        ArrayList<Cliente> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Cliente";

        try {

            PreparedStatement stmt =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente =
                        new Cliente();

                cliente.setIdCliente(
                        rs.getInt("id_cliente")
                );

                cliente.setNomeCliente(
                        rs.getString("nome_cliente")
                );

                cliente.setTelefoneCliente(
                        rs.getString("telefone_cliente")
                );

                cliente.setCpfCliente(
                        rs.getString("cpf_cliente")
                );

                lista.add(cliente);
            }

            rs.close();

            stmt.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return lista;
    }

    // ATUALIZAR

    public void atualizarCliente(Cliente cliente) {

        String sql =
                "UPDATE Cliente SET "
                + "nome_cliente = ?, "
                + "telefone_cliente = ?, "
                + "cpf_cliente = ? "
                + "WHERE id_cliente = ?";

        try {

            PreparedStatement stmt =
                    connection.prepareStatement(sql);

            stmt.setString(1,
                    cliente.getNomeCliente());

            stmt.setString(2,
                    cliente.getTelefoneCliente());

            stmt.setString(3,
                    cliente.getCpfCliente());

            stmt.setInt(4,
                    cliente.getIdCliente());

            stmt.execute();

            stmt.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    // EXCLUIR

    public void excluirCliente(int id) {

        String sql =
                "DELETE FROM Cliente "
                + "WHERE id_cliente = ?";

        try {

            PreparedStatement stmt =
                    connection.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.execute();

            stmt.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}