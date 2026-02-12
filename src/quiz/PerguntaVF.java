package quiz;

public class PerguntaVF extends Pergunta {
    
    // CONSTRUTOR
    // Ele recebe o texto da pergunta e a resposta certa ("V" ou "F")
    public PerguntaVF(String enunciado, String respostaCorreta) {
        // "super" manda esses dados para a classe mãe (Pergunta) guardar
        super(enunciado, respostaCorreta);
    }

    // --- A IMPLEMENTAÇÃO DO MÉTODO ABSTRATO ---
    // Lembra que a mãe obrigou a ter o "mostrar"? Aqui está ele.
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