/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dev
 */
public class LogErro {
    
    
    public void logErro(SQLException e){
       
        FormataData data = new FormataData(); //
        File diretorio = new File(System.getProperty("user.home")+File.separator+"MyErro");
        diretorio.mkdir();
        PrintWriter a = null;
        try {
            a = new PrintWriter (new FileWriter (System.getProperty("user.home")+File.separator+"MyErro"+File.separator+data.dataAtual()+"_logErroEscolinha.txt", true));
        } catch (IOException ex) {
            Logger.getLogger(LogErro.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.println(data.horaAtual()+" "+e.getMessage());
        a.flush();
        a.close();
        JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showMessageDialog(null,"Ocorreu um erro de banco de dados: "+e.getMessage());
        
    }
    
}
