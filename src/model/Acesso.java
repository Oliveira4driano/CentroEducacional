/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dev-2810
 */
public class Acesso implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static String host;
    private static String porta;
    private static String usuario;
    private static String senha;
    private static String banco;

    public String getHost() {
        return host;
    }

    public String getPorta() {
        return porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getBanco() {
        return banco;
    }
    

    public void setHost(String host) {
        this.host = host;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Acesso{" + "host=" + host + ", porta=" + porta + ", usuario=" + usuario + ", senha=" + senha + ", banco=" + banco + '}';
    }
    
}
