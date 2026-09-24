package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.ItemVenda;

public class ItemVendaDAO {

    private Connection conexao;

    public ItemVendaDAO() throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public void cadastrarItemVenda(
            ItemVenda item)
            throws Exception {

        String sql
                = "INSERT INTO Item_Venda "
                + "(id_venda, "
                + "id_produto, "
                + "quantidade_item_venda, "
                + "preco_unitario_item_venda, "
                + "custo_item_venda) "
                + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(
                1,
                item.getIdVenda()
        );

        ps.setInt(
                2,
                item.getIdProduto()
        );

        ps.setInt(
                3,
                item.getQuantidadeItemVenda()
        );

        ps.setDouble(
                4,
                item.getPrecoUnitarioItemVenda()
        );

        ps.setDouble(
                5,
                item.getCustoItemVenda()
        );

        ps.execute();

        ps.close();
    }
}
