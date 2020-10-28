/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Acesso;
import model.Endereco;
import util.BDException;

import util.ControlaConexao;
import util.LogErro;

/**
 *
 * @author dev
 */
public class EnderecoDAO {
    private List<Endereco> enderecos;
    private LogErro err = new LogErro();
    
    
    public void inserir(Endereco endereco, Acesso acesso) throws BDException{     
        Connection conexao = null;
        CallableStatement instrucao = null;
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_inserirEndereco(?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, endereco.getCep());
            instrucao.setString( 2,  endereco.getLogradouro());
            instrucao.setString(3, endereco.getNumero());
            instrucao.setString(4, endereco.getBairro());
         
            
            instrucao.executeUpdate();
            //ControlaConexao.fecharInstrucao(instrucao);
            //conexao.close();
            //JOptionPane.showMessageDialog(null, "Endereco Cadastrado" , "AVISO", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("enderecoDAO");
        } catch (SQLException error) {
             err.logErro(error);
            throw new BDException(error); 
           
        }
        finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
    
        public void alterar(Endereco endereco, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarEndereco(?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setLong(1,endereco.getId());
            instrucao.setString(2, endereco.getCep());
            instrucao.setString(3, endereco.getLogradouro());
            instrucao.setString(4, endereco.getNumero());
            instrucao.setString(5, endereco.getBairro());
         
            
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

