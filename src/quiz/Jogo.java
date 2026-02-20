package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    
    private String codigo, tema;        // O código da sala (ex: "1234")
    private int rodadas, maxParticipantes;
    private int pFacil, pMedio, pDificil;
    private ArrayList<Pergunta> listaDePerguntas; // As perguntas dessa partida
    private int pontuacao; // Pontos do jogador atual
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
        
        // Assim que cria o jogo, ele já carrega as perguntas (Hardcoded)
        this.criarPerguntas();
    }

    public String getCodigo() {
        return codigo;
    }
    ///////////////////////////////
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
            teclado.nextLine(); // LIMPEZA DE BUFFER: Essencial para não bugar o próximo nextLine()
            
            if (num == 1) {
                cadastrarPerguntaMultiplaEscolha(teclado);
            } 
            else if (num == 2) {
                cadastrarPerguntaVF(teclado);
            } 
            else {
                System.out.println("❌ Opção inválida! Tente novamente.");
                i--; // TRUQUE DE LÓGICA: Diminui o 'i' para o loop repetir esta rodada
            }
        }
    }
    
    private void cadastrarPerguntaVF(Scanner teclado) {
    System.out.print("Digite o enunciado da questão V/F: ");
    String enunciado = teclado.nextLine();
    
    System.out.print("Dificuldade:\n 1)Fácil\n 2)Médio\n 3)Difícil");
    System.out.print("\nDigite um número para selecionar a dificuldade: ");
    String dificuldade = teclado.nextLine();
    
    /*
    System.out.print("Dificuldade:\n 1)Fácil\n 2)Médio\n 3)Difícil");
    int dificuldade = (lerOpcaoSegura("Digite um número para selecionar a dificuldade: "));
    */
    
    System.out.print("Resposta correta (V ou F): ");
    String respostaCerta = teclado.nextLine().toUpperCase();
    
    // Criando o objeto e adicionando na lista
    PerguntaVF nova = new PerguntaVF(enunciado, respostaCerta, dificuldade);
    this.listaDePerguntas.add(nova);
    
    System.out.println("✅ Pergunta V/F cadastrada com sucesso!");
}

    private void cadastrarPerguntaMultiplaEscolha(Scanner teclado) {
        System.out.print("Digite o enunciado: ");
        String enunciado = teclado.nextLine();
        
        System.out.print("Dificuldade (Fácil / Médio / Difícil): ");
        String dificuldade = teclado.nextLine();
        
        String[] opcoes = new String[4]; // Vetor para as 4 alternativas
        char letra = 'A';
        for (int i = 0; i < 4; i++) {
            System.out.print("Digite a alternativa " + letra + ": ");
            opcoes[i] = teclado.nextLine();
            letra++;
        }
        
        System.out.print("Qual é a letra correta? (A, B, C ou D): ");
        String respostaCerta = teclado.nextLine().toUpperCase(); // Salva sempre em maiúsculo
        
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
    
    // O MOTOR DO JOGO: O loop que mostra perguntas e corrige
    public void iniciar() {
        // LINHA 110: Criamos o Scanner para ler as respostas do jogador
        Scanner teclado = new Scanner(System.in); 
        
        this.pontuacao = 0; // Zera a pontuação para a nova partida
        
        //Só coloquei tema no lugar de código
        System.out.println("\n=== INICIANDO JOGO: " + this.tema + " ===");
        
        for (Pergunta p : listaDePerguntas) {
            // Usa o método mostrar() que você criou na classe Pergunta
            p.mostrar();
            
            System.out.print("Sua resposta: ");
            String resposta = teclado.next();
            
            // Usa o método corrigir() que você criou na classe Pergunta
            if (p.corrigir(resposta)) {
                int pontosGanhos = 0;
                
                // Verifica a dificuldade da pergunta para saber qual ponto somar
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
        
        
        
    }
}