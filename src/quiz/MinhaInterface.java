package quiz;

import java.util.Scanner;
import java.util.InputMismatchException;

public class MinhaInterface {

    Scanner leitor = new Scanner(System.in);
    
    private int option = -1;

    
    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    
    public int lerOpcaoSegura(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int numero = leitor.nextInt();
                leitor.nextLine(); // Limpa o buffer do teclado (importante!)
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("❌ Erro: Você digitou uma letra ou símbolo!");
                System.out.println("   Por favor, digite apenas números.");
                leitor.nextLine(); // Limpa o "lixo" que o usuário digitou
            }
        }
    }

    public void cabeçalho() {
        System.out.println("\n=================================");
        System.out.println("            SABE-TUDO       ");
        System.out.println("=================================");
    }

    public void menuJogador() {
        cabeçalho();
        System.out.println(" USUÁRIO LOGADO: " + Sistema.usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("(1) Entrar em um jogo (PIN)");
        System.out.println("(2) Ver Ranking");
        System.out.println("(3) Listar Usuários Cadastrados");
        System.out.println("(4) Fazer Logout");
        System.out.println("(0) Sair");
        
        // Usa o método seguro para capturar a opção
        setOption(lerOpcaoSegura("Escolha uma opção: "));
    }

    public void menuAdministrador() {
        cabeçalho();
        System.out.println(" ADMINISTRADOR: " + Sistema.usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("(1) Criar Novo Jogo (Sala)");
        System.out.println("(2) Ver Ranking");
        System.out.println("(3) Listar Usuários Cadastrados");
        System.out.println("(4) Fazer Logout");
        System.out.println("(0) Sair");
        
        setOption(lerOpcaoSegura("Escolha uma opção: "));
    }

    public void menuCriarJogo() {
        cabeçalho();
        System.out.println(" ADMINISTRADOR: " + Sistema.usuarioLogado.getNome());
        System.out.println("=================================");
        System.out.println("        --- CRIAR JOGO ---\n");
    }

    public void menuLogin() {
        cabeçalho();
        System.out.println(" STATUS: Desconectado (Faça login!)");
        System.out.println("=================================");
        System.out.println("(1) Criar uma conta de Jogador");
        System.out.println("(2) Criar uma conta de Administrador");
        System.out.println("(3) Fazer login");
        System.out.println("(4) Listar Usuários Cadastrados");
        System.out.println("(0) Para sair");
        
        setOption(lerOpcaoSegura("Escolha uma opção: "));
    }

    public void gerenciarInterface() {
        do {
            if (Sistema.usuarioLogado == null) {
                menuLogin();
                if (getOption() == 1) {
                    Sistema.cadastrarJogador();
                } else if (getOption() == 2) {
                    Sistema.cadastrarAdministrador();
                } else if (getOption() == 3) {
                    Sistema.fazerLogin();
                } else if (getOption() == 0) {
                    System.out.println("Saindo... Até mais!");
                    
                } else if(getOption() == 4){
                    Sistema.listarUsuarios();
                } else {
                    System.out.println("Opção inválida!");
                }
            } 
            else if (Sistema.usuarioLogado instanceof Administrador) {
                menuAdministrador();
                if (getOption() == 1) {
                    Sistema.criarJogo();
                } else if (getOption() == 2) {
                    System.out.println("--- RANKING ---");
                } else if (getOption() == 3) {
                    Sistema.listarUsuarios();
                } else if (getOption() == 4) {
                    System.out.println("Deslogando...");
                    Sistema.fazerLogout();
                } else if (getOption() == 0) {
                    System.out.println("Saindo... Até mais!");
                } else {
                    System.out.println("Opção inválida!");
                }
            } 
            else {
                menuJogador();
                if (getOption() == 1) {
                    Sistema.entrarEmJogo();
                } else if (getOption() == 2) {
                    Sistema.mostrarRanking();
                } else if (getOption() == 3) {
                    Sistema.listarUsuarios();
                } else if (getOption() == 4) {
                    System.out.println("Deslogando...");
                    Sistema.fazerLogout();
                } else if (getOption() == 0) {
                    System.out.println("Saindo... Até mais!");
                } else {
                    System.out.println("Opção inválida!");
                }
            }

            
            if (getOption() != 0) {
                setOption(-1);
            }

        } while (getOption() != 0);
    }
}