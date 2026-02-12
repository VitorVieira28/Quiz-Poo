package quiz;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;
        
        // Loop infinito do menu (só sai se digitar 0)
        do {
            // --- CABEÇALHO DINÂMICO ---
            System.out.println("\n=================================");
            System.out.println("      SISTEMA DE QUIZ POO        ");
            System.out.println("=================================");
            
            // SE TIVER ALGUÉM LOGADO, MOSTRA O NOME. SE NÃO, MOSTRA "DESCONECTADO"
            if (Sistema.usuarioLogado != null) {
                System.out.println("👤 USUÁRIO LOGADO: " + Sistema.usuarioLogado.getNome());
            } else {
                System.out.println("🔴 STATUS: Desconectado (Faça login!)");
            }
            System.out.println("=================================");
            System.out.println("\n=== SISTEMA DE QUIZ ===");
            System.out.println("(1) Criar Conta");
            System.out.println("(2) Fazer Login");
            System.out.println("(3) Criar Novo Jogo (Sala)");
            System.out.println("(4) Entrar em um Jogo (Via Código)");
            System.out.println("(5) Ver Ranking de um Jogo");
            System.out.println("(6) Listar Usuários Cadastrados");
            System.out.println("(7) Fazer Logout (Desconectar)");
            System.out.println("(0) Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.println("--- CRIAR CONTA ---");
                    Sistema.cadastrarUsuario();
                    esperarEnter(teclado);
                    break;
                case 2:
                    System.out.println("--- LOGIN ---");
                    Sistema.fazerLogin();
                    esperarEnter(teclado);
                    break;
                case 3:
                    System.out.println("--- CRIAR SALA DE JOGO ---");
                    Sistema.criarJogo();
                    esperarEnter(teclado);
                    break;
                case 4:
                    System.out.println("--- ENTRAR NO JOGO ---");
                    Sistema.entrarEmJogo();
                    esperarEnter(teclado);
                    break;
                case 5:
                    System.out.println("--- RANKING ---");
                    esperarEnter(teclado);
                    // Logica de mostrar ranking
                    break;
                case 6:
                    Sistema.listarUsuarios();
                    esperarEnter(teclado);
                    break;
                case 7:
                    Sistema.fazerLogout();
                    esperarEnter(teclado);// Chama o método que criamos
                    break;
                case 0:
                    System.out.println("Saindo... Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
    }
    // Método auxiliar para "congelar" a tela até o usuário dar Enter
    private static void esperarEnter(Scanner teclado) {
        System.out.print("\n[Pressione ENTER para voltar ao menu...]");
        teclado.nextLine(); // Captura o "Enter" que sobrou do numero anterior
        teclado.nextLine(); // Espera um novo "Enter"
    }
}