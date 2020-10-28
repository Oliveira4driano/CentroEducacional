/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.Acesso;
import java.io.File;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author adriano
 */
public class ControlaConexao implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    //private static final String DRIVER = "com.mysql.jdbc.Driver";
    public static String URL;
    public static Connection con = null;
   // private Acesso acesso;
      
    //private final  String zona = "?serverTimezone=UTC";

    public ControlaConexao() {
        
    }

    public static Connection getConexaoUser(Acesso acesso) throws ClassNotFoundException   {
             acesso.setPorta("3306");
             acesso.setBanco("bdcentroeducacional");
             String user  = acesso.getUsuario();
             String senha = acesso.getSenha();            
            //URL = "jdbc:mysql:"+File.separator+File.separator+acesso.getHost()+":"+acesso.getPorta()+File.separator+"mysql?serverTimezone=UTC";
            // URL = "jdbc:mysql:‪"+File.separator+File.separator+acesso.getHost()+":"+acesso.getPorta()+File.separator+"bdcentroeducacional?serverTimezone=UTC?noAccessToProcedureBodies=true";
            System.out.println("Conectando ao banco...");
            try { 
               // Class.forName("com.mysql.cj.jdbc.Driver");
               // Class.forName("com.mysql.jdbc.Driver");
               URL ="jdbc:mysql://"+acesso.getHost()+":"+acesso.getPorta()+"/"+acesso.getBanco()+"?noAccessToProcedureBodies=true&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
               con = DriverManager.getConnection( URL,user,senha);//              
                         
            } catch ( SQLException error) {
           // System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            JOptionPane.showMessageDialog(null, error, "AVISO", JOptionPane.INFORMATION_MESSAGE);
           // throw new RuntimeException(error);      
            
           } 
           return con; 
    }// valida usuario

    public static Connection getConexao(Acesso acesso) throws SQLException {
        //System.out.println("acesso"+acesso.getUsuario());
         URL = "jdbc:mysql://"+acesso.getHost()+":"+acesso.getPorta()+"/"+acesso.getBanco()+"?noAccessToProcedureBodies=true&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
      //  URL = "jdbc:mysql:"+File.separator+File.separator+acesso.getHost()+":"+acesso.getPorta()+File.separator+"bdcentroeducacional";
        return DriverManager.getConnection(URL,acesso.getUsuario(),acesso.getSenha());
    }// valida porta
        
        
    public static void fecharResultSet(ResultSet resultados) {
        if(resultados!=null){
            try{
                resultados.close();
            }catch(SQLException ex){
                ex.getMessage();
            }
        }
    }
    
    public static void fecharInstrucao(PreparedStatement instrucao) {
        if(instrucao!=null){
            try{
                instrucao.close();
            }catch(SQLException ex){
                ex.getMessage();
            }
        }
    }
    
    public static void fecharConexao(Connection conexao) {
        if(conexao!=null){
            try{
                conexao.close();
            }catch(SQLException ex){
                ex.getMessage();
            }
        }
    }
       
    
}
