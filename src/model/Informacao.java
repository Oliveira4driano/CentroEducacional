/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dev
 */
public class Informacao {
     private static final long serialVersionUID = 1L;
     private long codigo;
     private String pergunta1;
     private String pergunta2;
     private String pergunta3;
     private String pergunta4;
     private String pergunta5;
     private String pergunta6;
     private String pergunta7;
     private String pergunta8;
     private String pergunta9;
     private String obs;

    public Informacao() {
    }

    public Informacao(long codigo, String pergunta1, String pergunta2, String pergunta3, String pergunta4, String pergunta5, String pergunta6, String pergunta7, String pergunta8, String pergunta9, String obs) {
        this.codigo = codigo;
        this.pergunta1 = pergunta1;
        this.pergunta2 = pergunta2;
        this.pergunta3 = pergunta3;
        this.pergunta4 = pergunta4;
        this.pergunta5 = pergunta5;
        this.pergunta6 = pergunta6;
        this.pergunta7 = pergunta7;
        this.pergunta8 = pergunta8;
        this.pergunta9 = pergunta9;
        this.obs = obs;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public String getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(String pergunta3) {
        this.pergunta3 = pergunta3;
    }

    public String getPergunta4() {
        return pergunta4;
    }

    public void setPergunta4(String pergunta4) {
        this.pergunta4 = pergunta4;
    }

    public String getPergunta5() {
        return pergunta5;
    }

    public void setPergunta5(String pergunta5) {
        this.pergunta5 = pergunta5;
    }

    public String getPergunta6() {
        return pergunta6;
    }

    public void setPergunta6(String pergunta6) {
        this.pergunta6 = pergunta6;
    }

    public String getPergunta7() {
        return pergunta7;
    }

    public void setPergunta7(String pergunta7) {
        this.pergunta7 = pergunta7;
    }

    public String getPergunta8() {
        return pergunta8;
    }

    public void setPergunta8(String pergunta8) {
        this.pergunta8 = pergunta8;
    }

    public String getPergunta9() {
        return pergunta9;
    }

    public void setPergunta9(String pergunta9) {
        this.pergunta9 = pergunta9;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
  
    
}
