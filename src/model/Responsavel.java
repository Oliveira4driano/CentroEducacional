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
public class Responsavel {
    private static final long serialVersionUID = 1L;
    private long idResponsavel;
    private String nome;
    private String localTrabalho;
    private String profissao;
    private Telefone telefone;
    private String foneTrabalho;
    private String parentesco;
    private Endereco endereco;

    public Responsavel() {
        this.endereco = new Endereco();
        this.telefone = new Telefone();
    }

    public Responsavel(long idResponsavel, String nome, String localTrabalho, String profissao, Telefone telefone, String foneTrabalho, String parentesco, Endereco endereco) {
        this.idResponsavel = idResponsavel;
        this.nome = nome;
        this.localTrabalho = localTrabalho;
        this.profissao = profissao;
        this.telefone = telefone;
        this.foneTrabalho = foneTrabalho;
        this.parentesco = parentesco;
        this.endereco = endereco;
    }

    public long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(String localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getFoneTrabalho() {
        return foneTrabalho;
    }

    public void setFoneTrabalho(String foneTrabalho) {
        this.foneTrabalho = foneTrabalho;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

   
    
    
}
