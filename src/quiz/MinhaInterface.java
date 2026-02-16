package quiz;

import java.util.Scanner;
import java.util.InputMismatchException; // Importante para o erro de letra

public class MinhaInterface {
    Scanner leitor = new Scanner(System.in); // Removi o "UTF-8" forçado se preferir o padrão
    private int option = -1; // Começa com -1 para não confundir com 0 (sair)
    
    // Getters e Setters
    public int getOption(){    
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    /*// Método auxiliar para ler números com segurança
    // Isso evita que você tenha que repetir o try-catch em todo lugar
    private void lerOpcaoSegura() {
        try {
            System.out.print("Escolha uma opção: ");
            int numero = leitor.nextInt();
            setOption(numero);
        } catch (InputMismatchException e) {
            System.out.println("❌ Erro: Você digitou uma letra ou símbolo!");
            System.out.println("   Por favor, digite apenas números.");
            leitor.nextLine(); // LIMPA O BUFFER (O "lixo" que o usuário digitou)
            setOption(-1); // Define uma opção inválida para o loop repetir
        }
    }*/

    // Menus (Apenas mostram o texto agora)
    public void cabeçalho(){
        System.out.println("\n=================================");
        System.out.println("            SABE-TUDO       ");
        System.out.println("=================================");
    }
    public void menuJogador(){
        cabeçalho();
        System.out.println(" USUÁRIO LOGADO: " + Sistema.usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("(6) Ver Ranking");
        System.out.println("(7) Listar Usuários Cadastrados");
        System.out.println("(8) Fazer Logout");
        System.out.println("(9) Entrar em um jogo (PIN)");
        System.out.println("(0) Sair");
        System.out.print("Escolha uma opção: ");
        setOption(leitor.nextInt());
        //lerOpcaoSegura(); // Chama a leitura protegida
    }

    public void menuAdministrador(){
        cabeçalho();
        System.out.println(" ADMINISTRADOR: " + Sistema.usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("(5) Criar Novo Jogo (Sala)");
        System.out.println("(6) Ver Ranking");
        System.out.println("(7) Listar Usuários Cadastrados");
        System.out.println("(8) Fazer Logout");
        System.out.println("(0) Sair");
        System.out.print("Escolha uma opção: ");
        setOption(leitor.nextInt());
        //lerOpcaoSegura(); // Chama a leitura protegida
    }
    
    public void menuCriarJogo(){
        
    }

    public void menuLogin(){
        cabeçalho();
        System.out.println(" STATUS: Desconectado (Faça login!)");
        System.out.println("=================================");
        System.out.println("(1) Criar uma conta de Jogador");
        System.out.println("(2) Criar uma conta de Administrador"); // Exemplo
        System.out.println("(3) Fazer login");
        System.out.println("(0) Para sair");
        //lerOpcaoSegura(); // Chama a leitura protegida
        System.out.print("Escolha uma opção: ");
        setOption(leitor.nextInt());
    }

    // Lógica Principal
    public void gerenciarInterface() {
        do {
            // 1. DECIDE QUAL MENU MOSTRAR
            if (Sistema.usuarioLogado == null) {
                menuLogin();
            } 
            else if (Sistema.usuarioLogado instanceof Administrador) {
                menuAdministrador();
            } 
            else {
                menuJogador();
            }

            // 2. PROCESSA A OPÇÃO ESCOLHIDA (USANDO IF/ELSE)
            if (getOption() == 1) {
                System.out.println("      --- CRIAR CONTA ---");
                Sistema.cadastrarJogador();
            }
            else if (getOption() == 2) {
                System.out.println("--- CRIAR CONTA ADMINISTRADOR ---");
                Sistema.cadastrarAdministrador();
            }
            else if (getOption() == 3) {
                System.out.println("--- LOGIN ---");
                Sistema.fazerLogin();
            }
            else if (getOption() == 5) {
                // Só permite se for ADM
                if (Sistema.usuarioLogado instanceof Administrador) {
                    System.out.println("--- CRIAR JOGO ---");
                    Sistema.criarJogo();
                } else {
                    System.out.println("Opção inválida para jogadores!");
                }
            }
            else if (getOption() == 6) {
                System.out.println("--- RANKING ---");
                // Lógica de ranking
            }
            else if (getOption() == 7) {
                System.out.println("--- LISTA DE USUÁRIOS ---");
                Sistema.listarUsuarios();
            }
            else if (getOption() == 8) {
                System.out.println("Deslogando...");
                Sistema.fazerLogout();
            }
            else if (getOption() == 9) {
                 // Só permite se NÃO for ADM (ou seja, jogador)
                 if (!(Sistema.usuarioLogado instanceof Administrador)) {
                    System.out.println("--- ENTRAR NO JOGO ---");
                    Sistema.entrarEmJogo();
                 }
            }
            else if (getOption() == 0) {
                System.out.println("Saindo... Até mais!");
            }
            else if (getOption() == -1) {
                // Não faz nada, apenas repete o loop (erro de digitação)
            }
            else {
                System.out.println("Opção inválida!");
            }
            
        } while(getOption() != 0);
    }
}