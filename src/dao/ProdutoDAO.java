package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Produto;
import java.sql.ResultSet;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {

        this.connection
                = ConnectionFactory.getConnection();
    }

    public void cadastrarProduto(Produto produto) {

        String sql
                = "INSERT INTO Produto "
                + "(nome_produto, descricao_produto, "
                + "preco_base_produto, prazo_producao_produto, "
                + "id_empreendedor) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,produto.getNomeProduto());

            stmt.setString(2,produto.getDescricaoProduto());

            stmt.setDouble(3,produto.getPrecoBaseProduto());

            stmt.setString(4,produto.getPrazoProducaoProduto());

            stmt.setInt(5,produto.getIdEmpreendedor());

            stmt.execute();

            stmt.close();

            JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Erro ao cadastrar produto:\n"+ e.getMessage());
        }
    }

    public ArrayList<Produto> listarProdutos() {

    ArrayList<Produto> lista =
            new ArrayList<>();

    String sql =
            "SELECT * FROM Produto";

    try (

        Connection conexao =
                ConnectionFactory.getConnection();

        PreparedStatement stmt =
                conexao.prepareStatement(sql);

        ResultSet rs =
                stmt.executeQuery();

    ) {

        while (rs.next()) {

            Produto produto = new Produto();

            produto.setIdProduto(rs.getInt("id_produto"));

            produto.setNomeProduto(rs.getString("nome_produto"));

            produto.setDescricaoProduto(rs.getString("descricao_produto"));

            produto.setPrecoBaseProduto(rs.getDouble("preco_base_produto"));

            produto.setPrazoProducaoProduto(rs.getString ("prazo_producao_produto")); //Verificar necessidade de manter como String

            lista.add(produto);
        }

    } catch (Exception e) {

        System.out.println(
                "Erro ao listar produtos: "
                + e.getMessage()
        );
    }

    return lista;
}
    
    public void atualizarProduto(Produto produto) {

    String sql =
            "UPDATE Produto SET "
            + "nome_produto = ?, "
            + "descricao_produto = ?, "
            + "preco_base_produto = ?, "
            + "prazo_producao_produto = ? "
            + "WHERE id_produto = ?";

    try (

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement stmt = conexao.prepareStatement(sql);

    ) {

        stmt.setString(1, produto.getNomeProduto()
        );

        stmt.setString(2, produto.getDescricaoProduto()
        );

        stmt.setDouble(3, produto.getPrecoBaseProduto()
        );

        stmt.setString(4, produto.getPrazoProducaoProduto()
        );

        stmt.setInt(5, produto.getIdProduto()
        );

        stmt.executeUpdate();

    } catch (Exception e) {

        System.out.println(
                "Erro ao atualizar produto: "
                + e.getMessage()
        );
    }
}
    
    public void excluirProduto(int idProduto) {

    String sql = "DELETE FROM Produto " + "WHERE id_produto = ?";

    try (

        Connection conexao = ConnectionFactory.getConnection();

        PreparedStatement stmt = conexao.prepareStatement(sql);

    ) {

        stmt.setInt(1, idProduto);

        stmt.executeUpdate();

    } catch (Exception e) {

        System.out.println("Erro ao excluir produto: " + e.getMessage()
        );
    }
}

}
