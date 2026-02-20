package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    // --- BANCO DE DADOS NA MEMÓRIA ---
    // "static" significa que essas listas pertencem ao programa todo, não somem.
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static ArrayList<Jogo> listaJogos = new ArrayList<>();
    
    //private int option;
    
    private static MinhaInterface tabela = new MinhaInterface();

    
    // Guarda quem está logado no momento (null = ninguém)
    public static Usuario usuarioLogado = null; 

    // --- 1. CADASTRAR JOGADOR ---
    public static void cadastrarJogador() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- NOVO CADASTRO DE JOGADOR ---");
        
        System.out.print("Login desejado: ");
        String login = teclado.next();
        
        // --- VALIDAÇÃO DE LOGIN ÚNICO ---
        // Vamos percorrer a lista para ver se esse login já existe
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login)) {
                System.out.println("\n❌ ERRO: O login '" + login + "' já está em uso!");
                System.out.println("Por favor, tente um login diferente.");
                return; // Encerra o método aqui mesmo, sem criar a conta
            }
        }
        
        // Se o código chegou aqui, significa que o login é novo!
        // Limpar o buffer do teclado antes de ler o nome (que pode ter espaços)
        teclado.nextLine(); 
        
        System.out.print("Nome completo: ");
        String nome = teclado.nextLine();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
        // Cria o jogador e salva na lista
        Jogador novo = new Jogador(nome, login, senha);
        listaUsuarios.add(novo);
        
        // Auto-login
        usuarioLogado = novo;
        System.out.println("\nConta '" + login + "' criada com sucesso!");
        System.out.println("Você já está logado como " + nome);
    }
    
    // --- 2. CADASTRAR Administrador ---
    public static void cadastrarAdministrador() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- NOVO CADASTRO DE Administrador ---");
        
        System.out.print("Login desejado: ");
        String login = teclado.next();
        
        // --- VALIDAÇÃO DE LOGIN ÚNICO ---
        // Vamos percorrer a lista para ver se esse login já existe
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login)) {
                System.out.println("\n❌ ERRO: O login '" + login + "' já está em uso!");
                System.out.println("Por favor, tente um login diferente.");
                return; // Encerra o método aqui mesmo, sem criar a conta
            }
        }
        
        // Se o código chegou aqui, significa que o login é novo!
        // Limpar o buffer do teclado antes de ler o nome (que pode ter espaços)
        teclado.nextLine(); 
        
        System.out.print("Nome completo: ");
        String nome = teclado.nextLine();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
        // Cria o jogador e salva na lista
        Administrador novo = new Administrador(nome, login, senha);
        listaUsuarios.add(novo);
        
        // Auto-login
        usuarioLogado = novo;
        System.out.println("\nConta '" + login + "' criada com sucesso!");
        System.out.println("Você já está logado como " + nome);
    }
  
    // --- 2. FAZER LOGIN ---
    public static void fazerLogin() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- LOGIN ---");
        
        System.out.print("Login: ");
        String login = teclado.next();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
        // Varre a lista procurando
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                usuarioLogado = u; // Salva quem logou!
                System.out.println("✅ Bem-vindo, " + u.getNome() + "!");
                return; // Sai do método
            }
        }
        
        System.out.println("❌ Login ou senha incorretos.");
    }

    // --- 6. LISTAR USUÁRIOS (Só pra gente ver se tá funcionando) ---
    public static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        if (listaUsuarios.isEmpty()) {
            System.out.println("(Nenhum usuário cadastrado)");
        } else {
            for (Usuario u : listaUsuarios) {
                System.out.println("- " + u.getNome() + " (" + u.getLogin() + ")");
            }
        }
    }
    
    // --- MÉTODOS DE JOGO (Deixei o esqueleto pra preenchermos depois) ---
    
    public static void criarJogo() {
        Scanner teclado = new Scanner(System.in);
        tabela.menuCriarJogo();

        String codigo = "";
        
        // Loop que "prende" o usuário até o PIN ser único
        while (true) {
            System.out.print("Defina um CÓDIGO (PIN) para a sala: ");
            codigo = teclado.nextLine();
            
            boolean jaExiste = false;
            
            // Verifica se o código já está em uso
            for (Jogo j : listaJogos) {
                if (j.getCodigo().equals(codigo)) {
                    jaExiste = true;
                    break; // Se achou um igual, não precisa olhar o resto da lista
                }
            }
            
            if (jaExiste) {
                System.out.println("❌ Erro: Esse código já está em uso por outra sala! Tente outro.");
            } else {
                break; // Se NÃO existe, sai do loop while e continua a criação
            }
        }

        System.out.print("Digite o tema do Quiz: ");
        String tema = teclado.nextLine(); 

        int rodadas = tabela.lerOpcaoSegura("Quantas rodadas terá o jogo? (Ex: 5): ");

        int maxParticipantes = tabela.lerOpcaoSegura("Limite máximo de participantes: ");

        System.out.println("\n--- CONFIGURAÇÃO DE PONTOS POR DIFICULDADE ---");

        int pFacil = tabela.lerOpcaoSegura("Pontos para questões FÁCEIS: ");

        int pMedio = tabela.lerOpcaoSegura("Pontos para questões MÉDIAS: ");

        int pDificil = tabela.lerOpcaoSegura("Pontos para questões DIFÍCEIS: ");
        
        Jogo novoJogo = new Jogo(codigo, tema, rodadas, maxParticipantes);
        novoJogo.setConfigPontuacao(pFacil, pMedio, pDificil);

        listaJogos.add(novoJogo);

        System.out.println("\nSala de '" + tema + "' criada com sucesso!");
        System.out.println("Avise seus amigos para entrarem usando o PIN: " + codigo);
    }
    
    
    
    public static void entrarEmJogo() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- ENTRAR EM UM JOGO ---");
        
        // 2. Pede o código da sala
        System.out.print("Digite o código da sala: ");
        String codigoDigitado = teclado.next();
        
        // 3. BUSCA: Varre a lista procurando um jogo com esse código
        boolean jogoEncontrado = false;
        
        for (Jogo j : listaJogos) {
            // Se o código do jogo na lista for igual ao digitado...
            if (j.getCodigo().equals(codigoDigitado)) {
                
                System.out.println("✅ Sala encontrada! Iniciando...");
                jogoEncontrado = true;
                
                // AQUI A MÁGICA ACONTECE: O Jogo começa!
                j.iniciar(); 
                
                break; // Para de procurar, já achou.
            }
        }
        
        if (jogoEncontrado == false) {
            System.out.println("❌ Erro: Nenhuma sala encontrada com o código " + codigoDigitado);
        }
    }
    // ... dentro da classe Sistema ...

    public static void fazerLogout() {
        if (usuarioLogado == null) {
            System.out.println("\n❌ Você nem está logado para desconectar!");
        } else {
            System.out.println("\n👋 Tchau, " + usuarioLogado.getNome() + "! Volte sempre.");
            usuarioLogado = null; // AQUI É O SEGREDO: Zera a variável
        }
    }
    public static void mostrarRanking(){
        System.out.println("\n--- RANKING ---");
    }
}