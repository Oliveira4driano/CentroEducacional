package view;

import DAO.TurmaDAO;
import DAO.UsuarioDAO;

import model.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Turma;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class TurmaAlteraTela extends javax.swing.JFrame {

    private Turma turma;
    protected Acesso acesso;
    private TurmaDAO turmaDAO;
       
   
    public TurmaAlteraTela(Turma turma, Acesso acesso) {
        this();
        this.acesso = acesso;
        this.turma = turma;
        preencherDados();

        
       // comboGrp.setVisible(false);
        
    }

    public TurmaAlteraTela() {
        initComponents();
        setLocationRelativeTo(this);
    }
    

     private void preencherDados(){
       // turma.setTurma(campoNome.getText());
        campoNome.setText(turma.getTurma());
      //  usuario.setSenha(campoSenha.getText());
        //usuario.getGrupo().setCodigo(Character.getNumericValue(comboGrp.getSelectedItem().toString().charAt(0)));
    }
    
    private void inserirBD() throws Exception{
       TurmaDAO dao= new TurmaDAO();
       dao.alterar(turma, acesso);  
    }
    
    private void alterar() throws Exception{
        // turma = new Turma();
        turma.setTurma(campoNome.getText());      
        inserirBD();
        JOptionPane.showMessageDialog(this,"Registro Alterado com Sucesso!");
        
    }

    
    private void deletar(){
        turmaDAO = new TurmaDAO();
            
        try{
            turmaDAO.deletar(turma, acesso);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Ocorreu um erro de banco de dados"+e);
        }

    }
    
//    public void carregaGrupos()  
//    {  
//        try{
//            Connection conexao = ControlaConexao.getConexaoUser(acesso);
//            String query ="{call sp_listarGrupo}";
//            CallableStatement instrucao = conexao.prepareCall(query);
//            ResultSet results = instrucao.executeQuery();
//            while(results.next()){
//                comboGrp.addItem(results.getInt("grucodigo") + " - " + results.getString("grudescricao"));
//            }
//                
//        } catch (SQLException e) {
//            System.out.println("ERRO: " + e.getMessage());
//        }
//    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoAlterar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        BotaoVoltar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botaoExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterar Turma");

        botaoAlterar.setBackground(new java.awt.Color(38, 38, 177));
        botaoAlterar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoAlterar.setForeground(java.awt.Color.white);
        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        BotaoVoltar.setBackground(new java.awt.Color(38, 38, 177));
        BotaoVoltar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        BotaoVoltar.setForeground(java.awt.Color.white);
        BotaoVoltar.setText("Voltar");
        BotaoVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoVoltarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 25, 117));
        jLabel5.setText("Nome");

        jPanel1.setBackground(new java.awt.Color(28, 28, 163));

        jLabel1.setBackground(java.awt.Color.white);
        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Alterar Turma");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        botaoExcluir.setBackground(new java.awt.Color(240, 5, 5));
        botaoExcluir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoExcluir.setForeground(java.awt.Color.white);
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotaoVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotaoVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        try {
            alterar();
            new TurmaPesquisaTela(acesso).setVisible(true);
            dispose();
        } catch (Exception ex) {
            Logger.getLogger(TurmaAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void BotaoVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoVoltarActionPerformed
        try {
            new TurmaPesquisaTela(acesso).setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(TurmaAlteraTela.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_BotaoVoltarActionPerformed

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        int resposta=JOptionPane.showConfirmDialog(this,"Deseja realmente excluir "+turma.getTurma()+"?",
                                                 "Exclusão de Turma",JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.QUESTION_MESSAGE);
        if(resposta==JOptionPane.YES_OPTION)
        try{
            deletar();
            new TurmaPesquisaTela(acesso).setVisible(true);
            JOptionPane.showMessageDialog(this, turma.getTurma()+" excluido") ;
            dispose();
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Erro:"+ e.getMessage());
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TurmaCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TurmaCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TurmaCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TurmaCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//      
//        //</editor-fold>
//        //<
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                UsuarioCadastroTela tela = new UsuarioCadastroTela(Acesso acesso);
////                tela.setLocationRelativeTo(null);
////                tela.setVisible(true);
////            }
////        });
//        //</editor-fold>
//            //</editor-fold>
//
//
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                UsuarioCadastroTela tela = new UsuarioCadastroTela(Acesso acesso);
////                tela.setLocationRelativeTo(null);
////                tela.setVisible(true);
////            }
////        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotaoVoltar;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JTextField campoNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
