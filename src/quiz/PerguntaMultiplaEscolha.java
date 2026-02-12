package quiz;

public class PerguntaMultiplaEscolha extends Pergunta {
    
    // Atributo exclusivo desta classe (as 4 ou 5 opções)
    private String[] alternativas;
    
    // CONSTRUTOR
    // Recebe: Enunciado, a Letra Certa (ex: "A") e o Vetor com as frases das opções
    public PerguntaMultiplaEscolha(String enunciado, String respostaCorreta, String[] alternativas) {
        super(enunciado, respostaCorreta);
        this.alternativas = alternativas;
    }
    
    // --- GETTERS E SETTERS ---
    public String[] getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String[] alternativas) {
        this.alternativas = alternativas;
    }
    
    // A MÁGICA DE MOSTRAR AS OPÇÕES
    @Override
    public void mostrar() {
        System.out.println("-------------------------------------------------");
        System.out.println("PERGUNTA (Múltipla Escolha):");
        System.out.println(this.getEnunciado());
        
        // Aqui usamos um truque para mostrar A, B, C, D automaticamente
        char letra = 'A'; 
        
        // "Para cada opção no meu vetor de alternativas..."
        for (String opcao : alternativas) {
            System.out.println(letra + ") " + opcao);
            letra++; // Passa para a próxima letra (A -> B -> C...)
        }
        System.out.println("-------------------------------------------------");
    }
}