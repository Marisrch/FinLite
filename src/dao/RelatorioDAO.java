package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatorioDAO {

    private Connection conexao;

    public RelatorioDAO() throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public ResultSet buscarVendasPeriodo(
            String dataInicial,
            String dataFinal)
            throws Exception {

        String sql
                = "SELECT "
                + "v.id_venda, "
                + "v.data_venda, "
                + "c.nome_cliente, "
                + "v.status_venda, "
                + "SUM(iv.custo_item_venda) AS total "
                + "FROM Venda v "
                + "INNER JOIN Cliente c "
                + "ON v.id_cliente = c.id_cliente "
                + "INNER JOIN Item_Venda iv "
                + "ON v.id_venda = iv.id_venda "
                + "WHERE v.data_venda "
                + "BETWEEN ? AND ? "
                + "GROUP BY "
                + "v.id_venda, "
                + "v.data_venda, "
                + "c.nome_cliente, "
                + "v.status_venda "
                + "ORDER BY v.data_venda";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setString(1, dataInicial);

        ps.setString(2, dataFinal);

        return ps.executeQuery();
    }

}
