package model;

public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private double precoBaseProduto;
    private String prazoProducaoProduto;
    private int idEmpreendedor;

    // Construtor vazio
    public Produto() {
    }

    // Construtor completo
    public Produto(int idProduto, String nomeProduto, String descricaoProduto,
                   double precoBaseProduto, String prazoProducaoProduto, int idEmpreendedor) {

        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoBaseProduto = precoBaseProduto;
        this.prazoProducaoProduto = prazoProducaoProduto;
        this.idEmpreendedor = idEmpreendedor;
    }
    
     // Getters e Setters
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getPrecoBaseProduto() {
        return precoBaseProduto;
    }

    public void setPrecoBaseProduto(double precoBaseProduto) {
        this.precoBaseProduto = precoBaseProduto;
    }

    public String getPrazoProducaoProduto() {
        return prazoProducaoProduto;
    }

    public void setPrazoProducaoProduto(String prazoProducaoProduto) {
        this.prazoProducaoProduto = prazoProducaoProduto;
    }
    
    public int getIdEmpreendedor() {
        return idEmpreendedor;
    }

    public void setIdEmpreendedor(int idEmpreendedor) {
        this.idEmpreendedor = idEmpreendedor;
    }
}
