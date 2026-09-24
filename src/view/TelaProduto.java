package view;

import controller.ProdutoController;

import dao.ProdutoDAO;
import model.Produto;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaProduto extends javax.swing.JFrame {

    private int idProdutoSelecionado;

    public TelaProduto() {
        initComponents();

        carregarTabela();

    }

    public void limparCampos() {

        txt_nome.setText("");
        txt_descricao.setText("");
        txt_preco.setText("");
        txt_prazo.setText("");
        txt_empreendedor.setText("");
    }

    public void carregarTabela() {

        DefaultTableModel modelo = (DefaultTableModel) tabela_produtos.getModel();

        modelo.setRowCount(0);

        ProdutoDAO dao = new ProdutoDAO();

        ArrayList<Produto> lista = dao.listarProdutos();

        for (Produto p : lista) {

            modelo.addRow(new Object[]{
                p.getIdProduto(),
                p.getNomeProduto(),
                p.getDescricaoProduto(),
                p.getPrecoBaseProduto(),
                p.getPrazoProducaoProduto()

            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_nome = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        lbl_descricao = new javax.swing.JLabel();
        txt_descricao = new javax.swing.JTextField();
        lbl_preco = new javax.swing.JLabel();
        txt_preco = new javax.swing.JTextField();
        lbl_prazo = new javax.swing.JLabel();
        txt_prazo = new javax.swing.JTextField();
        lbl_empreendedor = new javax.swing.JLabel();
        txt_empreendedor = new javax.swing.JTextField();
        btn_salvar = new javax.swing.JButton();
        lbl_produto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_produtos = new javax.swing.JTable();
        btn_atualizar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_nome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nome.setText("Nome do Produto: ");

        txt_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeActionPerformed(evt);
            }
        });

        lbl_descricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_descricao.setText("Descrição:");

        txt_descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descricaoActionPerformed(evt);
            }
        });

        lbl_preco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_preco.setText("Preço:");

        lbl_prazo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_prazo.setText("Prazo:");

        txt_prazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_prazoActionPerformed(evt);
            }
        });

        lbl_empreendedor.setText("ID Empreendedor:");

        btn_salvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        lbl_produto.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_produto.setForeground(new java.awt.Color(153, 0, 0));
        lbl_produto.setText("Cadastro Produtos");

        tabela_produtos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOME", "DESCRICAO", "PRECO", "PRAZO"
            }
        ));
        tabela_produtos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_produtosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela_produtos);

        btn_atualizar.setText("Editar");
        btn_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(lbl_produto))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbl_nome)
                .addGap(6, 6, 6)
                .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbl_preco)
                .addGap(18, 18, 18)
                .addComponent(txt_preco, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lbl_descricao)
                .addGap(6, 6, 6)
                .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbl_prazo)
                .addGap(18, 18, 18)
                .addComponent(txt_prazo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_empreendedor)
                .addGap(5, 5, 5)
                .addComponent(txt_empreendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264)
                .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btn_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(350, 350, 350)
                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(btn_voltar))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbl_produto)
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nome)
                            .addComponent(lbl_preco))))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_descricao)
                    .addComponent(txt_descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_prazo)
                    .addComponent(txt_prazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_empreendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_empreendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_atualizar)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btn_salvar))
                    .addComponent(btn_excluir))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btn_voltar))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(602, 555));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeActionPerformed

    private void txt_descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descricaoActionPerformed

    private void txt_prazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_prazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_prazoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        try {

            String nome = txt_nome.getText();
            String descricao = txt_descricao.getText();
            double preco = Double.parseDouble(txt_preco.getText());
            String prazo = txt_prazo.getText();

            int idEmpreendedor = Integer.parseInt(txt_empreendedor.getText());

            ProdutoController controller = new ProdutoController();

            controller.salvarProduto(
                    nome,
                    descricao,
                    preco,
                    prazo,
                    idEmpreendedor
            );

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            carregarTabela();
            limparCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void tabela_produtosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_produtosMouseClicked
        int linha = tabela_produtos.getSelectedRow();

        idProdutoSelecionado = Integer.parseInt(tabela_produtos.getValueAt(linha, 0).toString());

        txt_nome.setText(tabela_produtos.getValueAt(linha, 1).toString());

        txt_descricao.setText(tabela_produtos.getValueAt(linha, 2).toString());

        txt_preco.setText(tabela_produtos.getValueAt(linha, 3).toString());

        txt_prazo.setText(tabela_produtos.getValueAt(linha, 4).toString());
    }//GEN-LAST:event_tabela_produtosMouseClicked

    private void btn_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizarActionPerformed

        try {

            Produto produto = new Produto();

            produto.setIdProduto(idProdutoSelecionado);
            produto.setNomeProduto(txt_nome.getText());
            produto.setDescricaoProduto(txt_descricao.getText());
            produto.setPrecoBaseProduto(Double.parseDouble(txt_preco.getText()));
            produto.setPrazoProducaoProduto(txt_prazo.getText());

            ProdutoDAO dao = new ProdutoDAO();

            dao.atualizarProduto(produto);

            JOptionPane.showMessageDialog(null, "Produto atualizado!");

            carregarTabela();
            limparCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_atualizarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        try {

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir este produto?");

            if (resposta == JOptionPane.YES_OPTION) {

                ProdutoDAO dao = new ProdutoDAO();

                dao.excluirProduto(idProdutoSelecionado);

                JOptionPane.showMessageDialog(null, "Produto excluído!");

                carregarTabela();

                limparCampos();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_excluirActionPerformed

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
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atualizar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_descricao;
    private javax.swing.JLabel lbl_empreendedor;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_prazo;
    private javax.swing.JLabel lbl_preco;
    private javax.swing.JLabel lbl_produto;
    private javax.swing.JTable tabela_produtos;
    private javax.swing.JTextField txt_descricao;
    private javax.swing.JTextField txt_empreendedor;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_prazo;
    private javax.swing.JTextField txt_preco;
    // End of variables declaration//GEN-END:variables
}
