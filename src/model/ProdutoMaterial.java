package model;

public class ProdutoMaterial {

    private int idProdutoMaterial;

    private int idProduto;

    private int idMaterial;

    private double quantidadeMaterial;

    public int getIdProdutoMaterial() {
        return idProdutoMaterial;
    }

    public void setIdProdutoMaterial(
            int idProdutoMaterial) {

        this.idProdutoMaterial
                = idProdutoMaterial;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(
            int idProduto) {

        this.idProduto
                = idProduto;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(
            int idMaterial) {

        this.idMaterial
                = idMaterial;
    }

    public double getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    public void setQuantidadeMaterial(
            double quantidadeMaterial) {

        this.quantidadeMaterial
                = quantidadeMaterial;
    }
}
