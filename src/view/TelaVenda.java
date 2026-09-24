package view;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import dao.ItemVendaDAO;
import dao.ProdutoMaterialDAO;
import dao.MaterialDAO;

import model.Cliente;
import model.Produto;
import model.Venda;
import model.ItemVenda;
import model.ProdutoMaterial;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class TelaVenda extends javax.swing.JFrame {

    public TelaVenda() {

        initComponents();

        carregarClientes();

        carregarProdutos();

        configurarTabela();
    }

    public void carregarClientes() {

        try {

            ClienteDAO dao
                    = new ClienteDAO();

            ArrayList<Cliente> lista
                    = dao.listarClientes();

            combo_cliente.removeAllItems();

            for (Cliente cliente : lista) {

                combo_cliente.addItem(cliente.getNomeCliente());
            }

        } catch (Exception e) {

            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    public void carregarProdutos() {

        try {

            ProdutoDAO dao = new ProdutoDAO();

            ArrayList<Produto> lista = dao.listarProdutos();

            combo_produto.removeAllItems();

            for (Produto produto : lista) {

                combo_produto.addItem(produto.getNomeProduto());
            }

        } catch (Exception e) {

            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
    }

    public void configurarTabela() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Valor Unitário");
        modelo.addColumn("Subtotal");

        tabela_itens.setModel(modelo);
    }

    public void adicionarItemTabela() {

        try {

            String produto = combo_produto.getSelectedItem().toString();

            int quantidade = Integer.parseInt(txt_quantidade.getText()
            );

            ProdutoDAO dao = new ProdutoDAO();

            ArrayList<Produto> lista = dao.listarProdutos();

            double preco = 0;

            for (Produto p : lista) {

                if (p.getNomeProduto().equals(produto)) {

                    preco = p.getPrecoBaseProduto();
                }
            }

            double subtotal = preco * quantidade;

            DefaultTableModel modelo = (DefaultTableModel) tabela_itens.getModel();

            modelo.insertRow(0, new Object[]{
                produto,
                quantidade,
                preco,
                subtotal
            });

            calcularTotal();

            txt_quantidade.setText("");

        } catch (Exception e) {

            System.out.println("Erro ao adicionar item: " + e.getMessage());
        }
    }

    public void calcularTotal() {

        double total = 0;

        DefaultTableModel modelo = (DefaultTableModel) tabela_itens.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {

            Object valor = modelo.getValueAt(i, 3);

            if (valor != null) {

                double subtotal = Double.parseDouble(valor.toString());

                total += subtotal;
            }
        }

        lbl_total.setText("TOTAL: R$ " + total);
    }

    public void removerItem() {

        int linhaSelecionada = tabela_itens.getSelectedRow();

        if (linhaSelecionada != -1) {

            DefaultTableModel modelo = (DefaultTableModel) tabela_itens.getModel();

            modelo.removeRow(linhaSelecionada);

            calcularTotal();

        } else {

            System.out.println("Selecione um item para remover.");
        }
    }

    public void finalizarVenda() {

        try {

            ClienteDAO clienteDAO = new ClienteDAO();

            ProdutoDAO produtoDAO = new ProdutoDAO();

            VendaDAO vendaDAO = new VendaDAO();

            ItemVendaDAO itemDAO = new ItemVendaDAO();

            ArrayList<Cliente> clientes
                    = clienteDAO.listarClientes();

            ArrayList<Produto> produtos = produtoDAO.listarProdutos();

            String nomeCliente = combo_cliente.getSelectedItem().toString();

            int idCliente = 0;

            for (Cliente c : clientes) {

                if (c.getNomeCliente().equals(nomeCliente)) {

                    idCliente = c.getIdCliente();
                }
            }

            String dataVenda = txt_data.getText();

            String statusVenda = combo_status.getSelectedItem().toString();

            Venda venda = new Venda();

            venda.setIdCliente(idCliente);

            venda.setIdEmpreendedor(1);

            venda.setDataVenda(dataVenda);

            venda.setStatusVenda(statusVenda);

            int idVendaGerado = vendaDAO.cadastrarVenda(venda);

            DefaultTableModel modelo = (DefaultTableModel) tabela_itens.getModel();

            for (int i = 0; i < modelo.getRowCount(); i++) {

                String nomeProduto = modelo.getValueAt(i, 0).toString();

                int quantidade = Integer.parseInt(modelo.getValueAt(i, 1).toString());

                double preco = Double.parseDouble(modelo.getValueAt(i, 2).toString());

                double subtotal
                        = Double.parseDouble(modelo.getValueAt(i, 3).toString());

                int idProduto = 0;

                for (Produto p : produtos) {

                    if (p.getNomeProduto().equals(nomeProduto)) {

                        idProduto = p.getIdProduto();
                    }
                }

                ItemVenda item = new ItemVenda();

                item.setIdVenda(idVendaGerado);

                item.setIdProduto(idProduto);

                item.setQuantidadeItemVenda(quantidade);

                item.setPrecoUnitarioItemVenda(preco);

                item.setCustoItemVenda(subtotal);

                itemDAO.cadastrarItemVenda(item);

                ProdutoMaterialDAO produtoMaterialDAO
                        = new ProdutoMaterialDAO();

                MaterialDAO materialDAO = new MaterialDAO();

                //ArrayList<ProdutoMaterial> composicao = produtoMaterialDAO.listarPorProduto(idProduto);

                //for (ProdutoMaterial pm : composicao) {

             //       double quantidadeConsumida = pm.getQuantidadeMaterial() * quantidade;

            //        materialDAO.baixarEstoque(pm.getIdMaterial(), quantidadeConsumida);
              //  }
            }

            JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso!");

            limparTela();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao finalizar venda: " + e.getMessage());
        }
    }

    public void limparTela() {

        txt_quantidade.setText("");

        txt_data.setText("");

        lbl_total.setText(
                "TOTAL: R$ 0,00"
        );

        DefaultTableModel modelo = (DefaultTableModel) tabela_itens.getModel();

        modelo.setRowCount(0);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_Tela_Venda = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();
        combo_cliente = new javax.swing.JComboBox<>();
        lbl_data = new javax.swing.JLabel();
        txt_data = new javax.swing.JTextField();
        lbl_status = new javax.swing.JLabel();
        combo_status = new javax.swing.JComboBox<>();
        lbl_produto = new javax.swing.JLabel();
        combo_produto = new javax.swing.JComboBox<>();
        lbl_quantidade = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        btn_adicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_itens = new javax.swing.JTable();
        lbl_total = new javax.swing.JLabel();
        btn_finalizar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_remover = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Tela_Venda.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Tela_Venda.setForeground(new java.awt.Color(153, 0, 0));
        lbl_Tela_Venda.setText("Registrar Vendas");
        jPanel1.add(lbl_Tela_Venda, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        lbl_cliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_cliente.setText("Cliente:");
        jPanel1.add(lbl_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        combo_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(combo_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 131, -1));

        lbl_data.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_data.setText("Data: ");
        jPanel1.add(lbl_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));
        jPanel1.add(txt_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 130, -1));

        lbl_status.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_status.setText("Status: ");
        jPanel1.add(lbl_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EM ANDAMENTO", "CONCLUÍDA", "CANCELADA" }));
        combo_status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_statusActionPerformed(evt);
            }
        });
        jPanel1.add(combo_status, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        lbl_produto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_produto.setText("Produto: ");
        jPanel1.add(lbl_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        combo_produto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(combo_produto, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 130, -1));

        lbl_quantidade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_quantidade.setText("Quantidade: ");
        jPanel1.add(lbl_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        txt_quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantidadeActionPerformed(evt);
            }
        });
        jPanel1.add(txt_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 130, -1));

        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 120, -1));

        tabela_itens.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabela_itens);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 480, 86));

        lbl_total.setText("TOTAL: R$ 0,00");
        jPanel1.add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        btn_finalizar.setText("Finalizar");
        btn_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finalizarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 110, -1));

        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 110, -1));

        btn_remover.setText(" Remover item");
        btn_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removerActionPerformed(evt);
            }
        });
        jPanel1.add(btn_remover, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 120, -1));

        btn_voltar.setText("Voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void combo_statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_statusActionPerformed

    private void txt_quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantidadeActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed

        adicionarItemTabela();

    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removerActionPerformed
        removerItem();
    }//GEN-LAST:event_btn_removerActionPerformed

    private void btn_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finalizarActionPerformed
        finalizarVenda();
    }//GEN-LAST:event_btn_finalizarActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        TelaMenuPrincipal tela = new TelaMenuPrincipal();

        tela.setVisible(true);

        dispose();
    }//GEN-LAST:event_btn_voltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_finalizar;
    private javax.swing.JButton btn_remover;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JComboBox<String> combo_cliente;
    private javax.swing.JComboBox<String> combo_produto;
    private javax.swing.JComboBox<String> combo_status;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Tela_Venda;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JLabel lbl_data;
    private javax.swing.JLabel lbl_produto;
    private javax.swing.JLabel lbl_quantidade;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JTable tabela_itens;
    private javax.swing.JTextField txt_data;
    private javax.swing.JTextField txt_quantidade;
    // End of variables declaration//GEN-END:variables
}
