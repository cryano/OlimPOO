package br.edu.ucsal.olimpoo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OlimPOOConsole {
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final ArrayList<Aluno> ALUNOS = new ArrayList<>();
    private static final ArrayList<Olimpiada> OLIMPIADAS = new ArrayList<>();
    private static final ArrayList<Participacao> PARTICIPACOES = new ArrayList<>();
    private static final ArrayList<ResultadoOficial> RESULTADOS = new ArrayList<>();

    private static int proximoAlunoId = 1;
    private static int proximaOlimpiadaId = 1;
    private static int proximaParticipacaoId = 1;
    private static int proximoResultadoId = 1;

    public static void main(String[] args) {
        carregarDadosDemonstracao();
        boolean executando = true;
        while (executando) {
            exibirMenu();
            String opcao = ENTRADA.nextLine().trim();
            switch (opcao) {
                case "1" -> cadastrarAluno();
                case "2" -> cadastrarOlimpiada();
                case "3" -> registrarParticipacao();
                case "4" -> registrarResultadoOficial();
                case "5" -> listarPainel();
                case "6" -> enviarLembrete();
                case "0" -> executando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        System.out.println("OlimPOO encerrado.");
    }

    private static void exibirMenu() {
        System.out.println("\n=== OlimPOO ===");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Cadastrar olimpíada");
        System.out.println("3 - Registrar participação");
        System.out.println("4 - Registrar resultado oficial");
        System.out.println("5 - Listar painel");
        System.out.println("6 - Enviar lembrete simulado");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    private static void cadastrarAluno() {
        System.out.print("Matrícula: ");
        String matricula = ENTRADA.nextLine().trim();
        System.out.print("Nome: ");
        String nome = ENTRADA.nextLine().trim();
        System.out.print("E-mail: ");
        String email = ENTRADA.nextLine().trim();

        if (matricula.length() < 4 || nome.length() < 3 || !email.contains("@")) {
            System.out.println("Dados inválidos. Confira matrícula, nome e e-mail.");
            return;
        }
        for (Aluno aluno : ALUNOS) {
            if (aluno.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println("Já existe aluno com essa matrícula.");
                return;
            }
        }

        Aluno aluno = new Aluno(proximoAlunoId, matricula, nome, email);
        proximoAlunoId++;
        ALUNOS.add(aluno);
        System.out.println("Aluno cadastrado: " + aluno);
        System.out.println("E-mail simulado: boas-vindas enviadas para " + email);
    }

    private static void cadastrarOlimpiada() {
        System.out.print("Nome da olimpíada: ");
        String nome = ENTRADA.nextLine().trim();
        System.out.print("Edição/ano: ");
        int edicao = lerInteiro();
        if (nome.length() < 3 || edicao < 2020 || edicao > 2100) {
            System.out.println("Nome ou edição inválidos.");
            return;
        }
        Olimpiada olimpiada = new Olimpiada(proximaOlimpiadaId, nome, edicao);
        proximaOlimpiadaId++;
        OLIMPIADAS.add(olimpiada);
        System.out.println("Olimpíada cadastrada: " + olimpiada);
    }

    private static void registrarParticipacao() {
        listarAlunos();
        System.out.print("ID do aluno: ");
        Aluno aluno = buscarAluno(lerInteiro());
        listarOlimpiadas();
        System.out.print("ID da olimpíada: ");
        Olimpiada olimpiada = buscarOlimpiada(lerInteiro());
        if (aluno == null || olimpiada == null) {
            System.out.println("Aluno ou olimpíada não encontrados.");
            return;
        }
        for (Participacao participacao : PARTICIPACOES) {
            if (participacao.getAluno().getId() == aluno.getId()
                    && participacao.getOlimpiada().getId() == olimpiada.getId()) {
                System.out.println("Participação já registrada.");
                return;
            }
        }
        Participacao participacao = new Participacao(
                proximaParticipacaoId, aluno, olimpiada, "INSCRITO");
        proximaParticipacaoId++;
        PARTICIPACOES.add(participacao);
        System.out.println("Participação registrada: " + participacao);
        System.out.println("E-mail simulado: confirmação enviada para " + aluno.getEmail());
    }

    private static void registrarResultadoOficial() {
        listarParticipacoes();
        System.out.print("ID da participação: ");
        Participacao participacao = buscarParticipacao(lerInteiro());
        if (participacao == null) {
            System.out.println("Participação não encontrada.");
            return;
        }
        for (ResultadoOficial resultado : RESULTADOS) {
            if (resultado.getParticipacao().getId() == participacao.getId()) {
                System.out.println("Essa participação já possui resultado oficial.");
                return;
            }
        }

        System.out.print("Premiação oficial (ouro, prata, bronze, menção ou nenhuma): ");
        String premiacao = ENTRADA.nextLine().trim();
        System.out.print("Classificação informada pela olimpíada: ");
        String classificacao = ENTRADA.nextLine().trim();
        System.out.print("Fonte oficial do resultado: ");
        String fonte = ENTRADA.nextLine().trim();
        if (premiacao.isBlank() || classificacao.isBlank() || fonte.length() < 5) {
            System.out.println("Resultado incompleto. Nenhum dado foi armazenado.");
            return;
        }

        ResultadoOficial resultado = new ResultadoOficial(
                proximoResultadoId, participacao, premiacao,
                classificacao, fonte, LocalDateTime.now());
        proximoResultadoId++;
        RESULTADOS.add(resultado);
        System.out.println("Resultado oficial registrado: " + resultado);
        System.out.println("E-mail simulado: resultado enviado para "
                + participacao.getAluno().getEmail());
        System.out.println("Auditoria simulada: operação registrada no console.");
    }

    private static void enviarLembrete() {
        listarParticipacoes();
        System.out.print("ID da participação: ");
        Participacao participacao = buscarParticipacao(lerInteiro());
        if (participacao == null) {
            System.out.println("Participação não encontrada.");
            return;
        }
        String mensagem = "Lembrete: acompanhe o cronograma da "
                + participacao.getOlimpiada().getNome() + " "
                + participacao.getOlimpiada().getEdicao() + ".";
        System.out.println("E-mail simulado para "
                + participacao.getAluno().getEmail() + ": " + mensagem);
    }

    private static void listarPainel() {
        System.out.println("\n--- ALUNOS (" + ALUNOS.size() + ") ---");
        listarAlunos();
        System.out.println("\n--- OLIMPÍADAS (" + OLIMPIADAS.size() + ") ---");
        listarOlimpiadas();
        System.out.println("\n--- PARTICIPAÇÕES (" + PARTICIPACOES.size() + ") ---");
        listarParticipacoes();
        System.out.println("\n--- RESULTADOS OFICIAIS (" + RESULTADOS.size() + ") ---");
        if (RESULTADOS.isEmpty()) {
            System.out.println("Nenhum resultado registrado.");
        }
        for (ResultadoOficial resultado : RESULTADOS) {
            System.out.println(resultado);
        }
    }

    private static void listarAlunos() {
        for (Aluno aluno : ALUNOS) {
            System.out.println(aluno);
        }
    }

    private static void listarOlimpiadas() {
        for (Olimpiada olimpiada : OLIMPIADAS) {
            System.out.println(olimpiada);
        }
    }

    private static void listarParticipacoes() {
        for (Participacao participacao : PARTICIPACOES) {
            System.out.println(participacao);
        }
    }

    private static Aluno buscarAluno(int id) {
        for (Aluno aluno : ALUNOS) {
            if (aluno.getId() == id) {
                return aluno;
            }
        }
        return null;
    }

    private static Olimpiada buscarOlimpiada(int id) {
        for (Olimpiada olimpiada : OLIMPIADAS) {
            if (olimpiada.getId() == id) {
                return olimpiada;
            }
        }
        return null;
    }

    private static Participacao buscarParticipacao(int id) {
        for (Participacao participacao : PARTICIPACOES) {
            if (participacao.getId() == id) {
                return participacao;
            }
        }
        return null;
    }

    private static int lerInteiro() {
        String texto = ENTRADA.nextLine().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void carregarDadosDemonstracao() {
        Aluno aluno = new Aluno(proximoAlunoId++, "2026001", "Ana Souza", "ana.souza@ucsal.edu.br");
        Olimpiada olimpiada = new Olimpiada(proximaOlimpiadaId++, "OBI", 2026);
        Participacao participacao = new Participacao(
                proximaParticipacaoId++, aluno, olimpiada, "INSCRITO");
        ResultadoOficial resultado = new ResultadoOficial(
                proximoResultadoId++, participacao, "Bronze", "42ª colocação",
                "planilha oficial da OBI", LocalDateTime.now());
        ALUNOS.add(aluno);
        OLIMPIADAS.add(olimpiada);
        PARTICIPACOES.add(participacao);
        RESULTADOS.add(resultado);
    }
}
