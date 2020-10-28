/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import util.BDException;
import util.ControlaConexao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Aluno;
import model.Responsavel;
import sun.java2d.opengl.OGLRenderQueue;
import util.LogErro;

/**
 *
 * @author Adriano
 */
public class AlunoDAO {
    private List<Aluno> alunos;
    private LogErro err = new LogErro();
    //private Acesso acesso;
    //Connection conexao = null;
      //  CallableStatement instrucao = null;
    
    public void inserir(Aluno aluno, Acesso acesso) throws BDException{     
        Connection conexao = null;
        CallableStatement instrucao = null;
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_inserirAluno(?,?,?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, aluno.getNome());
            instrucao.setString(2,  aluno.getDatanascimento());
            instrucao.setString(3, aluno.getSexo());
            instrucao.setBytes( 4, aluno.getFoto());
            instrucao.setString(5, aluno.getNaturalidade());
            instrucao.setString(6, aluno.getStatus());
            instrucao.setLong(7, aluno.getEstado().getId());
            instrucao.setLong(8, aluno.getTurma().getId());
            instrucao.setLong(9, aluno.getTurno().getId());
           
            
            
            instrucao.executeUpdate();
            //ControlaConexao.fecharInstrucao(instrucao);
            //conexao.close();
            JOptionPane.showMessageDialog(null, aluno.getNome()+" Cadastrado ", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException error) {
            err.logErro(error);
            throw new BDException(error); 
        }
        finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
     public List<Aluno> listar(Acesso acesso) throws BDException{
        Connection conexao = null;
        CallableStatement instrucao = null;    
       try {
           ResultSet resultados;
           
           alunos = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_listarAluno()}";
           instrucao = conexao.prepareCall(query);
           resultados = instrucao.executeQuery();
         
           while (resultados.next()) {
               Aluno  alunoSaida = new Aluno(); 
               alunoSaida.setMatricula((resultados.getString("alumatricula")));
               alunoSaida.setNome(resultados.getString("alunome"));
               alunoSaida.setSexo(resultados.getString("alusexo"));
               alunoSaida.getTurno().setTurno(resultados.getString("tnonome"));
               alunoSaida.getTurma().setTurma(resultados.getString("turdescricao"));
               alunoSaida.setDatanascimento(resultados.getString("idade"));
               

               alunos.add(alunoSaida);
           }
         
        } catch (SQLException error) {
             err.logErro(error);            
             throw new BDException(error);  
        }       
        finally{
           ControlaConexao.fecharInstrucao(instrucao);
           ControlaConexao.fecharConexao(conexao);
        }
        return alunos;
        
        
   }
     
    public List<Aluno> pesquisar(Aluno aluno, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           alunos = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarAluno(?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,aluno.getNome());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Aluno  alunoSaida = new Aluno(); 
               alunoSaida.setMatricula((resultados.getString("alumatricula")));
               alunoSaida.setNome(resultados.getString("alunome"));
               alunoSaida.setSexo(resultados.getString("alusexo"));
               alunoSaida.getTurno().setTurno(resultados.getString("tnonome"));
               alunoSaida.getTurma().setTurma(resultados.getString("turdescricao"));
               alunoSaida.setDatanascimento(resultados.getString("idade"));

               alunos.add(alunoSaida);
           }
           
        } catch (SQLException error) {
            err.logErro(error);   
            throw new BDException(error); 
      //  }
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return alunos;
       
        
   }
    public List<Aluno> pesquisarAux(Aluno aluno, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           alunos = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarAlunoAux(?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,aluno.getNome());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Aluno  alunoSaida = new Aluno(); 
               
               alunoSaida.setMatricula((resultados.getString("alumatricula")));
               alunoSaida.setNome(resultados.getString("alunome"));
               alunoSaida.setDatanascimento(resultados.getString("aludtnascimento"));
               alunoSaida.setSexo(resultados.getString("alusexo"));
               alunoSaida.setFoto(resultados.getBytes("alufoto"));
               alunoSaida.setNaturalidade(resultados.getString("alunaturalidade")); 
               alunoSaida.getEstado().setEstado(resultados.getString("estnome"));
               alunoSaida.getTurno().setTurno(resultados.getString("tnonome"));
               alunoSaida.getTurma().setTurma(resultados.getString("turdescricao"));
               alunoSaida.getInformacao().setCodigo((resultados.getLong("infcodigo")));
               alunoSaida.getInformacao().setPergunta1(resultados.getString("infpergunta1"));
               alunoSaida.getInformacao().setPergunta2(resultados.getString("infpergunta2"));
               alunoSaida.getInformacao().setPergunta3(resultados.getString("infpergunta3"));
               alunoSaida.getInformacao().setPergunta4(resultados.getString("infpergunta4"));
               alunoSaida.getInformacao().setPergunta5(resultados.getString("infpergunta5"));  
               alunoSaida.getInformacao().setPergunta6(resultados.getString("infpergunta6"));
               alunoSaida.getInformacao().setPergunta7(resultados.getString("infpergunta7"));
               alunoSaida.getInformacao().setPergunta8(resultados.getString("infpergunta8"));
               alunoSaida.getInformacao().setPergunta9(resultados.getString("infpergunta9"));
               alunoSaida.getInformacao().setObs(resultados.getString("infobs"));
             
          
          
               alunos.add(alunoSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);          
            throw new BDException(e);   
         
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return alunos;
    }
    
        public void alterar(Aluno aluno, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarAluno(?,?,?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, aluno.getMatricula());
            instrucao.setString(2, aluno.getNome());
            instrucao.setString(3, aluno.getDatanascimento());
            instrucao.setString(4, aluno.getSexo());
            instrucao.setBytes(5, aluno.getFoto());
            instrucao.setString(6, aluno.getNaturalidade());
            instrucao.setLong(7, aluno.getEstado().getId());
            instrucao.setLong(8, aluno.getTurma().getId());
            instrucao.setLong(9, aluno.getTurno().getId());
            
            
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
    
        
    public void deletar(Aluno aluno, Acesso acesso) throws BDException{
       try {
           Connection conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_excluirAluno(?)}";
           CallableStatement instrucao = conexao.prepareCall(query);
           System.out.println("DAO.AlunoDAO.deletar()"+aluno.getMatricula());
           instrucao.setString(1, aluno.getMatricula());
           instrucao.execute();
        } catch (SQLException e) {
            err.logErro(e);   
            throw new BDException(e);  
        }    
   }
}