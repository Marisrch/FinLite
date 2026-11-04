package br.com.finlite.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Empreendedor")
public class Empreendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empreendedor")
    private Integer idEmpreendedor;

    @Column(name = "nome_empreendedor", nullable = false, length = 100)
    private String nomeEmpreendedor;

    @Column(name = "cpf_empreendedor", unique = true, length = 11)
    private String cpfEmpreendedor;

    @Column(name = "email_empreendedor", length = 100)
    private String emailEmpreendedor;

    @Column(name = "telefone_empreendedor", length = 20)
    private String telefoneEmpreendedor;

    @OneToMany(mappedBy = "empreendedor")
    private List<Venda> vendas;

    @OneToMany(mappedBy = "empreendedor")
    private List<Material> materiais;

    @OneToMany(mappedBy = "empreendedor")
    private List<LancamentoFinanceiro> lancamentos;

    public Empreendedor() {
    }

    // Getters e setters.


    public Integer getIdEmpreendedor() {
        return idEmpreendedor;
    }

    public void setIdEmpreendedor(Integer idEmpreendedor) {
        this.idEmpreendedor = idEmpreendedor;
    }

    public String getNomeEmpreendedor() {
        return nomeEmpreendedor;
    }

    public void setNomeEmpreendedor(String nomeEmpreendedor) {
        this.nomeEmpreendedor = nomeEmpreendedor;
    }

    public String getCpfEmpreendedor() {
        return cpfEmpreendedor;
    }

    public void setCpfEmpreendedor(String cpfEmpreendedor) {
        this.cpfEmpreendedor = cpfEmpreendedor;
    }

    public String getEmailEmpreendedor() {
        return emailEmpreendedor;
    }

    public void setEmailEmpreendedor(String emailEmpreendedor) {
        this.emailEmpreendedor = emailEmpreendedor;
    }

    public String getTelefoneEmpreendedor() {
        return telefoneEmpreendedor;
    }

    public void setTelefoneEmpreendedor(String telefoneEmpreendedor) {
        this.telefoneEmpreendedor = telefoneEmpreendedor;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    public List<LancamentoFinanceiro> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<LancamentoFinanceiro> lancamentos) {
        this.lancamentos = lancamentos;
    }
}