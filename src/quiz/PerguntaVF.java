package quiz;

public class PerguntaVF extends Pergunta {
    

    public PerguntaVF(String enunciado, String respostaCorreta, String dificuldade) {

    super(enunciado, respostaCorreta, dificuldade);
    }

  
    @Override
    public void mostrar() {
        System.out.println("-------------------------------------------------");
        System.out.println("❓ PERGUNTA (Verdadeiro ou Falso):");
        System.out.println(this.getEnunciado()); // Pega o texto da mãe
        System.out.println("[V] Verdadeiro");
        System.out.println("[F] Falso");
        System.out.println("-------------------------------------------------");
    }
}