package quiz;

public class PerguntaMultiplaEscolha extends Pergunta {
    
    private String[] alternativas;
    

    public PerguntaMultiplaEscolha(String enunciado, String respostaCorreta, String dificuldade, String[] alternativas) {
        // Envia para a mãe
        super(enunciado, respostaCorreta, dificuldade); 
        this.alternativas = alternativas;
    }
    
    public String[] getAlternativas() { return alternativas; }
    public void setAlternativas(String[] alternativas) { this.alternativas = alternativas; }
    
    @Override
    public void mostrar() {
        System.out.println("-------------------------------------------------");
        System.out.println("PERGUNTA (Múltipla Escolha - " + this.getDificuldade() + "):");
        System.out.println(this.getEnunciado());
        
        char letra = 'A'; 
        for (String opcao : alternativas) {
            System.out.println(letra + ") " + opcao);
            letra++; 
        }
        System.out.println("-------------------------------------------------");
    }
}