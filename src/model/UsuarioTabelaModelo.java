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
public class UsuarioTabelaModelo extends AbstractTableModel{
    private List<Usuario> usuarios;
    
    private int qtdcoluna = 5;     
    

    public UsuarioTabelaModelo(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return qtdcoluna;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Usuario usuario = usuarios.get(linha);
        switch(coluna){
            case 0: return usuario.getLogin();
            case 1: return usuario.getSelecionar();
            case 2: return usuario.getInserir();
            case 3: return usuario.getAlterar();
            case 4: return usuario.getDeletar();
          //  case 2: return usuario.getGrupo().getDescricao();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch(coluna){
            //case 0: return "Codigo";
            case 0: return "Usuário";
            case 1: return "Selecionar";
            case 2: return "Inserir";
            case 3: return "Alterar";
            case 4: return "Deletar";
           // case 2: return "Grupo";
        }
        return null;
    }
    
}
