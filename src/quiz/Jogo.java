package quiz;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Jogo {
    
    private String codigo, tema;        // O código da sala
    private int rodadas, maxParticipantes;
    private int pFacil, pMedio, pDificil;
    private ArrayList<Pergunta> listaDePerguntas; // As perguntas dessa partida
    private int pontuacao; // Pontos do jogador atual
    // Lista para guardar os usuários que já jogaram esta partida
    private ArrayList<Usuario> jogadoresQueJaJogaram = new ArrayList<>();
    private Map<Usuario, Integer> ranking = new HashMap<>();
    private int pontosPerdidosPorPulo;
    Scanner teclado = new Scanner(System.in);
    MinhaInterface opc = new MinhaInterface();
    
    // CONSTRUTOR: Pede o código da sala e o valor dos pontos
    public Jogo(String codigo, String tema, int rodadas, int maxParticipantes) {
        this.codigo = codigo;
        this.tema = tema;
        this.maxParticipantes = maxParticipantes;
        this.rodadas = rodadas;
        this.listaDePerguntas = new ArrayList<>();
        this.pontuacao = 0;
        
        this.criarPerguntas();
    }

    public String getCodigo() {
        return codigo;
    }
 
    private void criarPerguntas() {
        System.out.println("\nVocê está criando um jogo de " + this.rodadas + " perguntas!");
        System.out.println("Do tema: " + this.tema);
        
        for(int i = 1; i <= this.rodadas; i++){
            System.out.printf("\n--- Cadastrando Pergunta %d ---\n", i);
            System.out.println("Escolha o tipo da pergunta:");
            System.out.println("(1) Múltipla escolha (A, B, C, D)");
            System.out.println("(2) Verdadeiro ou Falso (V/F)");
            System.out.print("Opção: ");
            
            int num = teclado.nextInt();
            teclado.nextLine();
            
            if (num == 1) {
                cadastrarPerguntaMultiplaEscolha(teclado);
            } 
            else if (num == 2) {
                cadastrarPerguntaVF(teclado);
            } 
            else {
                System.out.println("❌ Opção inválida! Tente novamente.");
                i--;
            }
        }
    }
    
    private void cadastrarPerguntaVF(Scanner teclado) {
    System.out.print("Digite o enunciado da questão V/F: ");
    String enunciado = teclado.nextLine();
    
    String dificuldade = "";
        boolean dificuldadeValida = false;
        
        while (!dificuldadeValida) {
            System.out.print("Dificuldade:\n 1)Facil\n 2)Medio\n 3)Dificil\nDigite um numero para selecionar: ");
            
            dificuldade = teclado.nextLine(); 
            
            if (dificuldade.equals("1") || dificuldade.equals("2") || dificuldade.equals("3")) {
                dificuldadeValida = true; 
            } else {
                System.out.println("❌ Opcao invalida! Por favor, digite apenas 1, 2 ou 3.\n");
            }
        }
    
    
    String respostaCerta = "";
        boolean respostaValida = false;
        
        while (!respostaValida) {
            System.out.print("Qual a resposta correta (V/F)? ");
            
            respostaCerta = teclado.nextLine().toUpperCase(); 
            
            // Verifica se a letra é exatamente V ou F
            if (respostaCerta.equals("V") || respostaCerta.equals("F")) {
                respostaValida = true;
            } else {
                System.out.println("❌ Opcao invalida! Por favor, digite apenas V ou F.\n");
            }
        }
    
    // Criando o objeto e adicionando na lista
    PerguntaVF nova = new PerguntaVF(enunciado, respostaCerta, dificuldade);
    this.listaDePerguntas.add(nova);
    
    System.out.println("✅ Pergunta V/F cadastrada com sucesso!");
}

    private void cadastrarPerguntaMultiplaEscolha(Scanner teclado) {
        System.out.print("Digite o enunciado: ");
        String enunciado = teclado.nextLine();
        
       String dificuldade = "";
        boolean dificuldadeValida = false;
        
        while (!dificuldadeValida) {
            System.out.print("Dificuldade:\n 1)Facil\n 2)Medio\n 3)Dificil\nDigite um numero para selecionar: ");
            
            dificuldade = teclado.nextLine(); 
            
            if (dificuldade.equals("1") || dificuldade.equals("2") || dificuldade.equals("3")) {
                dificuldadeValida = true; 
            } else {
                System.out.println("❌ Opcao invalida! Por favor, digite apenas 1, 2 ou 3.\n");
            }
        }   
        
        String[] opcoes = new String[4];
        char letra = 'A';
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a alternativa " + letra + ": ");
            opcoes[i] = teclado.nextLine();
            letra++;
        }
        
        String respostaCerta = "";
        boolean respostaValida = false;
        
       
        while (!respostaValida) {
            System.out.print("Qual e a letra correta? (A, B, C ou D): ");
            
            
            respostaCerta = teclado.nextLine().toUpperCase(); 
            
            if (respostaCerta.equals("A") || respostaCerta.equals("B") || 
                respostaCerta.equals("C") || respostaCerta.equals("D")) {
                respostaValida = true; // Acertou a letra, libera a saída!
            } else {
                System.out.println("❌ Opcao invalida! Por favor, digite apenas A, B, C ou D.\n");
            }
        }
        
        // Instancia a filha passando os dados coletados
        PerguntaMultiplaEscolha novaPergunta = new PerguntaMultiplaEscolha(enunciado, respostaCerta, dificuldade, opcoes);
        this.listaDePerguntas.add(novaPergunta);
        System.out.println("✅ Pergunta adicionada!");
    }

    
    public void setConfigPontuacao(int f, int m, int d) {
        this.pFacil = f;
        this.pMedio = m;
        this.pDificil = d;
    }
    
    public boolean jaJogou(Usuario jogadorLogado) {
        for (Usuario u : jogadoresQueJaJogaram) {
            
            if (u.getLogin().equals(jogadorLogado.getLogin())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSalaCheia() {
        // Se o tamanho da lista for maior ou igual ao limite, retorna true (está cheia)
        return this.jogadoresQueJaJogaram.size() >= this.maxParticipantes; 
    }

    // Grava o usuário na lista depois que ele termina de jogar
    public void registrarPartida(Usuario jogadorLogado) {
        this.jogadoresQueJaJogaram.add(jogadorLogado);
    }
    public void exibirRanking() {
        System.out.println("\n=== RANKING DA SALA ===");
        if (ranking.isEmpty()) {
            System.out.println("Ninguém jogou esta partida ainda.");
            return;
        }

    // 1. Cria uma lista temporária com os dados do ranking e já ordena do maior pro menor
        java.util.List<Map.Entry<Usuario, Integer>> listaOrdenada = new java.util.ArrayList<>(ranking.entrySet());
        listaOrdenada.sort(Map.Entry.<Usuario, Integer>comparingByValue().reversed());

        // 2. Cria o contador de posição
        int posicao = 1;

        // 3. Imprime cada jogador com a sua posição na frente
        for (Map.Entry<Usuario, Integer> entry : listaOrdenada) {
            Usuario u = entry.getKey();
            int pontos = entry.getValue();
            
            System.out.println(posicao + " - Nome: " + u.getNome() + " | Login: " + u.getLogin() + " | Pontos: " + pontos);
            
            posicao++; 
        }
        
    }
    public void setPontosPerdidosPorPulo(int pontos) {
        this.pontosPerdidosPorPulo = pontos;
    }
    
    
    public void iniciar() {
       
        Scanner teclado = new Scanner(System.in); 
        Collections.shuffle(this.listaDePerguntas);
        this.pontuacao = 0; 
        
       
        System.out.println("\n=== INICIANDO JOGO: " + this.tema + " ===");
        
        for (Pergunta p : listaDePerguntas) {
            
            p.mostrar();
            
            System.out.println("➡️ Digite [P] para PULAR a pergunta (Penalidade: -" + this.pontosPerdidosPorPulo + " pontos)");
            System.out.print("Sua resposta: ");
            String resposta = teclado.next().toUpperCase();
            if (resposta.equals("P")) {
                this.pontuacao -= this.pontosPerdidosPorPulo;
                System.out.println("⚠️ Você pulou a pergunta! Perdeu " + this.pontosPerdidosPorPulo + " pontos.");
                System.out.println("Pontuação atual: " + this.pontuacao);
                continue; 
            }
            
            
            if (p.corrigir(resposta)) {
                int pontosGanhos = 0;
                
               
                if (p.getDificuldade().equalsIgnoreCase("1")) {
                    pontosGanhos = this.pFacil;
                } else if (p.getDificuldade().equalsIgnoreCase("2")) {
                    pontosGanhos = this.pMedio;
                } else {
                    pontosGanhos = this.pDificil;
                }

                this.pontuacao += pontosGanhos; 
                System.out.println(">> +" + pontosGanhos + " pontos!");
            }
            System.out.println("---------------------------------");
            
        }
        
        System.out.println("\nFIM DE JOGO! Você fez " + this.pontuacao + " pontos.");
        this.ranking.put(Sistema.usuarioLogado, this.pontuacao);
        
    
        
        
    }
}