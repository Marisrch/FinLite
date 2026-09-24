package view;

import dao.EmpreendedorDAO;

import model.Empreendedor;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class TelaEmpreendedor extends javax.swing.JFrame {

    private int idSelecionado = -1;

    public TelaEmpreendedor() {

        initComponents();

        configurarTabela();

        listarEmpreendedores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_tela_empreendedor = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        lbl_cpf = new javax.swing.JLabel();
        txt_cpf = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        lbl_telefone = new javax.swing.JLabel();
        txt_telefone = new javax.swing.JTextField();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_voltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_empreendedor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(null);

        lbl_tela_empreendedor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tela_empreendedor.setForeground(new java.awt.Color(153, 0, 0));
        lbl_tela_empreendedor.setText("Cadastro Empreendedor");
        jPanel1.add(lbl_tela_empreendedor);
        lbl_tela_empreendedor.setBounds(150, 20, 205, 25);

        lbl_nome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nome.setText("Nome:");
        jPanel1.add(lbl_nome);
        lbl_nome.setBounds(30, 70, 40, 16);
        jPanel1.add(txt_nome);
        txt_nome.setBounds(90, 70, 270, 22);

        lbl_cpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_cpf.setText("CPF:");
        jPanel1.add(lbl_cpf);
        lbl_cpf.setBounds(30, 160, 23, 16);
        jPanel1.add(txt_cpf);
        txt_cpf.setBounds(90, 130, 161, 22);

        lbl_email.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_email.setText("Email:");
        jPanel1.add(lbl_email);
        lbl_email.setBounds(30, 100, 32, 16);
        jPanel1.add(txt_email);
        txt_email.setBounds(90, 100, 270, 22);

        lbl_telefone.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_telefone.setText("Telefone:");
        jPanel1.add(lbl_telefone);
        lbl_telefone.setBounds(30, 130, 60, 16);
        jPanel1.add(txt_telefone);
        txt_telefone.setBounds(90, 160, 161, 22);

        btn_cadastrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_cadastrar.setText("Cadastrar");
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_cadastrar);
        btn_cadastrar.setBounds(30, 230, 90, 23);

        btn_editar.setText("Editar");
        btn_editar.setActionCommand("🖋️ Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_editar);
        btn_editar.setBounds(390, 170, 80, 23);

        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_excluir);
        btn_excluir.setBounds(390, 200, 80, 23);

        btn_voltar.setText("Voltar");
        btn_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voltarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_voltar);
        btn_voltar.setBounds(210, 380, 72, 23);

        tabela_empreendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CPF", "Email", "Telefone"
            }
        ));
        tabela_empreendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_empreendedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_empreendedor);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(30, 270, 452, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        cadastrarEmpreendedor();
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void tabela_empreendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_empreendedorMouseClicked
        int linhaSelecionada = tabela_empreendedor.getSelectedRow();

        DefaultTableModel modelo = (DefaultTableModel) tabela_empreendedor.getModel();

        idSelecionado = Integer.parseInt(modelo.getValueAt(linhaSelecionada, 0).toString());

        txt_nome.setText(modelo.getValueAt(linhaSelecionada, 1).toString());

        txt_cpf.setText(modelo.getValueAt(linhaSelecionada, 2).toString());

        txt_email.setText(modelo.getValueAt(linhaSelecionada, 3).toString());

        txt_telefone.setText(modelo.getValueAt(linhaSelecionada, 4).toString());
    }//GEN-LAST:event_tabela_empreendedorMouseClicked

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        editarEmpreendedor();
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluirEmpreendedor();
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voltarActionPerformed
        TelaMenuPrincipal tela = new TelaMenuPrincipal();

        tela.setVisible(true);

        dispose();
    }//GEN-LAST:event_btn_voltarActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(TelaEmpreendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEmpreendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEmpreendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEmpreendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEmpreendedor().setVisible(true);
            }
        });
    }

    public void configurarTabela() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Email");
        modelo.addColumn("Telefone");

        tabela_empreendedor.setModel(modelo);
    }

    public void listarEmpreendedores() {

        try {

            EmpreendedorDAO dao = new EmpreendedorDAO();

            ArrayList<Empreendedor> lista = dao.listarEmpreendedores();

            DefaultTableModel modelo = (DefaultTableModel) tabela_empreendedor.getModel();

            modelo.setRowCount(0);

            for (Empreendedor e : lista) {

                modelo.addRow(new Object[]{
                    e.getIdEmpreendedor(),
                    e.getNomeEmpreendedor(),
                    e.getCpfEmpreendedor(),
                    e.getEmailEmpreendedor(),
                    e.getTelefoneEmpreendedor()
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
        }
    }

    public void cadastrarEmpreendedor() {

        try {

            Empreendedor empreendedor = new Empreendedor();

            empreendedor.setNomeEmpreendedor(txt_nome.getText());
            empreendedor.setCpfEmpreendedor(txt_cpf.getText());
            empreendedor.setEmailEmpreendedor(txt_email.getText());
            empreendedor.setTelefoneEmpreendedor(txt_telefone.getText());

            EmpreendedorDAO dao = new EmpreendedorDAO();

            dao.cadastrarEmpreendedor(empreendedor);

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

            listarEmpreendedores();

            limparCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void limparCampos() {

        txt_nome.setText("");
        txt_cpf.setText("");
        txt_email.setText("");
        txt_telefone.setText("");

        idSelecionado = -1;
    }

    public void editarEmpreendedor() {

        try {

            if (idSelecionado == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um empreendedor!");

                return;
            }

            Empreendedor empreendedor = new Empreendedor();

            empreendedor.setIdEmpreendedor(idSelecionado);
            
            empreendedor.setNomeEmpreendedor(txt_nome.getText());
            empreendedor.setCpfEmpreendedor(txt_cpf.getText());
            empreendedor.setEmailEmpreendedor(txt_email.getText());
            empreendedor.setTelefoneEmpreendedor(txt_telefone.getText());

            EmpreendedorDAO dao = new EmpreendedorDAO();

            dao.editarEmpreendedor(empreendedor);

            JOptionPane.showMessageDialog(null, "Editado com sucesso!");

            listarEmpreendedores();

            limparCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e.getMessage());
        }
    }

    public void excluirEmpreendedor() {

        try {

            if (idSelecionado == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um empreendedor!");

                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {

                EmpreendedorDAO dao = new EmpreendedorDAO();

                dao.excluirEmpreendedor(idSelecionado);

                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

                listarEmpreendedores();

                limparCampos();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage() );
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_voltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_cpf;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_tela_empreendedor;
    private javax.swing.JLabel lbl_telefone;
    private javax.swing.JTable tabela_empreendedor;
    private javax.swing.JTextField txt_cpf;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_telefone;
    // End of variables declaration//GEN-END:variables
}
