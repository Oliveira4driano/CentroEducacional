/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author dev
 */
public class PercorreVetor {
    
    
    public void next(String no){
           String[] s = {no};
               for (String nome : s) {
                     System.out.println(nome);
                     JOptionPane.showMessageDialog(null, nome);
                }
    }
    
    public void next2(String vet, String nome){
       // String[][] table = {{"Cristiano","32","Italia"},{"Marcos","45","Canada"}};
        String oQueQueroPesquisar = nome;
        String[] table ={vet};
        
        boolean achou = false;

    // Percorre o primeiro índice de 0 até o tamanho do array - 1.
    for (int i = 0; i < table.length; i++) {
        JOptionPane.showMessageDialog(null, table[i]);
    // Percorre o segundo índice de 0 até o tamanho do array - 1.
    //for (int j = 0; j < table[i].length; j++) {

        // Se é o que queria encontrar, marca que achou.
       // if (oQueQueroPesquisar.equals(table[i][j])) {
        if (oQueQueroPesquisar.equals(table[i])) {    
            achou = true;
            break; // Sai do laço. Não apenas do interno, do externo também
        }
    }
   // }

    if (achou) {
        System.out.println("Achei");
    } else {
        System.out.println("Não achei");
    }
    }

}



