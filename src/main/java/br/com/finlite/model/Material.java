package br.com.finlite.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial;

    @Column(name = "nome_material", nullable = false, length = 100)
    private String nomeMaterial;

    @Column(name = "marca_material", length = 50)
    private String marcaMaterial;

    @Column(name = "cor_material", length = 50)
    private String corMaterial;

    @Column(name = "categoria_material", nullable = false, length = 50)
    private String categoriaMaterial;

    @Column(name = "quantidade_estoque_material", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal quantidadeEstoqueMaterial;

    @Column(name = "quantidade_minima_material", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal quantidadeMinimaMaterial;

    @Column(name = "unidade_medida_material", nullable = false, length = 20)
    private String unidadeMedidaMaterial;

    @Column(name = "custo_unitario_material", precision = 10, scale = 2)
    private BigDecimal custoUnitarioMaterial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empreendedor", nullable = false)
    private Empreendedor empreendedor;

    @OneToMany(mappedBy = "material")
    private List<LancamentoFinanceiro> lancamentos;

    @OneToMany(mappedBy = "material")
    private List<MovimentacaoEstoque> movimentacoes;

    public Material() {
    }

    // Getters e setters.


    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public String getMarcaMaterial() {
        return marcaMaterial;
    }

    public void setMarcaMaterial(String marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
    }

    public String getCorMaterial() {
        return corMaterial;
    }

    public void setCorMaterial(String corMaterial) {
        this.corMaterial = corMaterial;
    }

    public String getCategoriaMaterial() {
        return categoriaMaterial;
    }

    public void setCategoriaMaterial(String categoriaMaterial) {
        this.categoriaMaterial = categoriaMaterial;
    }

    public BigDecimal getQuantidadeEstoqueMaterial() {
        return quantidadeEstoqueMaterial;
    }

    public void setQuantidadeEstoqueMaterial(BigDecimal quantidadeEstoqueMaterial) {
        this.quantidadeEstoqueMaterial = quantidadeEstoqueMaterial;
    }

    public BigDecimal getQuantidadeMinimaMaterial() {
        return quantidadeMinimaMaterial;
    }

    public void setQuantidadeMinimaMaterial(BigDecimal quantidadeMinimaMaterial) {
        this.quantidadeMinimaMaterial = quantidadeMinimaMaterial;
    }

    public String getUnidadeMedidaMaterial() {
        return unidadeMedidaMaterial;
    }

    public void setUnidadeMedidaMaterial(String unidadeMedidaMaterial) {
        this.unidadeMedidaMaterial = unidadeMedidaMaterial;
    }

    public BigDecimal getCustoUnitarioMaterial() {
        return custoUnitarioMaterial;
    }

    public void setCustoUnitarioMaterial(BigDecimal custoUnitarioMaterial) {
        this.custoUnitarioMaterial = custoUnitarioMaterial;
    }

    public Empreendedor getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(Empreendedor empreendedor) {
        this.empreendedor = empreendedor;
    }

    public List<LancamentoFinanceiro> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<LancamentoFinanceiro> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<MovimentacaoEstoque> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoEstoque> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}