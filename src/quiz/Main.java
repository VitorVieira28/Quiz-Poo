package quiz;


public class Main {

    public static void main(String[] args) {
        try {
    System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
    }   catch (java.io.UnsupportedEncodingException e) {
    }
        MinhaInterface jogo = new MinhaInterface();
        
        Sistema.inicializarDados();
        jogo.gerenciarInterface();
        
    
    }
}
