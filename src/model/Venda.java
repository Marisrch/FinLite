package model;

public class Venda {

    private int idVenda;
    private int idCliente;
    private int idEmpreendedor;
    private String dataVenda;
    private String statusVenda;

    public Venda() {
    }

    public Venda(
            int idCliente,
            int idEmpreendedor,
            String dataVenda,
            String statusVenda) {

        this.idCliente = idCliente;
        this.idEmpreendedor = idEmpreendedor;
        this.dataVenda = dataVenda;
        this.statusVenda = statusVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpreendedor() {
        return idEmpreendedor;
    }

    public void setIdEmpreendedor(int idEmpreendedor) {
        this.idEmpreendedor = idEmpreendedor;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }
}
