package view;

import dao.ProdutoDAO;
import dao.MaterialDAO;
import dao.ProdutoMaterialDAO;

import model.Produto;
import model.Material;
import model.ProdutoMaterial;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class TelaProdutoMaterial extends javax.swing.JFrame {

    private int idSelecionado = 0;

    public TelaProdutoMaterial() {

        initComponents();

        carregarProdutos();

        carregarMateriais();

        configurarTabela();

        carregarTabela();
    }

    public void carregarProdutos() {

        try {

            ProdutoDAO dao
                    = new ProdutoDAO();

            ArrayList<Produto> lista
                    = dao.listarProdutos();

            combo_produto.removeAllItems();

            for (Produto produto : lista) {

                combo_produto.addItem(
                        produto.getNomeProduto()
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar produtos: "
                    + e.getMessage()
            );
        }
    }

    public void carregarMateriais() {

        try {

            MaterialDAO dao
                    = new MaterialDAO();

            ArrayList<Material> lista
                    = dao.listarMateriais();

            combo_material.removeAllItems();

            for (Material material : lista) {

                combo_material.addItem(
                        material.getNomeMaterial()
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar materiais: "
                    + e.getMessage()
            );
        }
    }

    public void configurarTabela() {

        DefaultTableModel modelo
                = new DefaultTableModel();

        modelo.addColumn("ID");

        modelo.addColumn("Produto");

        modelo.addColumn("Material");

        modelo.addColumn("Quantidade");

        tabela_produto_material.setModel(
                modelo
        );
    }

    public void carregarTabela() {

        try {

            ProdutoMaterialDAO dao
                    = new ProdutoMaterialDAO();

            ArrayList<ProdutoMaterial> lista
                    = dao.listar();

            DefaultTableModel modelo
                    = (DefaultTableModel) tabela_produto_material.getModel();

            modelo.setRowCount(0);

            for (ProdutoMaterial pm : lista) {

                modelo.addRow(new Object[]{
                    pm.getIdProdutoMaterial(),
                    pm.getIdProduto(),
                    pm.getIdMaterial(),
                    pm.getQuantidadeMaterial()

                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar tabela: "
                    + e.getMessage()
            );
        }
    }

    public void adicionarRelacionamento() {

        try {

            ProdutoDAO produtoDAO
                    = new ProdutoDAO();

            MaterialDAO materialDAO
                    = new MaterialDAO();

            ArrayList<Produto> produtos
                    = produtoDAO.listarProdutos();

            ArrayList<Material> materiais
                    = materialDAO.listarMateriais();

            String nomeProduto
                    = combo_produto
                            .getSelectedItem()
                            .toString();

            String nomeMaterial
                    = combo_material
                            .getSelectedItem()
                            .toString();

            int idProduto = 0;
            int idMaterial = 0;

            for (Produto produto : produtos) {

                if (produto.getNomeProduto()
                        .equals(nomeProduto)) {

                    idProduto
                            = produto.getIdProduto();
                }
            }

            for (Material material : materiais) {

                if (material.getNomeMaterial()
                        .equals(nomeMaterial)) {

                    idMaterial
                            = material.getIdMaterial();
                }
            }

            double quantidade
                    = Double.parseDouble(
                            txt_quantidade.getText()
                    );

            ProdutoMaterial pm
                    = new ProdutoMaterial();

            pm.setIdProduto(idProduto);

            pm.setIdMaterial(idMaterial);

            pm.setQuantidadeMaterial(
                    quantidade
            );

            ProdutoMaterialDAO dao
                    = new ProdutoMaterialDAO();

            dao.cadastrar(pm);

            JOptionPane.showMessageDialog(
                    null,
                    "Relacionamento cadastrado com sucesso!"
            );

            txt_quantidade.setText("");

            combo_produto.setSelectedIndex(0);

            combo_material.setSelectedIndex(0);

            carregarTabela();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao cadastrar: "
                    + e.getMessage()
            );
        }
    }

    public void excluirRelacionamento() {

        try {

            int linhaSelecionada
                    = tabela_produto_material.getSelectedRow();

            if (linhaSelecionada == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Selecione um registro."
                );

                return;
            }

            int idProdutoMaterial
                    = Integer.parseInt(
                            tabela_produto_material
                                    .getValueAt(
                                            linhaSelecionada,
                                            0
                                    )
                                    .toString()
                    );

            ProdutoMaterialDAO dao
                    = new ProdutoMaterialDAO();

            dao.excluir(idProdutoMaterial);

            JOptionPane.showMessageDialog(
                    null,
                    "Relacionamento excluído com sucesso!"
            );

            carregarTabela();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao excluir: "
                    + e.getMessage()
            );
        }
    }

    public void selecionarRegistro() {

        try {

            int linha
                    = tabela_produto_material
                            .getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Selecione um registro."
                );

                return;
            }

            idSelecionado
                    = Integer.parseInt(
                            tabela_produto_material
                                    .getValueAt(linha, 0)
                                    .toString()
                    );

            txt_quantidade.setText(
                    tabela_produto_material
                            .getValueAt(linha, 3)
                            .toString()
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    public void editarRelacionamento() {

        try {

            if (idSelecionado == 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Selecione um registro primeiro."
                );

                return;
            }

            ProdutoDAO produtoDAO
                    = new ProdutoDAO();

            MaterialDAO materialDAO
                    = new MaterialDAO();

            ArrayList<Produto> produtos
                    = produtoDAO.listarProdutos();

            ArrayList<Material> materiais
                    = materialDAO.listarMateriais();

            String nomeProduto
                    = combo_produto
                            .getSelectedItem()
                            .toString();

            String nomeMaterial
                    = combo_material
                            .getSelectedItem()
                            .toString();

            int idProduto = 0;
            int idMaterial = 0;

            for (Produto p : produtos) {

                if (p.getNomeProduto()
                        .equals(nomeProduto)) {

                    idProduto
                            = p.getIdProduto();
                }
            }

            for (Material m : materiais) {

                if (m.getNomeMaterial()
                        .equals(nomeMaterial)) {

                    idMaterial
                            = m.getIdMaterial();
                }
            }

            ProdutoMaterial pm
                    = new ProdutoMaterial();

            pm.setIdProdutoMaterial(
                    idSelecionado
            );

            pm.setIdProduto(
                    idProduto
            );

            pm.setIdMaterial(
                    idMaterial
            );

            pm.setQuantidadeMaterial(
                    Double.parseDouble(
                            txt_quantidade.getText()
                    )
            );

            ProdutoMaterialDAO dao
                    = new ProdutoMaterialDAO();

            dao.atualizar(pm);

            JOptionPane.showMessageDialog(
                    null,
                    "Atualizado com sucesso!"
            );

            carregarTabela();

            idSelecionado = 0;

            txt_quantidade.setText("");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_titulo = new javax.swing.JLabel();
        lbl_produto = new javax.swing.JLabel();
        combo_produto = new javax.swing.JComboBox<>();
        lbl_material = new javax.swing.JLabel();
        combo_material = new javax.swing.JComboBox<>();
        lbl_quantidade = new javax.swing.JLabel();
        txt_quantidade = new javax.swing.JTextField();
        btn_adicionar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_produto_material = new javax.swing.JTable();
        btn_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_titulo.setText("COMPOSIÇÃO DOS PRODUTOS");

        lbl_produto.setText("Produto:");

        combo_produto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_material.setText("Material:");

        combo_material.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbl_quantidade.setText("Quantidade:");

        btn_adicionar.setText("Adicionar");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_voltar.setText("Voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });

        tabela_produto_material.setModel(new javax.swing.table.DefaultTableModel(
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
        tabela_produto_material.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_produto_materialMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_produto_material);

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btn_voltar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_produto)
                            .addGap(18, 18, 18)
                            .addComponent(combo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lbl_material)
                            .addGap(18, 18, 18)
                            .addComponent(combo_material, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_quantidade)
                        .addGap(18, 18, 18)
                        .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_adicionar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_excluir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_editar)
                    .addComponent(lbl_titulo))
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_produto)
                    .addComponent(combo_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_material)
                    .addComponent(combo_material, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_quantidade)
                    .addComponent(txt_quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_adicionar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_editar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btn_voltar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed

        adicionarRelacionamento();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluirRelacionamento();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        TelaMenuPrincipal tela
                = new TelaMenuPrincipal();

        tela.setVisible(true);

        dispose();
    }//GEN-LAST:event_btn_voltarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        editarRelacionamento();
    }//GEN-LAST:event_btn_editarActionPerformed

    private void tabela_produto_materialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_produto_materialMouseClicked
        selecionarRegistro();
    }//GEN-LAST:event_tabela_produto_materialMouseClicked

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
            java.util.logging.Logger.getLogger(TelaProdutoMaterial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProdutoMaterial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProdutoMaterial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProdutoMaterial.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProdutoMaterial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JComboBox<String> combo_material;
    private javax.swing.JComboBox<String> combo_produto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_material;
    private javax.swing.JLabel lbl_produto;
    private javax.swing.JLabel lbl_quantidade;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTable tabela_produto_material;
    private javax.swing.JTextField txt_quantidade;
    // End of variables declaration//GEN-END:variables
}
