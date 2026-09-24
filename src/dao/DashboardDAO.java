package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    private Connection conexao;

    public DashboardDAO() throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public double calcularTotalVendido()
            throws Exception {

        String sql
                = "SELECT SUM(preco_unitario_item_venda * quantidade_item_venda) "
                + "AS total "
                + "FROM Item_Venda";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ResultSet rs
                = ps.executeQuery();

        double total = 0;

        if (rs.next()) {

            total
                    = rs.getDouble("total");
        }

        rs.close();
        ps.close();

        return total;
    }

    public String produtoMaisVendido()
            throws Exception {

        String sql
                = "SELECT p.nome_produto, "
                + "SUM(iv.quantidade_item_venda) "
                + "AS total "
                + "FROM Item_Venda iv "
                + "INNER JOIN Produto p "
                + "ON iv.id_produto = p.id_produto "
                + "GROUP BY p.nome_produto "
                + "ORDER BY total DESC "
                + "LIMIT 1";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ResultSet rs
                = ps.executeQuery();

        String produto = "Nenhuma venda";

        if (rs.next()) {

            produto
                    = rs.getString(
                            "nome_produto"
                    );
        }

        rs.close();
        ps.close();

        return produto;
    }

    public double calcularGastos()
            throws Exception {

        return 0;

    }

    public double calcularFaturamento()
            throws Exception {

        double vendas = calcularTotalVendido();

        double gastos = calcularGastos();

        return vendas - gastos;
    }

}
