package br.com.vicente.model;

import java.io.Serializable;

public class Aluno  implements Serializable {
    private int id = 0;
    private  String nomeAluno;
    private  String telefoneAluno;
    private  String emailAluno;

    public Aluno(String nomeAluno, String telefoneAluno, String emailAluno) {
        this.nomeAluno = nomeAluno;
        this.telefoneAluno = telefoneAluno;
        this.emailAluno = emailAluno;
    }

    public Aluno() {

    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getTelefoneAluno() {
        return telefoneAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    @Override
    public String toString() {
        return nomeAluno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public void setTelefoneAluno(String telefoneAluno) {
        this.telefoneAluno = telefoneAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}
