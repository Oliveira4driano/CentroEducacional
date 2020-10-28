/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.sql.Date;



/**
 *
 * @author dev
 */
public class Aluno {
    private static final long serialVersionUID = 1L;
    private long id;
    private String matricula;
    private String nome;
    private String datanascimento;
    private String sexo;
    private byte[] foto;
    private String naturalidade;
    private Estado estado;
    private Turno turno;
    private Turma turma;
    private Informacao informacao;
    private String status;

    public Aluno() {
        this.turma = new Turma();
        this.informacao = new Informacao();
        this.estado = new Estado();
        this.turno = new Turno();
    }

    public Aluno(long id, String matricula, String nome, String datanascimento, String sexo, byte[] foto, String naturalidade, Estado estado, Turno turno, Turma turma, Informacao informacao, String status) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
        this.foto = foto;
        this.naturalidade = naturalidade;
        this.estado = estado;
        this.turno = turno;
        this.turma = turma;
        this.informacao = informacao;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(String datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Informacao getInformacao() {
        return informacao;
    }

    public void setInformacao(Informacao informacao) {
        this.informacao = informacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
  

    
   
}
