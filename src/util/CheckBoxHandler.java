/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import model.Informacao;

/**
 *
 * @author dev
 */
public class CheckBoxHandler implements ItemListener {
    private JCheckBox catapora, sarampo, caxumba, convulsao, desmaio;
    private Informacao informacao;
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        String texto = ""; 
    if(catapora.isSelected())
     texto += "Catapora ";

    if(sarampo.isSelected())
     texto += "Sarampo ";

    if(caxumba.isSelected())
     texto += "Caxumba ";

    if(convulsao.isSelected())
     texto += "Convulsão ";
    
    if(desmaio.isSelected())
     texto += "Desmaio ";

    informacao.setPergunta4(texto);
    System.out.println("txt"+texto);
    }
    
}
