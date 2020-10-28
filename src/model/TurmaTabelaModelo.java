/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano
 */
public class TurmaTabelaModelo extends AbstractTableModel{
    private List<Turma> turmas;
    
    private int qtdcoluna = 2;     
    

    public TurmaTabelaModelo(List<Turma> turmas) {
        this.turmas = turmas;
    }
    
    @Override
    public int getRowCount() {
        return turmas.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Turma turma = turmas.get(linha);
        switch(coluna){
            case 0: return turma.getId();
            case 1: return turma.getTurma();
          //  case 2: return usuario.getGrupo().getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Codigo";
            case 1: return "Turma";
           // case 2: return "Grupo";
        }
        return null;
    }
    
}
