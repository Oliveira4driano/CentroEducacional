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
import model.Turno;
import util.LogErro;

/**
 *
 * @author Adriano
 */
public class TurnoDAO {
    private LogErro err = new LogErro();
    private List<Turno> turnos;

 

    public List<Turno> listar(Acesso acesso) throws BDException{
        Connection conexao = null;
        CallableStatement instrucao = null;    
       try {
           ResultSet resultados;
           
           turnos = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_listarTurno()}";
           instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Turno  turnoSaida = new Turno(); 
               turnoSaida.setId((resultados.getInt("tnocodigo")));
               turnoSaida.setTurno(resultados.getString("tnonome"));
               turnos.add(turnoSaida);
           }
          // return turnos;
        } catch (SQLException e) {
           err.logErro(e);
             throw new BDException(e);  
        }       
        finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }
        return turnos;
        
        
   }
       public List<Turno> pesquisar(Turno turno, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           turnos = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarTurno(?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,turno.getTurno());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Turno  turnoSaida = new Turno(); 
               turnoSaida.setId(resultados.getInt("turcodigo"));
               turnoSaida.setTurno(resultados.getString("turdescricao"));
               turnos.add(turnoSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e); 
      //  }
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return turnos;
       
        
   }
    

    public void alterar(Turno turno, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarTurno(?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setLong(1, turno.getId());
            instrucao.setString(2,turno.getTurno());
            
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
        
    
    public void deletar(Turno turno, Acesso acesso) throws BDException{
       try {
           Connection conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_excluirTurno(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           instrucao.setString(1, turno.getTurno());
           instrucao.execute();
        } catch (SQLException e) {
            err.logErro(e);
            throw new BDException(e);  
        }    
   }

    
}  
    
    

