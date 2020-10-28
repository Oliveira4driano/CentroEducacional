package view;

import DAO.UsuarioDAO;

import model.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Acesso;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jonathan
 */
public class UsuarioCadastroTela extends javax.swing.JFrame {

    private Usuario usuario;
    protected Acesso acesso;
       
   
    public UsuarioCadastroTela(Acesso acesso) {
        usuario = new Usuario();
        initComponents();
        this.acesso = acesso;

        setLocationRelativeTo(this);
       // comboGrp.setVisible(false);
        
    }
    

     private void preencherDados(){
        usuario.setLogin(campoNome.getText());
       // String senha = );
        
       if(campoSenha.getPassword().length < 8) {
           JOptionPane.showMessageDialog(this,"informe uma senha de 8 digitos! ");
       } else {
           usuario.setSenha(String.valueOf(campoSenha.getPassword()));
       }
        if(jCheckBoxSelecionar.isSelected()){
            usuario.setSelecionar("Y");          
        }
        else{
            usuario.setSelecionar("N");
        }
        if(jCheckBoxInserir.isSelected()){
            usuario.setInserir("Y");
        }else{
            usuario.setInserir("N");
        }
        if(jCheckBoxAlterar.isSelected()){
            usuario.setAlterar("Y");
        }else{
            usuario.setAlterar("N");
        }
        if(jCheckBoxExcluir.isSelected()){
            usuario.setDeletar("Y");
        }else{
            usuario.setDeletar("N");
        }
        
        //usuario.getGrupo().setCodigo(Character.getNumericValue(comboGrp.getSelectedItem().toString().charAt(0)));
    }
    
    private void inserirBD() throws Exception{
       UsuarioDAO dao= new UsuarioDAO();
       dao.inserir(usuario, acesso);  
    }
    
    private void limparDados(){
        campoNome.setText("");
        campoSenha.setText("");
              
    }
    private void salvar() throws Exception{
        preencherDados();
        inserirBD();
        limparDados();
    }
      private void selecaoTodos() {
        jCheckBoxSelecionar.isSelected();
    }
    
    private void liberarTela(){
        dispose();
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

        botaoCadastrar = new javax.swing.JButton();
        campoNome = new javax.swing.JTextField();
        BotaoCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBoxSelecionar = new javax.swing.JCheckBox();
        jCheckBoxInserir = new javax.swing.JCheckBox();
        jCheckBoxAlterar = new javax.swing.JCheckBox();
        jCheckBoxExcluir = new javax.swing.JCheckBox();
        campoSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro Usuário");

        botaoCadastrar.setBackground(new java.awt.Color(38, 38, 177));
        botaoCadastrar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        botaoCadastrar.setForeground(java.awt.Color.white);
        botaoCadastrar.setText("Cadastrar");
        botaoCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadastrarActionPerformed(evt);
            }
        });

        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        BotaoCancelar.setBackground(new java.awt.Color(38, 38, 177));
        BotaoCancelar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        BotaoCancelar.setForeground(java.awt.Color.white);
        BotaoCancelar.setText("Cancelar");
        BotaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotaoCancelarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(38, 61, 177));
        jLabel2.setText("*Mínimo 8 caracteres");

        jPanel1.setBackground(new java.awt.Color(28, 28, 163));

        jLabel3.setBackground(java.awt.Color.white);
        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Cadastro Usuário");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(25, 25, 117));
        jLabel6.setText("Nome");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(25, 25, 117));
        jLabel7.setText("Senha");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissão"));
        jPanel2.setForeground(new java.awt.Color(38, 61, 177));

        jCheckBoxSelecionar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jCheckBoxSelecionar.setForeground(new java.awt.Color(38, 38, 177));
        jCheckBoxSelecionar.setText("Selecionar");

        jCheckBoxInserir.setForeground(new java.awt.Color(38, 38, 177));
        jCheckBoxInserir.setText("Inserir");

        jCheckBoxAlterar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jCheckBoxAlterar.setForeground(new java.awt.Color(38, 38, 177));
        jCheckBoxAlterar.setText("Alterar");

        jCheckBoxExcluir.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jCheckBoxExcluir.setForeground(new java.awt.Color(38, 38, 177));
        jCheckBoxExcluir.setText("Excluir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jCheckBoxSelecionar)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxInserir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxAlterar)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxExcluir)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxSelecionar)
                    .addComponent(jCheckBoxInserir)
                    .addComponent(jCheckBoxAlterar)
                    .addComponent(jCheckBoxExcluir)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(BotaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(campoSenha, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(campoNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(BotaoCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadastrarActionPerformed
        try {
            salvar();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioCadastroTela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoCadastrarActionPerformed

    private void BotaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotaoCancelarActionPerformed
            liberarTela();        // TODO add your handling code here:
    }//GEN-LAST:event_BotaoCancelarActionPerformed

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

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
//            java.util.logging.Logger.getLogger(UsuarioCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(UsuarioCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(UsuarioCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(UsuarioCadastroTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
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
    private javax.swing.JButton BotaoCancelar;
    private javax.swing.JButton botaoCadastrar;
    private javax.swing.JTextField campoNome;
    private javax.swing.JPasswordField campoSenha;
    private javax.swing.JCheckBox jCheckBoxAlterar;
    private javax.swing.JCheckBox jCheckBoxExcluir;
    private javax.swing.JCheckBox jCheckBoxInserir;
    private javax.swing.JCheckBox jCheckBoxSelecionar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

  
}
