package quiz;

public abstract class Pergunta {
    
    private String enunciado;
    private String respostaCorreta;
    private String dificuldade; // Fundamental para o sistema de pontos!
    
    // CONSTRUTOR
    public Pergunta(String enunciado, String respostaCorreta, String dificuldade) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.dificuldade = dificuldade;
    }

    // GETTERS E SETTERS
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String getRespostaCorreta() { return respostaCorreta; }
    public void setRespostaCorreta(String respostaCorreta) { this.respostaCorreta = respostaCorreta; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
    
    // --- MÉTODOS DE LÓGICA DO JOGO ---
    public boolean corrigir(String respostaUsuario) {
        if (this.respostaCorreta.equalsIgnoreCase(respostaUsuario)) {
            System.out.println("✅ ACERTOU!");
            return true;
        } else {
            System.out.println("❌ ERROU! A resposta certa era: " + this.respostaCorreta);
            return false;
        }
    }
    
    // Descomentei o método abstrato para obrigar as filhas a implementar
    public abstract void mostrar();
}