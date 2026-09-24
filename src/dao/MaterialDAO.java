package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import model.Material;

public class MaterialDAO {

    private Connection connection;

    public MaterialDAO() {

        this.connection = ConnectionFactory.getConnection();
    }

    // CREATE
    public void cadastrarMaterial(Material material) {

        String sql
                = "INSERT INTO Material "
                + "(nome_material, "
                + "cor_material, "
                + "marca_material, "
                + "custo_unitario_material, "
                + "quantidade_estoque_material, "
                + "unidade_medida_material) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setString(
                    1,
                    material.getNomeMaterial()
            );

            stmt.setString(
                    2,
                    material.getCorMaterial()
            );

            stmt.setString(
                    3,
                    material.getMarcaMaterial()
            );

            stmt.setDouble(
                    4,
                    material.getCustoUnitarioMaterial()
            );

            stmt.setDouble(
                    5,
                    material.getQuantidadeEstoqueMaterial()
            );

            stmt.setString(
                    6,
                    material.getUnidadeMedidaMaterial()
            );

            stmt.executeUpdate();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao cadastrar material: "
                    + e.getMessage()
            );
        }
    }

    // READ
    public ArrayList<Material> listarMateriais() {

        ArrayList<Material> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM Material\n"
                + "ORDER BY nome_material";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                Material material
                        = new Material();

                material.setIdMaterial(
                        rs.getInt("id_material")
                );

                material.setNomeMaterial(
                        rs.getString("nome_material")
                );

                material.setCorMaterial(
                        rs.getString("cor_material")
                );

                material.setMarcaMaterial(
                        rs.getString("marca_material")
                );

                material.setCustoUnitarioMaterial(
                        rs.getDouble("custo_unitario_material")
                );

                material.setQuantidadeEstoqueMaterial(
                        rs.getDouble("quantidade_estoque_material")
                );

                material.setUnidadeMedidaMaterial(
                        rs.getString("unidade_medida_material")
                );

                lista.add(material);
            }

            rs.close();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao listar materiais: "
                    + e.getMessage()
            );
        }

        return lista;
    }

    public void atualizarMaterial(Material material) {

        String sql
                = "UPDATE Material SET "
                + "nome_material = ?, "
                + "cor_material = ?, "
                + "marca_material = ?, "
                + "custo_unitario_material = ?, "
                + "quantidade_estoque_material = ?, "
                + "unidade_medida_material = ? "
                + "WHERE id_material = ?";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setString(1,
                    material.getNomeMaterial());

            stmt.setString(2,
                    material.getCorMaterial());

            stmt.setString(3,
                    material.getMarcaMaterial());

            stmt.setDouble(4,
                    material.getCustoUnitarioMaterial());

            stmt.setDouble(5,
                    material.getQuantidadeEstoqueMaterial());

            stmt.setString(6,
                    material.getUnidadeMedidaMaterial());

            stmt.setInt(7,
                    material.getIdMaterial());

            stmt.executeUpdate();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao atualizar material: "
                    + e.getMessage()
            );
        }
    }

    public void excluirMaterial(int id) {

        String sql
                = "DELETE FROM Material "
                + "WHERE id_material = ?";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao atualizar material: "
                    + e.getMessage()
            );
        }
    }

    public Material buscarMaterialPorId(int id) {

        Material material = new Material();

        String sql
                = "SELECT * FROM Material "
                + "WHERE id_material = ?";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs
                    = stmt.executeQuery();

            if (rs.next()) {

                material.setIdMaterial(
                        rs.getInt("id_material")
                );

                material.setNomeMaterial(
                        rs.getString("nome_material")
                );

                material.setCorMaterial(
                        rs.getString("cor_material")
                );

                material.setMarcaMaterial(
                        rs.getString("marca_material")
                );

                material.setCustoUnitarioMaterial(
                        rs.getDouble("custo_unitario_material")
                );

                material.setQuantidadeEstoqueMaterial(
                        rs.getDouble("quantidade_estoque_material")
                );

                material.setUnidadeMedidaMaterial(
                        rs.getString("unidade_medida_material")
                );
            }

            rs.close();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao buscar material: "
                    + e.getMessage()
            );
        }

        return material;
    }

    public void baixarEstoque(
            int idMaterial,
            double quantidadeConsumida) {

        String sql
                = "UPDATE Material "
                + "SET quantidade_estoque_material = "
                + "quantidade_estoque_material - ? "
                + "WHERE id_material = ?";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setDouble(
                    1,
                    quantidadeConsumida
            );

            stmt.setInt(
                    2,
                    idMaterial
            );

            stmt.executeUpdate();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao baixar estoque: "
                    + e.getMessage()
            );
        }
    }
}
