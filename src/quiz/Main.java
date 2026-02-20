package quiz;


public class Main {

    public static void main(String[] args) {
        
        MinhaInterface jogo = new MinhaInterface();
        
        Sistema.inicializarDados();
        jogo.gerenciarInterface();
    
    }
}
