/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aluno
 */
public class Usuario {

    
   private static final long serialVersionUID = 1L;         
   private int codigo;
   private String login;
   private String senha;
   private String selecionar;
   private String inserir;
   private String alterar;
   private String deletar;
   
  // private Grupo grupo;
    
   public Usuario() {
      // this.grupo = new Grupo();
    } 

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
   

   public Usuario(int codigo, String login) {
        this.codigo = codigo;
        this.login = login;
       // this.grupo = grupo;
    }

    public Usuario(int codigo, String login, String senha, String selecionar, String inserir, String alterar, String deletar) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.selecionar = selecionar;
        this.inserir = inserir;
        this.alterar = alterar;
        this.deletar = deletar;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSelecionar() {
        return selecionar;
    }

    public void setSelecionar(String selecionar) {
        this.selecionar = selecionar;
    }

    public String getInserir() {
        return inserir;
    }

    public void setInserir(String inserir) {
        this.inserir = inserir;
    }

    public String getAlterar() {
        return alterar;
    }

    public void setAlterar(String alterar) {
        this.alterar = alterar;
    }

    public String getDeletar() {
        return deletar;
    }

    public void setDeletar(String deletar) {
        this.deletar = deletar;
    }
   
}
   