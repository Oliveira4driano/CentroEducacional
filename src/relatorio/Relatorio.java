/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import model.Acesso;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static sun.security.krb5.Confounder.bytes;
import util.ControlaConexao;

/**
 *
 * @author Adriano
 */
public class Relatorio {
     public void gerarRelatorio(JTextField matricula, Acesso acesso) {     
         //JasperReport jasperReport = null;
         //JasperPrint relatorio = null;
         String aluno = matricula.getText();
         Connection conexao = null;
        // usado a classe hashmap para criar um filtro
        HashMap<String, Object> param = new HashMap<>();
  
        param.put("MatriculaAluno", aluno);
        try{
            conexao = ControlaConexao.getConexao(acesso);
            InputStream fonte = Relatorio.class.getResourceAsStream("/RelatorioEscolinha.jrxml");  
            //InputStream fonte1 = Relatorio.class.getResourceAsStream("/RelatorioResponsavelMae.jrxml");
           // InputStream fonte2 = Relatorio.class.getResourceAsStream("/RelatorioResponsavelPai.jrxml");
            JasperReport report = JasperCompileManager.compileReport(fonte);
            //JasperReport report1 = JasperCompileManager.compileReport(fonte1);
           // JasperReport report2 = JasperCompileManager.compileReport(fonte2);
            JasperPrint print = JasperFillManager.fillReport(report, param, conexao);
            //JasperExportManager.exportReportToPdfFile(print, "arquivo.pdf");
            JasperViewer viewer = new JasperViewer(print, false);
            
            viewer.setTitle("Ficha Aluno");
   
            //viewer.setIconImage();
            viewer.setVisible(true);
            
//            File arq = new File(System.getProperty("user.home")+File.separator+aluno+".pdf");
//            if(arq.exists()){
//                arq.delete();
//            }
//            
//            FileOutputStream fos = new FileOutputStream(arq);
//             int bytes = 0;
//            fos.write(bytes);
//            fos.flush();
//            fos.close();
//            
//            Desktop desk = Desktop.getDesktop();
//            desk.open(arq);
           
                             
        } 
            catch (JRException e) {
            Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException ex) {
             Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}


