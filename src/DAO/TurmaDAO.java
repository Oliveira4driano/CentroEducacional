/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import util.BDException;
import util.ControlaConexao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Turma;
import util.LogErro;

/**
 *
 * @author Adriano
 */
public class TurmaDAO {
    private LogErro err = new LogErro();
    private List<Turma> turmas;
    //private Acesso acesso;
    //Connection conexao = null;
      //  CallableStatement instrucao = null;
    
    public void inserir(Turma turma, Acesso acesso) throws BDException{     
        Connection conexao = null;
        CallableStatement instrucao = null;
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_inserirTurma(?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, turma.getTurma());
            //nstrucao.setString(2,estado.getSigla());
            instrucao.executeUpdate();
            //ControlaConexao.fecharInstrucao(instrucao);
            //conexao.close();
            JOptionPane.showMessageDialog(null, "Cadastrado "+turma.getTurma(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException error) {
            err.logErro(error);
            throw new BDException(error); 
        }
        finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
    public List<Turma> listar(Acesso acesso) throws BDException{
        Connection conexao = null;
        CallableStatement instrucao = null;    
       try {
           ResultSet resultados;
           
           turmas = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_listarTurma()}";
           instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Turma  turmaSaida = new Turma(); 
               turmaSaida.setId((resultados.getInt("turcodigo")));
               turmaSaida.setTurma(resultados.getString("turdescricao"));
               turmas.add(turmaSaida);
           }
          // return turmas;
        } catch (SQLException e) {
           err.logErro(e);
             throw new BDException(e);  
        }       
        finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }
        return turmas;
        
        
   }
       public List<Turma> pesquisar(Turma turma, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           turmas = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarTurma(?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,turma.getTurma());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Turma  turmaSaida = new Turma(); 
               turmaSaida.setId(resultados.getInt("turcodigo"));
               turmaSaida.setTurma(resultados.getString("turdescricao"));
               turmas.add(turmaSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e); 
      //  }
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return turmas;
       
        
   }
    

    public void alterar(Turma turma, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarTurma(?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setLong(1, turma.getId());
            instrucao.setString(2,turma.getTurma());
            
            instrucao.execute();
           // conexao.close();
            
        } catch (SQLException e) {
            err.logErro(e);
                throw new BDException(e);  
        
        }finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }  
    }    
        
    
    public void deletar(Turma turma, Acesso acesso) throws BDException{
       try {
           Connection conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_excluirTurma(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1, turma.getTurma());
           instrucao.execute();
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e);  
        }    
   }

    
}  
    
    

