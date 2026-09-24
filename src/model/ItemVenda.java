package model;

public class ItemVenda {

    private int idItemVenda;
    private int idVenda;
    private int idProduto;
    private int quantidadeItemVenda;
    private double precoUnitarioItemVenda;
    private double custoItemVenda;

    // Construtor vazio
    public ItemVenda() {
    }

    // Construtor completo
    public ItemVenda(int idItemVenda, int idVenda, int idProduto,
            int quantidadeItemVenda, double precoUnitarioItemVenda,
            double custoItemVenda) {

        this.idItemVenda = idItemVenda;
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidadeItemVenda = quantidadeItemVenda;
        this.precoUnitarioItemVenda = precoUnitarioItemVenda;
        this.custoItemVenda = custoItemVenda;
    }

    // Getters e Setters
    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeItemVenda() {
        return quantidadeItemVenda;
    }

    public void setQuantidadeItemVenda(int quantidadeItemVenda) {
        this.quantidadeItemVenda = quantidadeItemVenda;
    }

    public double getPrecoUnitarioItemVenda() {
        return precoUnitarioItemVenda;
    }

    public void setPrecoUnitarioItemVenda(double precoUnitarioItemVenda) {
        this.precoUnitarioItemVenda = precoUnitarioItemVenda;
    }

    public double getCustoItemVenda() {
        return custoItemVenda;
    }

    public void setCustoItemVenda(double custoItemVenda) {
        this.custoItemVenda = custoItemVenda;
    }
}
