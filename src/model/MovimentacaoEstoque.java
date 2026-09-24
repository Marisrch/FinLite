package model;

import java.time.LocalDateTime;

public class MovimentacaoEstoque {

    private int idMovimentacao;

    private int idMaterial;

    private String tipoMovimentacao;

    private double quantidade;

    private LocalDateTime dataMovimentacao;

    // CONSTRUTOR VAZIO
    public MovimentacaoEstoque() {
    }

    // GETTERS E SETTERS
    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(
            LocalDateTime dataMovimentacao) {

        this.dataMovimentacao
                = dataMovimentacao;
    }
}
