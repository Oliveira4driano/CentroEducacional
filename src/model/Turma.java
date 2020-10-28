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
public class Turma {
    private static final long serialVersionUID = 1L;
    private long id;
    private String turma;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Turma() {
    }

    public Turma(String turma, long id) {
        this.turma = turma;
        this.id = id;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
    
    
    
}
