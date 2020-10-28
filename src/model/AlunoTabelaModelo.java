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
public class AlunoTabelaModelo extends AbstractTableModel{
    private List<Aluno> alunos;
   
    
    private int qtdcoluna = 6;     
    

    public AlunoTabelaModelo(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    @Override
    public int getRowCount() {
        return alunos.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Aluno aluno = alunos.get(linha);
        switch(coluna){
            case 0: return aluno.getMatricula();
            case 1: return aluno.getNome();
            case 2: return aluno.getSexo();
            case 3: return aluno.getTurno().getTurno();
            case 4: return aluno.getTurma().getTurma();
            case 5: return aluno.getDatanascimento();
          //  case 2: return usuario.getGrupo().getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            case 0: return "Matrícula";
            case 1: return "Nome";
            case 2: return "Sexo";
            case 3: return "Turno";
            case 4: return "Turma";
            case 5: return "Idade";
        }
        return null;
    }
    
}
