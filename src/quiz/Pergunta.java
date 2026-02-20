package quiz;

public abstract class Pergunta {
    
    private String enunciado;
    private String respostaCorreta;
    private String dificuldade;
    

    public Pergunta(String enunciado, String respostaCorreta, String dificuldade) {
        this.enunciado = enunciado;
        this.respostaCorreta = respostaCorreta;
        this.dificuldade = dificuldade;
    }

    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }

    public String getRespostaCorreta() { return respostaCorreta; }
    public void setRespostaCorreta(String respostaCorreta) { this.respostaCorreta = respostaCorreta; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
    
    
    public boolean corrigir(String respostaUsuario) {
        if (this.respostaCorreta.equalsIgnoreCase(respostaUsuario)) {
            System.out.println("✅ ACERTOU!");
            return true;
        } else {
            System.out.println("❌ ERROU! A resposta certa era: " + this.respostaCorreta);
            return false;
        }
    }
    
 
    public abstract void mostrar();
}