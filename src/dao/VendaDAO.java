package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import model.Venda;

public class VendaDAO {

    private Connection conexao;

    public VendaDAO() throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public int cadastrarVenda(Venda venda)
            throws Exception {

        String sql
                = "INSERT INTO Venda "
                + "(id_cliente, "
                + "id_empreendedor, "
                + "data_venda, "
                + "status_venda) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement ps
                = conexao.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );

        ps.setInt(
                1,
                venda.getIdCliente()
        );

        ps.setInt(
                2,
                venda.getIdEmpreendedor()
        );

        ps.setString(
                3,
                venda.getDataVenda()
        );

        ps.setString(
                4,
                venda.getStatusVenda()
        );

        ps.execute();

        ResultSet rs
                = ps.getGeneratedKeys();

        int idGerado = 0;

        if (rs.next()) {

            idGerado = rs.getInt(1);
        }

        ps.close();

        return idGerado;
    }

    public ResultSet listarRelatorioVendas()
            throws Exception {

        String sql
                = "SELECT v.id_venda, "
                + "v.data_venda, "
                + "c.nome_cliente, "
                + "v.status_venda, "
                + "SUM(iv.custo_item_venda) AS total "
                + "FROM Venda v "
                + "INNER JOIN Cliente c "
                + "ON v.id_cliente = c.id_cliente "
                + "INNER JOIN Item_Venda iv "
                + "ON v.id_venda = iv.id_venda "
                + "GROUP BY v.id_venda, v.data_venda, c.nome_cliente, v.status_venda "
                + "ORDER BY v.data_venda DESC";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        return ps.executeQuery();
    }
}
