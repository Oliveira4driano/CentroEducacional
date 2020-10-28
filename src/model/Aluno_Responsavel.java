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
public class Aluno_Responsavel {
    private Aluno aluno;
    private Responsavel responsavel;

    public Aluno_Responsavel() {
         this.aluno = new Aluno();
        this.responsavel = new Responsavel();
    }

    public Aluno_Responsavel(Aluno aluno, Responsavel responsavel) {
        this.aluno = aluno;
        this.responsavel = responsavel;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
    
        
}
