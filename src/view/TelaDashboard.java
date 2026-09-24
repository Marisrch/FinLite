package view;

import dao.DashboardDAO;

import javax.swing.JOptionPane;

public class TelaDashboard extends javax.swing.JFrame {

    public TelaDashboard() {

        initComponents();

        carregarDashboard();
    }

    public void carregarDashboard() {

        try {

            DashboardDAO dao
                    = new DashboardDAO();

            double totalVendido
                    = dao.calcularTotalVendido();

            double gastos
                    = dao.calcularGastos();

            double faturamento
                    = dao.calcularFaturamento();

            String produtoMaisVendido
                    = dao.produtoMaisVendido();

            lbl_totalVendas.setText(
                    "TOTAL VENDIDO: R$ "
                    + totalVendido
            );

            lbl_gastos.setText(
                    "GASTOS: R$ "
                    + gastos
            );

            lbl_faturamento.setText(
                    "Faturamento TOTAL: R$ "
                    + faturamento
            );

            lbl_produtoMaisVendido.setText(
                    "PRODUTO MAIS VENDIDO: "
                    + produtoMaisVendido
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar dashboard: "
                    + e.getMessage()
            );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        lbl_totalVendas = new javax.swing.JLabel();
        lbl_faturamento = new javax.swing.JLabel();
        lbl_gastos = new javax.swing.JLabel();
        lbl_produtoMaisVendido = new javax.swing.JLabel();
        btn_atualizar = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_titulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_titulo.setForeground(new java.awt.Color(153, 0, 0));
        lbl_titulo.setText("Dashboard Financeiro");

        lbl_totalVendas.setText("TOTAL VENDIDO: R$ 0,00");

        lbl_faturamento.setText("FATURAMENTO TOTAL: R$ 0,00");

        lbl_gastos.setText("GASTOS: R$ 0,00");

        lbl_produtoMaisVendido.setText("PRODUTO MAIS VENDIDO:");

        btn_atualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_atualizar.setText("Atualizar");
        btn_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizarActionPerformed(evt);
            }
        });

        btn_voltar.setText("Voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_titulo)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_voltar)
                        .addGap(55, 55, 55)))
                .addGap(140, 140, 140))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_atualizar)
                    .addComponent(lbl_faturamento)
                    .addComponent(lbl_totalVendas)
                    .addComponent(lbl_gastos)
                    .addComponent(lbl_produtoMaisVendido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_titulo)
                .addGap(38, 38, 38)
                .addComponent(lbl_totalVendas)
                .addGap(18, 18, 18)
                .addComponent(lbl_faturamento)
                .addGap(18, 18, 18)
                .addComponent(lbl_gastos)
                .addGap(18, 18, 18)
                .addComponent(lbl_produtoMaisVendido)
                .addGap(29, 29, 29)
                .addComponent(btn_atualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(btn_voltar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizarActionPerformed
        carregarDashboard();
    }//GEN-LAST:event_btn_atualizarActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        new TelaMenuPrincipal().setVisible(true);

        dispose();
    }//GEN-LAST:event_btn_voltarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atualizar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_faturamento;
    private javax.swing.JLabel lbl_gastos;
    private javax.swing.JLabel lbl_produtoMaisVendido;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JLabel lbl_totalVendas;
    // End of variables declaration//GEN-END:variables
}
