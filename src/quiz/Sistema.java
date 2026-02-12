package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    // --- BANCO DE DADOS NA MEMÓRIA ---
    // "static" significa que essas listas pertencem ao programa todo, não somem.
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static ArrayList<Jogo> listaJogos = new ArrayList<>();
    
    // Guarda quem está logado no momento (null = ninguém)
    public static Usuario usuarioLogado = null; 

    // --- 1. CADASTRAR USUÁRIO ---
  public static void cadastrarUsuario() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- NOVO CADASTRO ---");
        
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
        System.out.println("\n✅ Conta '" + login + "' criada com sucesso!");
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
        // 1. SEGURANÇA: Só cria sala se estiver logado
        if (usuarioLogado == null) {
            System.out.println("\n❌ ERRO: Você precisa estar logado para criar uma sala!");
            return;
        }

        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- CRIAR NOVA SALA ---");
        
        System.out.print("Defina um CÓDIGO para a sala (ex: 1234): ");
        String codigo = teclado.next();
        
        System.out.print("Quantos PONTOS vale cada pergunta? (ex: 10): ");
        int valor = teclado.nextInt();
        
        // Cria o objeto Jogo com os dados
        Jogo novoJogo = new Jogo(codigo, valor);
        
        // Adiciona na lista de jogos do sistema
        listaJogos.add(novoJogo);
        
        System.out.println("✅ Sala " + codigo + " criada com sucesso!");
        System.out.println("Avise seus amigos para entrarem usando a Opção 4.");
    }
    
    public static void entrarEmJogo() {
        // 1. SEGURANÇA: Só joga quem tem login
        if (usuarioLogado == null) {
            System.out.println("\n❌ ERRO: Você precisa fazer LOGIN primeiro para jogar!");
            return;
        }

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
}