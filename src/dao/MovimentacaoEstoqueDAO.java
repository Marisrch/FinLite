package dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import model.MovimentacaoEstoque;

public class MovimentacaoEstoqueDAO {

    private Connection connection;
    
    

    public MovimentacaoEstoqueDAO() {

        this.connection
                = ConnectionFactory.getConnection();
    }

    // CREATE
    public void cadastrarMovimentacao(
            MovimentacaoEstoque movimentacao) {

        String sql
                = "INSERT INTO Movimentacao_Estoque "
                + "(id_material, "
                + "tipo_movimentacao, "
                + "quantidade) "
                + "VALUES (?, ?, ?)";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            stmt.setInt(
                    1,
                    movimentacao.getIdMaterial()
            );

            stmt.setString(
                    2,
                    movimentacao.getTipoMovimentacao()
            );

            stmt.setDouble(
                    3,
                    movimentacao.getQuantidade()
            );

            stmt.executeUpdate();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao cadastrar movimentação: "
                    + e.getMessage()
            );
        }
    }

    // READ
    public ArrayList<MovimentacaoEstoque>
            listarMovimentacoes() {

        ArrayList<MovimentacaoEstoque> lista
                = new ArrayList<>();

        String sql
                = "SELECT * FROM Movimentacao_Estoque "
                + "ORDER BY data_movimentacao DESC";

        try {

            PreparedStatement stmt
                    = connection.prepareStatement(sql);

            ResultSet rs
                    = stmt.executeQuery();

            while (rs.next()) {

                MovimentacaoEstoque mov
                        = new MovimentacaoEstoque();

                mov.setIdMovimentacao(
                        rs.getInt("id_movimentacao")
                );

                mov.setIdMaterial(
                        rs.getInt("id_material")
                );

                mov.setTipoMovimentacao(
                        rs.getString(
                                "tipo_movimentacao"
                        )
                );

                mov.setQuantidade(
                        rs.getDouble("quantidade")
                );

                lista.add(mov);
            }

            rs.close();

            stmt.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao listar movimentações: "
                    + e.getMessage()
            );
        }

        return lista;
    }

}
