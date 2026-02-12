package quiz;

public abstract class Pergunta {
    
    private String enunciado;
    private String respostaCorreta;
    
    // CONSTRUTOR
    public Pergunta(String enunciado, String respostaCorreta) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
    }

    // --- GETTERS E SETTERS (Encapsulamento Completo) ---

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRespostaCorreta() {
        return respostaCorreta;
    }

    public void setRespostaCorreta(String respostaCorreta) {
        this.respostaCorreta = respostaCorreta;
    }
    
    // --- MÉTODOS DE LÓGICA DO JOGO ---

    // O método corrigir continua sendo o mais importante!
    // Ele protege a lógica de comparação dentro da classe.
    public boolean corrigir(String respostaUsuario) {
        if (this.respostaCorreta.equalsIgnoreCase(respostaUsuario)) {
            System.out.println("✅ ACERTOU!");
            return true;
        } else {
            System.out.println("❌ ERROU! A resposta certa era: " + this.respostaCorreta);
            return false;
        }
    }
    
    // Método abstrato para obrigar as filhas a se mostrarem
    public abstract void mostrar();
}