package br.edu.ucsal.olimpoo;

public class Aluno {
    private final int id;
    private final String matricula;
    private final String nome;
    private final String email;

    public Aluno(int id, String matricula, String nome, String email) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "#" + id + " | " + matricula + " | " + nome + " | " + email;
    }
}
