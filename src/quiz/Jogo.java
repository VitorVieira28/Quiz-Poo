package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    
    private String codigo;        // O código da sala (ex: "1234")
    private int valorPorPergunta; // Quanto vale cada acerto (ex: 10)
    private ArrayList<Pergunta> listaDePerguntas; // As perguntas dessa partida
    private int pontuacao; // Pontos do jogador atual
    
    // CONSTRUTOR: Pede o código da sala e o valor dos pontos
    public Jogo(String codigo, int valorPorPergunta) {
        this.codigo = codigo;
        this.valorPorPergunta = valorPorPergunta;
        this.listaDePerguntas = new ArrayList<>();
        this.pontuacao = 0;
        
        // Assim que cria o jogo, ele já carrega as perguntas (Hardcoded)
        this.criarPerguntas();
    }

    public String getCodigo() {
        return codigo;
    }
    
    // Método que cria as perguntas fixas (Hardcoded)
    private void criarPerguntas() {
        // Usa as classes que VOCÊ JÁ CRIOU (não apague elas!)
        listaDePerguntas.add(new PerguntaVF("O Java é compilado e interpretado?", "V"));
        listaDePerguntas.add(new PerguntaVF("int é uma classe?", "F"));
        
        String[] opcoes = {"Gosling", "Musk", "Gates", "Jobs"};
        listaDePerguntas.add(new PerguntaMultiplaEscolha("Quem criou o Java?", "A", opcoes));
    }
    
    // O MOTOR DO JOGO: O loop que mostra perguntas e corrige
    public void iniciar() {
        Scanner teclado = new Scanner(System.in);
        this.pontuacao = 0; // Zera pontuação ao começar
        
        System.out.println("\n=== INICIANDO JOGO (Sala: " + this.codigo + ") ===");
        
        for (Pergunta p : listaDePerguntas) {
            // Usa o método mostrar() que você criou na classe Pergunta
            p.mostrar();
            
            System.out.print("Sua resposta: ");
            String resposta = teclado.next();
            
            // Usa o método corrigir() que você criou na classe Pergunta
            if (p.corrigir(resposta)) {
                this.pontuacao += this.valorPorPergunta; // Soma pontos!
                System.out.println(">> +" + this.valorPorPergunta + " pontos!");
            }
            System.out.println("---------------------------------");
        }
        
        System.out.println("\nFIM DE JOGO! Você fez " + this.pontuacao + " pontos.");
    }
}