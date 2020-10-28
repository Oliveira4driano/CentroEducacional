/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Acesso;
import model.Aluno;
import model.Responsavel;
import model.Responsavel;
import model.Telefone;
import util.BDException;
import util.ControlaConexao;
import util.LogErro;

/**
 *
 * @author dev
 */
public class ResponsavelDAO {
    private LogErro err = new LogErro();
    private List<Responsavel> responsaveis;
    
    
    public void inserir(Responsavel responsavel, Acesso acesso) throws BDException, SQLException{     
        Connection conexao = null;
        CallableStatement instrucao = null;
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_inserirResponsavel(?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, responsavel.getNome());
            instrucao.setString(2,  responsavel.getLocalTrabalho());
            instrucao.setString(3, responsavel.getProfissao());
            instrucao.setString(4, responsavel.getFoneTrabalho());
            instrucao.setString(5,responsavel.getParentesco());
            instrucao.setString(6, responsavel.getTelefone().getNumero());
            //instrucao.setLong(6, responsavel.getEndereco().getIdendereco());
         
            
            instrucao.executeUpdate();
            //ControlaConexao.fecharInstrucao(instrucao);
            //conexao.close();
            //JOptionPane.showMessageDialog(null, "Cadastrado" , "AVISO", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("REsponsavelDAO");
        } catch (SQLException error) {
             err.logErro(error);
            throw new BDException(error); 
           
        }
        finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
    
    public List<Responsavel> pesquisarMae(Aluno aluno, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           responsaveis = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           String query = "{call sp_pesquisarResponsavelMae(?, ?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,aluno.getNome());
           instrucao.setString(2, aluno.getMatricula());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Responsavel  responsavelSaida = new Responsavel();
               responsavelSaida.setIdResponsavel(resultados.getLong("rescodigo"));
               responsavelSaida.setNome((resultados.getString("resnome")));
               responsavelSaida.setLocalTrabalho(resultados.getString("reslocaltrabalho"));
               responsavelSaida.setProfissao(resultados.getString("resprofissao"));
               responsavelSaida.setFoneTrabalho(resultados.getString("resfonetrabalho"));
               responsavelSaida.setParentesco(resultados.getString("resgrauparentesco"));
               responsavelSaida.getTelefone().setNumero(resultados.getString("fontelefone"));
               responsavelSaida.getEndereco().setId(resultados.getLong("endcodigo"));
               responsavelSaida.getEndereco().setCep(resultados.getString("endcep"));
               responsavelSaida.getEndereco().setLogradouro(resultados.getString("endrua"));
               responsavelSaida.getEndereco().setNumero(resultados.getString("endnumero"));
               responsavelSaida.getEndereco().setBairro(resultados.getString("endbairro"));

               responsaveis.add(responsavelSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);          
            throw new BDException(e);   
         
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return responsaveis;
    }
    
     public List<Responsavel> pesquisarPai(Aluno aluno, Acesso acesso)throws BDException{
       Connection conexao = null;
       CallableStatement instrucao = null;
     
       try {
           ResultSet resultados;          
           responsaveis = new ArrayList<>();
           conexao = ControlaConexao.getConexao(acesso);
           
           String query = "{call sp_pesquisarResponsavelPai(?, ?)}";
           instrucao = conexao.prepareCall(query);
           instrucao.setString(1,aluno.getNome());
           instrucao.setString(2, aluno.getMatricula());
           resultados = instrucao.executeQuery();
           
           
           while (resultados.next()) {
               Responsavel  responsavelSaida = new Responsavel(); 
               responsavelSaida.setIdResponsavel(resultados.getLong("rescodigo"));
               responsavelSaida.setNome((resultados.getString("resnome")));
               responsavelSaida.setLocalTrabalho(resultados.getString("reslocaltrabalho"));
               responsavelSaida.setProfissao(resultados.getString("resprofissao"));
               responsavelSaida.setFoneTrabalho(resultados.getString("resfonetrabalho"));
               responsavelSaida.setParentesco(resultados.getString("resgrauparentesco"));
               responsavelSaida.getTelefone().setNumero(resultados.getString("fontelefone")); 
               responsavelSaida.getEndereco().setId(resultados.getLong("endcodigo"));
               responsavelSaida.getEndereco().setCep(resultados.getString("endcep"));
               responsavelSaida.getEndereco().setLogradouro(resultados.getString("endrua"));
               responsavelSaida.getEndereco().setNumero(resultados.getString("endnumero"));
               responsavelSaida.getEndereco().setBairro(resultados.getString("endbairro"));

               responsaveis.add(responsavelSaida);
           }
           
        } catch (SQLException e) {
            err.logErro(e);          
            throw new BDException(e);   
         
        }finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }  
       return responsaveis;
    }
     
        public void alterar(Responsavel responsavel, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarResponsavel(?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setLong(1, responsavel.getEndereco().getId());
            instrucao.setString(2, responsavel.getNome());
            instrucao.setString(3, responsavel.getLocalTrabalho());
            instrucao.setString(4, responsavel.getProfissao());
            instrucao.setString(5, responsavel.getFoneTrabalho());
            instrucao.setString(6, responsavel.getTelefone().getNumero());
            instrucao.setString(7, responsavel.getParentesco());
            
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
    
    
    
}
