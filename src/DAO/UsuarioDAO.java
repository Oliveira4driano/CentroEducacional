/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import util.BDException;
import util.ControlaConexao;
import model.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Acesso;
import util.LogErro;

/**
 *
 * @author Adriano
 */
public class UsuarioDAO {
    private LogErro err = new LogErro();
     private List<Usuario> usuarios;//1
    // private Acesso acesso;
     
       
    public void inserir(Usuario usuario, Acesso acesso) throws BDException{     
        try {
            Connection conexao = ControlaConexao.getConexao(acesso);
            String query ="{Call sp_criarUsuario(?,?,?,?,?,?,?)}";
            CallableStatement instrucao = conexao.prepareCall(query);
            instrucao.setString(1, usuario.getLogin());
            instrucao.setString(2, usuario.getSenha());
            instrucao.setString(3, acesso.getHost());
            instrucao.setString(4, usuario.getSelecionar());
            instrucao.setString(5, usuario.getInserir());
            instrucao.setString(6, usuario.getAlterar());
            instrucao.setString(7, usuario.getDeletar());
           // instrucao.setInt(3, usuario.getGrupo().getCodigo());
            instrucao.executeQuery();
            conexao.close();
            JOptionPane.showMessageDialog(null, "Cadastrado "+usuario.getLogin(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
           // JOptionPane.showMessageDialog(null, "AVISO!", "Registro Salvo com Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException error) {
           err.logErro(error);    
            throw new BDException(error);
        }        
    }
      public List<Usuario> listar(Acesso acesso) throws BDException{
        Connection conexao = null;
        CallableStatement instrucao = null;    
       try {
           ResultSet resultados;
           
           usuarios = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_listarUsuario()}";
           instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Usuario  usuarioSaida = new Usuario(); 
              // usuarioSaida.setInserir((resultados.getString("password_last_changed")));
               usuarioSaida.setLogin(resultados.getString("usuario"));
               usuarioSaida.setSelecionar(resultados.getString("Select_priv"));
               usuarioSaida.setInserir(resultados.getString("Insert_priv"));
               usuarioSaida.setAlterar(resultados.getString("Update_priv"));
               usuarioSaida.setDeletar(resultados.getString("Delete_priv"));
               usuarios.add(usuarioSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);
             throw new BDException(e);  
        }       
        finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }
        return usuarios;
        //return turmas;
        
        
   }
    
     
    public void alterar(Usuario usuario, Acesso acesso) throws BDException{     
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{Call sp_alterarUsuario(?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, usuario.getLogin());
            instrucao.setString(2, usuario.getSenha());
            instrucao.setString(3, acesso.getHost());
            instrucao.setString(4, usuario.getSelecionar());
            instrucao.setString(5, usuario.getInserir());
            instrucao.setString(6, usuario.getAlterar());
            instrucao.setString(7, usuario.getDeletar());
            instrucao.execute();
            
            
            JOptionPane.showMessageDialog(null,  "Registro Alterado com Sucesso!","Aviso!", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e);  
        }  finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }         
    }
         
 
    
   public void deletar(Usuario usuario, Acesso acesso) throws BDException{
       try {
           Connection conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_excluirUsuario(?,?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1, usuario.getLogin());
           instrucao.setString(2, acesso.getHost());
           instrucao.execute();
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e);  
        }    
   }
    
    
    
    
      
   public List<Usuario> pesquisar(Usuario usuario, Acesso acesso) throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;     
       try {
           ResultSet resultados;
           usuarios = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarUsuario(?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,usuario.getLogin());
           resultados = instrucao.executeQuery();
           while (resultados.next()) {
               Usuario  usuarioSaida = new Usuario(); 
               usuarioSaida.setLogin(resultados.getString("User"));
               usuarioSaida.setSelecionar(resultados.getString("Select_priv"));
               usuarioSaida.setInserir(resultados.getString("Insert_priv"));
               usuarioSaida.setAlterar(resultados.getString("Update_priv"));
               usuarioSaida.setDeletar(resultados.getString("Delete_priv"));
               usuarios.add(usuarioSaida);
           }
           
       } catch (SQLException e) {
           err.logErro(e);
           throw new BDException(e); 
       }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
       }       
       return usuarios;
        
   }
     
}





  /*
public Grupo buscarPorId(int codigo){
       Grupo g = null;
       try {
           Connection conexao = ControlaConexao.getConnection();
           String query ="{call sp_buscarPorId(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setInt(1, codigo);
           ResultSet results = instrucao.executeQuery();
           while(results.next()){
               Grupo grupo = new Grupo();
               System.out.println(results.getInt("codigo"));
               grupo.setCodigo(results.getInt("codigo"));
               grupo.setDescricao(results.getString("descricao"));
               grupo.getPermissao().setStatus(results.getString("permissao"));
               
           }
           
       } catch (SQLException e) {
           g=null;
       }
        return g;
       
   }
        String inserirProduto = "insert into grupo (grucodigo, grudescricao,grupercodigo)values(?,?,?);";       
        try{
            Connection conexao = ControlaConexao.getConnection();
            PreparedStatement instrucao = conexao.prepareStatement(inserirProduto);
            instrucao.setString(1, "0");
            instrucao.setString(2, grupo.getDescricao());
            instrucao.setInt(3,grupo.getPermissao().getStatus());
            instrucao.execute();
            conexao.close();
        } catch(SQLException e){
            throw new BDException(e);
        } */
