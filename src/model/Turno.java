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
public class Turno {
    private static final long serialVersionUID = 1L;
    private long id;
    private String turno;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Turno(long id, String turno) {
        this.id = id;
        this.turno = turno;
    }

    public Turno() {
    }
    
}
