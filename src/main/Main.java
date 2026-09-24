package main;

//import java.sql.Connection;
//import connection.ConnectionFactory;
import view.TelaCliente;
import view.TelaEmpreendedor;
import view.TelaEstoque;
import view.TelaMaterial;
import view.TelaMenuPrincipal;
import view.TelaProduto;
import view.TelaProdutoMaterial;
import view.TelaVenda;

public class Main {

    public static void main(String[] args) {
        /*try {

            try (Connection conexao = ConnectionFactory.getConnection()) {
                System.out.println(
                        "Conexão realizada com sucesso!"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao conectar: " + e.getMessage()
            );
        }*/

        //TelaProdutoMaterial tela = new TelaProdutoMaterial();
        //TelaEstoque tela = new TelaEstoque();
        //TelaVenda tela = new TelaVenda();
        //TelaCliente tela = new TelaCliente();
        java.awt.EventQueue.invokeLater(new Runnable() {

           public void run() {

                new TelaMenuPrincipal().setVisible(true);
            }
            
            
        });
    }
}
