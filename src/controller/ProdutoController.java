package controller;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoController {
    
 public void salvarProduto(
            String nome,
            String descricao,
            double preco,
            String prazo,
            int idEmpreendedor
    ) {

        Produto produto = new Produto();

        produto.setNomeProduto(nome);
        produto.setDescricaoProduto(descricao);
        produto.setPrecoBaseProduto(preco);
        produto.setPrazoProducaoProduto(prazo);
        produto.setIdEmpreendedor(idEmpreendedor);

        ProdutoDAO dao = new ProdutoDAO();

        dao.cadastrarProduto(produto);
    }
}
