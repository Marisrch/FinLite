package br.com.finlite.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Movimentacao_Estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Integer idMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @Column(name = "tipo_movimentacao", nullable = false, length = 30)
    private String tipoMovimentacao;

    @Column(name = "quantidade_movimentacao", nullable = false,
            precision = 10, scale = 2)
    private BigDecimal quantidadeMovimentacao;

    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;

    @Column(name = "motivo_origem", length = 100)
    private String motivoOrigem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lancamento", unique = true)
    private LancamentoFinanceiro lancamento;

    public MovimentacaoEstoque() {
    }

    // Getters e setters.


    public Integer getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(Integer idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public BigDecimal getQuantidadeMovimentacao() {
        return quantidadeMovimentacao;
    }

    public void setQuantidadeMovimentacao(BigDecimal quantidadeMovimentacao) {
        this.quantidadeMovimentacao = quantidadeMovimentacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getMotivoOrigem() {
        return motivoOrigem;
    }

    public void setMotivoOrigem(String motivoOrigem) {
        this.motivoOrigem = motivoOrigem;
    }

    public LancamentoFinanceiro getLancamento() {
        return lancamento;
    }

    public void setLancamento(LancamentoFinanceiro lancamento) {
        this.lancamento = lancamento;
    }
}