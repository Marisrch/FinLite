package model;

public class Material {

    private int idMaterial;
    private String nomeMaterial;
    private String corMaterial;
    private String marcaMaterial;
    private double custoUnitarioMaterial;
    private double quantidadeEstoqueMaterial;
    private String unidadeMedidaMaterial;

    // Construtor vazio
    public Material() {
    }

    // Construtor completo
    public Material(int idMaterial, String nomeMaterial, String corMaterial,
            String marcaMaterial, double custoUnitarioMaterial,
            double quantidadeEstoqueMaterial, String unidadeMedidaMaterial) {

        this.idMaterial = idMaterial;
        this.nomeMaterial = nomeMaterial;
        this.corMaterial = corMaterial;
        this.marcaMaterial = marcaMaterial;
        this.custoUnitarioMaterial = custoUnitarioMaterial;
        this.quantidadeEstoqueMaterial = quantidadeEstoqueMaterial;
        this.unidadeMedidaMaterial = unidadeMedidaMaterial;
    }

    // Getters e Setters
    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getCorMaterial() {
        return corMaterial;
    }

    public void setCorMaterial(String corMaterial) {
        this.corMaterial = corMaterial;
    }

    public String getMarcaMaterial() {
        return marcaMaterial;
    }

    public void setMarcaMaterial(String marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
    }

    public double getCustoUnitarioMaterial() {
        return custoUnitarioMaterial;
    }

    public void setCustoUnitarioMaterial(double custoUnitarioMaterial) {
        this.custoUnitarioMaterial = custoUnitarioMaterial;
    }

    public double getQuantidadeEstoqueMaterial() {
        return quantidadeEstoqueMaterial;
    }

    public void setQuantidadeEstoqueMaterial(double quantidadeEstoqueMaterial) {
        this.quantidadeEstoqueMaterial = quantidadeEstoqueMaterial;
    }

    public String getUnidadeMedidaMaterial() {
        return unidadeMedidaMaterial;
    }

    public void setUnidadeMedidaMaterial(String unidadeMedidaMaterial) {
        this.unidadeMedidaMaterial = unidadeMedidaMaterial;
    }

    @Override
    public String toString() {

        return nomeMaterial
                + " - "
                + corMaterial;
    }
}
