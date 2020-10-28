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
import javax.swing.JOptionPane;
import model.Acesso;
import model.Informacao;
import model.Responsavel;
import model.Telefone;
import util.BDException;
import util.ControlaConexao;
import util.LogErro;

/**
 *
 * @author dev
 */
public class InformacaoDAO {
    private LogErro err = new LogErro();
    private List<Informacao> informacao;
    
    
    public void inserir(Informacao informacao, Acesso acesso) throws BDException, SQLException{     
        Connection conexao = null;
        CallableStatement instrucao = null;
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_inserirInformacao(?,?,?,?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setString(1, informacao.getPergunta1());
            instrucao.setString(2, informacao.getPergunta2());
            instrucao.setString(3, informacao.getPergunta3());
            instrucao.setString(4, informacao.getPergunta4());
            instrucao.setString(5, informacao.getPergunta5());
            instrucao.setString(6, informacao.getPergunta6());
            instrucao.setString(7, informacao.getPergunta7());
            instrucao.setString(8, informacao.getPergunta8());
            instrucao.setString(9, informacao.getPergunta9());
            instrucao.setString(10, informacao.getObs());
            
            instrucao.executeUpdate();

            System.out.println("InformaçãoDAO");
        } catch (SQLException error) {
             err.logErro(error);
            throw new BDException(error); 
           
        }
        finally{
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }   
    }
    
        public void alterar(Informacao informacao, Acesso acesso) throws BDException{
         Connection conexao = null;
        CallableStatement instrucao = null;   
        try {
            conexao = ControlaConexao.getConexao(acesso);
            String query ="{call sp_alterarInformacao(?,?,?,?,?,?,?,?,?,?,?)}";
            instrucao = conexao.prepareCall(query);
            instrucao.setLong(1, informacao.getCodigo());
            instrucao.setString(2,informacao.getPergunta1());
            instrucao.setString(3, informacao.getPergunta2());
            instrucao.setString(4, informacao.getPergunta3());
            instrucao.setString(5, informacao.getPergunta4());
            instrucao.setString(6, informacao.getPergunta5());
            instrucao.setString(7, informacao.getPergunta6());
            instrucao.setString(8, informacao.getPergunta7());
            instrucao.setString(9, informacao.getPergunta8());
            instrucao.setString(10, informacao.getPergunta9());
            instrucao.setString(11, informacao.getObs());
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
