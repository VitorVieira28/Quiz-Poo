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
        System.out.println("\n--- LISTA DE USUÁRIOS CADASTRADOS ---");
        
        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado ainda.");
            return;
        }

        for (Usuario u : listaUsuarios) {
            String tipo = "";
            
            // Verifica de qual classe o objeto é filho
            if (u instanceof Administrador) {
                tipo = "[ADMINISTRADOR]";
            } else if (u instanceof Jogador) {
                tipo = "[JOGADOR]";
            }
            
            // Imprime com o tipo na frente
            System.out.println(tipo + " Nome: " + u.getNome() + " | Login: " + u.getLogin());
        }
        Sistema.pausar();
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
        
       System.out.print("Quantos pontos o jogador perde se pular uma pergunta? ");
        int penalidade = teclado.nextInt();
        teclado.nextLine(); // Boa prática: limpa o buffer depois de ler um int

        // 1. PRIMEIRO: Cria o jogo na memória
        Jogo novoJogo = new Jogo(codigo, tema, rodadas, maxParticipantes);
        
        // 2. DEPOIS: Configura tudo nele
        novoJogo.setConfigPontuacao(pFacil, pMedio, pDificil);
        novoJogo.setPontosPerdidosPorPulo(penalidade); // Agora ele já conhece o novoJogo!
        
        // 3. POR FIM: Adiciona na lista
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
                
                System.out.println("✔️ Sala encontrada! Iniciando...");
        jogoEncontrado = true;
        
        // 1. BLOQUEIO: Verifica se já jogou antes de iniciar
        if (j.jaJogou(usuarioLogado)) {
            System.out.println("❌ ERRO: Você já jogou esta partida! Não é permitido jogar novamente.");
            break; // Sai do loop e volta pro menu
        }
        if (j.isSalaCheia()) {
            System.out.println("❌ ERRO: A sala está cheia! O limite de participantes foi atingido.");
            break; // Volta pro menu
        }
        // 2. O Jogo Roda Normal
        j.iniciar();
        
        // 3. REGISTRO: O jogo acabou! Vamos salvar que ele já jogou.
        j.registrarPartida(usuarioLogado);
        Sistema.pausar();
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
   public static void mostrarRanking() {
        System.out.println("\n--- RANKING ---");
        
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.print("Digite o código do jogo: ");
        String codigoDigitado = teclado.next(); // Troquei para .next() para ficar igual ao entrarEmJogo()

        Jogo jogoEncontrado = null;
        for (Jogo j : listaJogos) {
            if (j.getCodigo().equals(codigoDigitado)) { 
                jogoEncontrado = j;
                break;
            }
        }

        if (jogoEncontrado == null) {
            System.out.println("❌ Jogo não encontrado com este código!");
        } else {
            
            jogoEncontrado.exibirRanking();
        }
        Sistema.pausar();
    }
   // Método utilitário para pausar a tela
    public static void pausar() {
        System.out.println("\n[ Pressione ENTER para voltar ao menu... ]");
        try {
            System.in.read();
            // Limpa qualquer "lixo" que tenha ficado no teclado
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            // Se der erro de leitura, apenas ignora e segue a vida
        }
    }
    public static void inicializarDados() {
    Administrador adm1 = new Administrador("Vitor Vieira", "vitin", "123");
    Administrador adm2 = new Administrador("Pedro Henrique", "PedroH", "123");
    Jogador jog1 = new Jogador("Guiomar Netto", "netto", "123");
    Jogador jog2 = new Jogador("Francisco", "dylas", "123");

    listaUsuarios.add(adm1);
    listaUsuarios.add(adm2);
    listaUsuarios.add(jog1);
    listaUsuarios.add(jog2);
    
    // Linha de print removida para não aparecer nada no console ao iniciar
}
}