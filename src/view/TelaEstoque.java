package view;

import dao.MaterialDAO;
import model.Material;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dao.MovimentacaoEstoqueDAO;
import model.MovimentacaoEstoque;

import javax.swing.JOptionPane;

public class TelaEstoque extends javax.swing.JFrame {

    public TelaEstoque() {

        initComponents();

        carregarTabelaEstoque();

        carregarComboMaterial();

        carregarHistorico();
    }

    public void carregarTabelaEstoque() {

        try {

            MaterialDAO dao
                    = new MaterialDAO();

            ArrayList<Material> lista
                    = dao.listarMateriais();

            DefaultTableModel modelo
                    = (DefaultTableModel) tabela_estoque.getModel();

            modelo.setRowCount(0);

            for (Material material : lista) {

                modelo.addRow(new Object[]{
                    material.getIdMaterial(),
                    material.getNomeMaterial(),
                    material.getCorMaterial(),
                    material.getQuantidadeEstoqueMaterial(),
                    material.getUnidadeMedidaMaterial(),
                    material.getCustoUnitarioMaterial()
                });
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao carregar tabela: "
                    + e.getMessage()
            );
        }
    }

    public void carregarComboMaterial() {

        try {

            MaterialDAO dao
                    = new MaterialDAO();

            ArrayList<Material> lista
                    = dao.listarMateriais();

            combo_material.removeAllItems();

            for (Material material : lista) {

                combo_material.addItem(material);
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao carregar combo: "
                    + e.getMessage()
            );
        }
    }

    public void carregarHistorico() {

        try {

            MovimentacaoEstoqueDAO dao
                    = new MovimentacaoEstoqueDAO();

            ArrayList<MovimentacaoEstoque> lista
                    = dao.listarMovimentacoes();

            DefaultTableModel modelo
                    = (DefaultTableModel) tabela_historico.getModel();

            modelo.setRowCount(0);

            MaterialDAO materialDAO
                    = new MaterialDAO();

            for (MovimentacaoEstoque mov : lista) {

                Material material
                        = materialDAO.buscarMaterialPorId(
                                mov.getIdMaterial()
                        );

                modelo.addRow(new Object[]{
                    mov.getIdMovimentacao(),
                    material.getNomeMaterial(),
                    mov.getTipoMovimentacao(),
                    mov.getQuantidade()
                });
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao carregar histórico: "
                    + e.getMessage()
            );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbl_TelaEstoque = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_estoque = new javax.swing.JTable();
        lbl_material = new javax.swing.JLabel();
        combo_tipo = new javax.swing.JComboBox<>();
        lbl_tipo = new javax.swing.JLabel();
        lbl_quantidade = new javax.swing.JLabel();
        combo_material = new javax.swing.JComboBox();
        txt_quantidade = new javax.swing.JTextField();
        btn_registrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_historico = new javax.swing.JTable();
        txt_voltar = new javax.swing.JButton();
        lbl_TelaEstoque1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_TelaEstoque.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_TelaEstoque.setForeground(new java.awt.Color(153, 0, 0));
        lbl_TelaEstoque.setText("Histórico");
        jPanel2.add(lbl_TelaEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 370, -1, -1));

        tabela_estoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Cor", "Estoque", "Unidade", "Custo"
            }
        ));
        jScrollPane2.setViewportView(tabela_estoque);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 390, 126));

        lbl_material.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_material.setText("Material:");
        jPanel2.add(lbl_material, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        combo_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrada", "Saída" }));
        jPanel2.add(combo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 160, -1));

        lbl_tipo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_tipo.setText("Tipo: ");
        jPanel2.add(lbl_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        lbl_quantidade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_quantidade.setText("Quantidade:");
        jPanel2.add(lbl_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        combo_material.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo_material.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_materialActionPerformed(evt);
            }
        });
        jPanel2.add(combo_material, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 160, -1));

        txt_quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantidadeActionPerformed(evt);
            }
        });
        jPanel2.add(txt_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 160, -1));

        btn_registrar.setText("Registrar");
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        tabela_historico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Material", "Tipo", "Quantidade"
            }
        ));
        jScrollPane1.setViewportView(tabela_historico);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 410, 103));

        txt_voltar.setText("Voltar");
        txt_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_voltarActionPerformed(evt);
            }
        });
        jPanel2.add(txt_voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, -1, -1));

        lbl_TelaEstoque1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_TelaEstoque1.setForeground(new java.awt.Color(153, 0, 0));
        lbl_TelaEstoque1.setText("Estoque");
        jPanel2.add(lbl_TelaEstoque1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantidadeActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        try {

            // MATERIAL SELECIONADO
            Material material
                    = (Material) combo_material.getSelectedItem();

            // TIPO
            String tipo
                    = combo_tipo.getSelectedItem()
                            .toString();

            // QUANTIDADE
            double quantidade
                    = Double.parseDouble(
                            txt_quantidade.getText()
                    );

            // CRIAR MOVIMENTAÇÃO
            MovimentacaoEstoque mov
                    = new MovimentacaoEstoque();

            mov.setIdMaterial(
                    material.getIdMaterial()
            );

            // BANCO USA MINÚSCULO
            if (tipo.equals("Entrada")) {

                mov.setTipoMovimentacao(
                        "entrada"
                );

            } else {

                mov.setTipoMovimentacao(
                        "saida"
                );
            }

            mov.setQuantidade(quantidade);

            // SALVAR MOVIMENTAÇÃO
            MovimentacaoEstoqueDAO movDAO
                    = new MovimentacaoEstoqueDAO();

            movDAO.cadastrarMovimentacao(mov);

            // ATUALIZAR ESTOQUE
            double estoqueAtual
                    = material.getQuantidadeEstoqueMaterial();

            if (tipo.equals("Entrada")) {

                material.setQuantidadeEstoqueMaterial(
                        estoqueAtual + quantidade
                );

            } else {

                material.setQuantidadeEstoqueMaterial(
                        estoqueAtual - quantidade
                );
            }

            // SALVAR NOVO ESTOQUE
            MaterialDAO materialDAO
                    = new MaterialDAO();

            materialDAO.atualizarMaterial(material);

            // ATUALIZAR TABELA
            carregarTabelaEstoque();

            carregarHistorico();

            // LIMPAR CAMPO
            txt_quantidade.setText("");

            JOptionPane.showMessageDialog(
                    null,
                    "Movimentação registrada!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro: " + e.getMessage()
            );
        }
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void combo_materialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_materialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_materialActionPerformed

    private void txt_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_voltarActionPerformed
        TelaMenuPrincipal tela
                = new TelaMenuPrincipal();

        tela.setVisible(true);

        dispose();
    }//GEN-LAST:event_txt_voltarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_registrar;
    private javax.swing.JComboBox combo_material;
    private javax.swing.JComboBox<String> combo_tipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_TelaEstoque;
    private javax.swing.JLabel lbl_TelaEstoque1;
    private javax.swing.JLabel lbl_material;
    private javax.swing.JLabel lbl_quantidade;
    private javax.swing.JLabel lbl_tipo;
    private javax.swing.JTable tabela_estoque;
    private javax.swing.JTable tabela_historico;
    private javax.swing.JTextField txt_quantidade;
    private javax.swing.JButton txt_voltar;
    // End of variables declaration//GEN-END:variables
}
