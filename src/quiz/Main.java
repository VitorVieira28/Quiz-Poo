package quiz;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in, "UTF-8");
        MinhaInterface jogo = new MinhaInterface();
        
        jogo.gerenciarInterface();
    
    }
    // Método auxiliar para "congelar" a tela até o usuário dar Enter
    /*private static void esperarEnter(Scanner teclado) {
        System.out.print("\n[Pressione ENTER para voltar ao menu...]");
        teclado.nextLine(); // Captura o "Enter" que sobrou do numero anterior
        teclado.nextLine(); // Espera um novo "Enter"
    }*/
}

// Palmeiras Melhor do Brasil