package br.edu.ucsal.olimpoo;

public class Olimpiada {
    private final int id;
    private final String nome;
    private final int edicao;

    public Olimpiada(int id, String nome, int edicao) {
        this.id = id;
        this.nome = nome;
        this.edicao = edicao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public String toString() {
        return "#" + id + " | " + nome + " | edição " + edicao;
    }
}
