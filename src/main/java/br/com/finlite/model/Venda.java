package br.com.finlite.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda")
    private Integer idVenda;

    @Column(name = "nome_cliente", nullable = false, length = 100)
    private String nomeCliente;

    @Column(name = "produto_peca", nullable = false, length = 100)
    private String produtoPeca;

    @Column(name = "valor_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorVenda;

    @Column(name = "forma_pagamento", nullable = false, length = 30)
    private String formaPagamento;

    @Column(name = "status_venda", nullable = false, length = 30)
    private String statusVenda;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "data_registro", nullable = false)
    private LocalDate dataRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empreendedor", nullable = false)
    private Empreendedor empreendedor;

    public Venda() {
    }

    // Getters e setters.


    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getProdutoPeca() {
        return produtoPeca;
    }

    public void setProdutoPeca(String produtoPeca) {
        this.produtoPeca = produtoPeca;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Empreendedor getEmpreendedor() {
        return empreendedor;
    }

    public void setEmpreendedor(Empreendedor empreendedor) {
        this.empreendedor = empreendedor;
    }
}