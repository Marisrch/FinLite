package dao;

import connection.ConnectionFactory;

import model.ProdutoMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class ProdutoMaterialDAO {

    private Connection conexao;

    public ProdutoMaterialDAO()
            throws Exception {

        this.conexao
                = ConnectionFactory.getConnection();
    }

    public void cadastrar(
            ProdutoMaterial pm)
            throws Exception {

        String sql
                = "INSERT INTO Produto_Material "
                + "(id_produto, "
                + "id_material, "
                + "quantidade_material) "
                + "VALUES (?, ?, ?)";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(
                1,
                pm.getIdProduto()
        );

        ps.setInt(
                2,
                pm.getIdMaterial()
        );

        ps.setDouble(
                3,
                pm.getQuantidadeMaterial()
        );

        ps.execute();

        ps.close();
    }

    public ArrayList<ProdutoMaterial>
            listar()
            throws Exception {

        ArrayList<ProdutoMaterial> lista
                = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM Produto_Material";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ResultSet rs
                = ps.executeQuery();

        while (rs.next()) {

            ProdutoMaterial pm
                    = new ProdutoMaterial();

            pm.setIdProdutoMaterial(
                    rs.getInt(
                            "id_produto_material"
                    )
            );

            pm.setIdProduto(
                    rs.getInt(
                            "id_produto"
                    )
            );

            pm.setIdMaterial(
                    rs.getInt(
                            "id_material"
                    )
            );

            pm.setQuantidadeMaterial(
                    rs.getDouble(
                            "quantidade_material"
                    )
            );

            lista.add(pm);
        }

        rs.close();
        ps.close();

        return lista;
    }

    public void excluir(
            int idProdutoMaterial)
            throws Exception {

        String sql
                = "DELETE FROM Produto_Material "
                + "WHERE id_produto_material = ?";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(
                1,
                idProdutoMaterial
        );

        ps.execute();

        ps.close();
    }

    public void atualizar(
            ProdutoMaterial pm)
            throws Exception {

        String sql
                = "UPDATE Produto_Material "
                + "SET id_produto=?, "
                + "id_material=?, "
                + "quantidade_material=? "
                + "WHERE id_produto_material=?";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(
                1,
                pm.getIdProduto()
        );

        ps.setInt(
                2,
                pm.getIdMaterial()
        );

        ps.setDouble(
                3,
                pm.getQuantidadeMaterial()
        );

        ps.setInt(
                4,
                pm.getIdProdutoMaterial()
        );

        ps.executeUpdate();

        ps.close();
    }

    public ArrayList<ProdutoMaterial> listarPorProduto(
            int idProduto)
            throws Exception {

        ArrayList<ProdutoMaterial> lista
                = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM Produto_Material "
                + "WHERE id_produto = ?";

        PreparedStatement ps
                = conexao.prepareStatement(sql);

        ps.setInt(
                1,
                idProduto
        );

        ResultSet rs
                = ps.executeQuery();

        while (rs.next()) {

            ProdutoMaterial pm
                    = new ProdutoMaterial();

            pm.setIdProdutoMaterial(
                    rs.getInt(
                            "id_produto_material"
                    )
            );

            pm.setIdProduto(
                    rs.getInt(
                            "id_produto"
                    )
            );

            pm.setIdMaterial(
                    rs.getInt(
                            "id_material"
                    )
            );

            pm.setQuantidadeMaterial(
                    rs.getDouble(
                            "quantidade_material"
                    )
            );

            lista.add(pm);
        }

        rs.close();
        ps.close();

        return lista;
    }
}
