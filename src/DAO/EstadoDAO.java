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
import model.Estado;
import model.Turma;
import util.LogErro;

/**
 *
 * @author Adriano
 */
public class EstadoDAO {
    private LogErro err = new LogErro();
    private List<Estado> estados;

    public List<Estado> listar(Acesso acesso) throws BDException{
        Connection conexao = null;
        CallableStatement instrucao = null;    
       try {
           ResultSet resultados;
           
           estados = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_listarEstado()}";
           instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Estado  estadoSaida = new Estado(); 
               estadoSaida.setId((resultados.getInt("estcodigo")));
               estadoSaida.setEstado(resultados.getString("estnome"));
               estados.add(estadoSaida);
           }
    
        } catch (SQLException e) {
           err.logErro(e);
             throw new BDException(e);  
        }       
        finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }
        return estados;
        
        
   }
//       public List<Turma> pesquisar(Turma turma, Acesso acesso)throws BDException{
//       Connection conexao = null;
//       CallableStatement instrucao = null;
//     
//       try {
//           ResultSet resultados;          
//           turmas = new ArrayList<>();
//           conexao = ControlaConexao.getConexao(acesso);
//           String query = "{call sp_pesquisarTurma(?)}";
//           instrucao = conexao.prepareCall(query);
//           instrucao.setString(1,turma.getTurma());
//           resultados = instrucao.executeQuery();
//           
//           
//           while (resultados.next()) {
//               Turma  turmaSaida = new Turma(); 
//               turmaSaida.setId(resultados.getInt("turcodigo"));
//               turmaSaida.setTurma(resultados.getString("turdescricao"));
//               turmas.add(turmaSaida);
//           }
//           
//        } catch (SQLException e) {
//            err.logErro(e);
//            throw new BDException(e); 
//      //  }
//        }finally{
//            ControlaConexao.fecharInstrucao(instrucao);
//            ControlaConexao.fecharConexao(conexao);
//        }  
//       return turmas;
//       
//        
//   }
//    
//
//    public void alterar(Turma turma, Acesso acesso) throws BDException{
//         Connection conexao = null;
//        CallableStatement instrucao = null;   
//        try {
//            conexao = ControlaConexao.getConexao(acesso);
//            String query ="{call sp_alterarTurma(?,?)}";
//            instrucao = conexao.prepareCall(query);
//            instrucao.setLong(1, turma.getId());
//            instrucao.setString(2,turma.getTurma());
//            
//            instrucao.execute();
//           // conexao.close();
//            
//        } catch (SQLException e) {
//            err.logErro(e);
//                throw new BDException(e);  
//        
//        }finally{
//           ControlaConexao.fecharInstrucao(instrucao);
//           ControlaConexao.fecharConexao(conexao);
//        }  
//        }    
//        
//    
//    public void deletar(Turma turma, Acesso acesso) throws BDException{
//       try {
//           Connection conexao = ControlaConexao.getConexao(acesso);
//           String query = "{call sp_excluirTurma(?)}";
//           CallableStatement instrucao = conexao.prepareCall(query);
//           instrucao.setString(1, turma.getTurma());
//           instrucao.execute();
//        } catch (SQLException e) {
//            err.logErro(e);
//            throw new BDException(e);  
//        }    
//   }
//
//    
}  
    
   
