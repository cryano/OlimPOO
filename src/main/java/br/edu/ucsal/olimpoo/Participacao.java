package br.edu.ucsal.olimpoo;

public class Participacao {
    private final int id;
    private final Aluno aluno;
    private final Olimpiada olimpiada;
    private final String situacao;

    public Participacao(int id, Aluno aluno, Olimpiada olimpiada, String situacao) {
        this.id = id;
        this.aluno = aluno;
        this.olimpiada = olimpiada;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Olimpiada getOlimpiada() {
        return olimpiada;
    }

    public String getSituacao() {
        return situacao;
    }

    @Override
    public String toString() {
        return "#" + id + " | " + aluno.getNome() + " | "
                + olimpiada.getNome() + " " + olimpiada.getEdicao() + " | " + situacao;
    }
}
