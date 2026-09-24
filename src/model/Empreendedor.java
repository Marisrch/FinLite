package model;

public class Empreendedor {

    private int idEmpreendedor;

    private String nomeEmpreendedor;

    private String cpfEmpreendedor;

    private String emailEmpreendedor;

    private String telefoneEmpreendedor;

    public Empreendedor() {
    }

    public Empreendedor(
            int idEmpreendedor,
            String nomeEmpreendedor,
            String cpfEmpreendedor,
            String emailEmpreendedor,
            String telefoneEmpreendedor) {

        this.idEmpreendedor = idEmpreendedor;
        this.nomeEmpreendedor = nomeEmpreendedor;
        this.cpfEmpreendedor = cpfEmpreendedor;
        this.emailEmpreendedor = emailEmpreendedor;
        this.telefoneEmpreendedor = telefoneEmpreendedor;
    }

    public int getIdEmpreendedor() {
        return idEmpreendedor;
    }

    public void setIdEmpreendedor(int idEmpreendedor) {
        this.idEmpreendedor = idEmpreendedor;
    }

    public String getNomeEmpreendedor() {
        return nomeEmpreendedor;
    }

    public void setNomeEmpreendedor(
            String nomeEmpreendedor) {

        this.nomeEmpreendedor = nomeEmpreendedor;
    }

    public String getCpfEmpreendedor() {
        return cpfEmpreendedor;
    }

    public void setCpfEmpreendedor(
            String cpfEmpreendedor) {

        this.cpfEmpreendedor = cpfEmpreendedor;
    }

    public String getEmailEmpreendedor() {
        return emailEmpreendedor;
    }

    public void setEmailEmpreendedor(
            String emailEmpreendedor) {

        this.emailEmpreendedor = emailEmpreendedor;
    }

    public String getTelefoneEmpreendedor() {
        return telefoneEmpreendedor;
    }

    public void setTelefoneEmpreendedor(
            String telefoneEmpreendedor) {

        this.telefoneEmpreendedor
                = telefoneEmpreendedor;
    }
}
