package quiz;


public class Main {

    public static void main(String[] args) {
        Sistema.inicializarDados();
        MinhaInterface jogo = new MinhaInterface();
        
        jogo.gerenciarInterface();
    
    }
}
