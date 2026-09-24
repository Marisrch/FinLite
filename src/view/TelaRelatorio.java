package view;

import dao.RelatorioDAO;

import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaRelatorio extends javax.swing.JFrame {

    public TelaRelatorio() {

        initComponents();

        configurarTabela();
    }

    public void configurarTabela() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Data");
        modelo.addColumn("Cliente");
        modelo.addColumn("Status");
        modelo.addColumn("Valor Total");

        tabela_relatorio.setModel(modelo);
    }

    public void buscarRelatorio() {

        try {

            String dataInicial = txt_dataInicial.getText();

            String dataFinal = txt_dataFinal.getText();

            RelatorioDAO dao = new RelatorioDAO();

            ResultSet rs = dao.buscarVendasPeriodo(dataInicial, dataFinal);

            DefaultTableModel modelo = (DefaultTableModel) tabela_relatorio.getModel();

            modelo.setRowCount(0);

            double totalPeriodo = 0;

            while (rs.next()) {

                double total = rs.getDouble("total");

                modelo.addRow(new Object[]{
                    rs.getInt("id_venda"),
                    rs.getString("data_venda"),
                    rs.getString("nome_cliente"),
                    rs.getString("status_venda"),
                    total
                });

                totalPeriodo += total;
            }

            lbl_totalPeriodo.setText("TOTAL VENDIDO NO PERÍODO: R$ " + totalPeriodo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_dataFinal = new javax.swing.JTextField();
        lbl_dataFinal = new javax.swing.JLabel();
        txt_dataInicial = new javax.swing.JTextField();
        lbl_dataInicial = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_relatorio = new javax.swing.JTable();
        lbl_totalPeriodo = new javax.swing.JLabel();
        lbl_titulo = new javax.swing.JLabel();
        btn_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txt_dataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 120, -1));

        lbl_dataFinal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_dataFinal.setText("Data Final:");
        jPanel1.add(lbl_dataFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));
        jPanel1.add(txt_dataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 120, -1));

        lbl_dataInicial.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_dataInicial.setText("Data Inicial:");
        jPanel1.add(lbl_dataInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        btn_buscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        tabela_relatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabela_relatorio);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 420, 110));

        lbl_totalPeriodo.setText("TOTAL VENDIDO NO PERÍODO: R$ 0,00");
        jPanel1.add(lbl_totalPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        lbl_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(153, 0, 0));
        lbl_titulo.setText("Relatório de Vendas");
        jPanel1.add(lbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        btn_voltar.setText("Voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 450, 410));

        setSize(new java.awt.Dimension(488, 448));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        buscarRelatorio();
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        new TelaMenuPrincipal().setVisible(true);

        dispose();
    }//GEN-LAST:event_btn_voltarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRelatorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_dataFinal;
    private javax.swing.JLabel lbl_dataInicial;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_totalPeriodo;
    private javax.swing.JTable tabela_relatorio;
    private javax.swing.JTextField txt_dataFinal;
    private javax.swing.JTextField txt_dataInicial;
    // End of variables declaration//GEN-END:variables
}
