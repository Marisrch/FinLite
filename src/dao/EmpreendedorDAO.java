package dao;

import connection.ConnectionFactory;

import model.Empreendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class EmpreendedorDAO {

    private Connection conexao;

    public EmpreendedorDAO() throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public void cadastrarEmpreendedor(
            Empreendedor empreendedor)
            throws Exception {

        String sql
                = "INSERT INTO Empreendedor "
                + "(nome_empreendedor, "
                + "cpf_empreendedor, "
                + "email_empreendedor, "
                + "telefone_empreendedor) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setString(
                1,
                empreendedor.getNomeEmpreendedor()
        );

        ps.setString(
                2,
                empreendedor.getCpfEmpreendedor()
        );

        ps.setString(
                3,
                empreendedor.getEmailEmpreendedor()
        );

        ps.setString(
                4,
                empreendedor.getTelefoneEmpreendedor()
        );

        ps.execute();

        ps.close();
    }

    public ArrayList<Empreendedor>
            listarEmpreendedores()
            throws Exception {

        ArrayList<Empreendedor> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM Empreendedor";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Empreendedor e
                    = new Empreendedor();

            e.setIdEmpreendedor(
                    rs.getInt("id_empreendedor")
            );

            e.setNomeEmpreendedor(
                    rs.getString("nome_empreendedor")
            );

            e.setCpfEmpreendedor(
                    rs.getString("cpf_empreendedor")
            );

            e.setEmailEmpreendedor(
                    rs.getString("email_empreendedor")
            );

            e.setTelefoneEmpreendedor(
                    rs.getString("telefone_empreendedor")
            );

            lista.add(e);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void excluirEmpreendedor(
            int id)
            throws Exception {

        String sql
                = "DELETE FROM Empreendedor "
                + "WHERE id_empreendedor = ?";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(1, id);

        ps.execute();

        ps.close();
    }

    public void editarEmpreendedor(
            Empreendedor empreendedor)
            throws Exception {

        String sql
                = "UPDATE Empreendedor SET "
                + "nome_empreendedor = ?, "
                + "cpf_empreendedor = ?, "
                + "email_empreendedor = ?, "
                + "telefone_empreendedor = ? "
                + "WHERE id_empreendedor = ?";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setString(
                1,
                empreendedor.getNomeEmpreendedor()
        );

        ps.setString(
                2,
                empreendedor.getCpfEmpreendedor()
        );

        ps.setString(
                3,
                empreendedor.getEmailEmpreendedor()
        );

        ps.setString(
                4,
                empreendedor.getTelefoneEmpreendedor()
        );

        ps.setInt(
                5,
                empreendedor.getIdEmpreendedor()
        );

        ps.execute();

        ps.close();
    }
}
