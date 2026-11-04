package br.com.finlite.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Lancamento_Financeiro")
public class LancamentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lancamento")
    private Integer idLancamento;

    @Column(name = "tipo_lancamento", nullable = false, length = 50)
    private String tipoLancamento;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "descricao_lancamento", nullable = false, length = 255)
    private String descricaoLancamento;

    @Column(name = "categoria_lancamento", nullable = false, length = 50)
    private String categoriaLancamento;

    @Column(name = "quantidade_lancamento", precision = 10, scale = 2)
    private BigDecimal quantidadeLancamento;

    @Column(name = "custo_unitario_lancamento", precision = 10, scale = 2)
    private BigDecimal custoUnitarioLancamento;

    @Column(name = "valor_total_lancamento", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal valorTotalLancamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empreendedor", nullable = false)
    private Empreendedor empreendedor;

    @OneToOne(mappedBy = "lancamento")
    private MovimentacaoEstoque movimentacao;

    public LancamentoFinanceiro() {
    }

    // Getters e setters .


    public Integer getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(Integer idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricaoLancamento() {
        return descricaoLancamento;
    }

    public void setDescricaoLancamento(String descricaoLancamento) {
        this.descricaoLancamento = descricaoLancamento;
    }

    public String getCategoriaLancamento() {
        return categoriaLancamento;
    }

    public void setCategoriaLancamento(String categoriaLancamento) {
        this.categoriaLancamento = categoriaLancamento;
    }

    public BigDecimal getQuantidadeLancamento() {
        return quantidadeLancamento;
    }

    public void setQuantidadeLancamento(BigDecimal quantidadeLancamento) {
        this.quantidadeLancamento = quantidadeLancamento;
    }

    public BigDecimal getCustoUnitarioLancamento() {
        return custoUnitarioLancamento;
    }

    public void setCustoUnitarioLancamento(BigDecimal custoUnitarioLancamento) {
        this.custoUnitarioLancamento = custoUnitarioLancamento;
    }

    public BigDecimal getValorTotalLancamento() {
        return valorTotalLancamento;
    }

    public void setValorTotalLancamento(BigDecimal valorTotalLancamento) {
        this.valorTotalLancamento = valorTotalLancamento;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Empreendedor getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(Empreendedor empreendedor) {
        this.empreendedor = empreendedor;
    }

    public MovimentacaoEstoque getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(MovimentacaoEstoque movimentacao) {
        this.movimentacao = movimentacao;
    }
}