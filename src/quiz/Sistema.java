package quiz;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    
    
    private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private static ArrayList<Jogo> listaJogos = new ArrayList<>();
    
  
    
    private static MinhaInterface tabela = new MinhaInterface();

    
    
    public static Usuario usuarioLogado = null; 

   
    public static void cadastrarJogador() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- NOVO CADASTRO DE JOGADOR ---");
        
        System.out.print("Login desejado: ");
        String login = teclado.next();
        
       
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login)) {
                System.out.println("\n❌ ERRO: O login '" + login + "' já está em uso!");
                System.out.println("Por favor, tente um login diferente.");
                return; 
            }
        }
        
        
        teclado.nextLine(); 
        
        System.out.print("Nome completo: ");
        String nome = teclado.nextLine();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
       
        Jogador novo = new Jogador(nome, login, senha);
        listaUsuarios.add(novo);
        
       
        usuarioLogado = novo;
        System.out.println("\nConta '" + login + "' criada com sucesso!");
        System.out.println("Você já está logado como " + nome);
    }
    
   
    public static void cadastrarAdministrador() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- NOVO CADASTRO DE Administrador ---");
        
        System.out.print("Login desejado: ");
        String login = teclado.next();
        
        
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login)) {
                System.out.println("\n❌ ERRO: O login '" + login + "' já está em uso!");
                System.out.println("Por favor, tente um login diferente.");
                return; 
            }
        }
        
       
        teclado.nextLine(); 
        
        System.out.print("Nome completo: ");
        String nome = teclado.nextLine();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
        // Cria o jogador e salva na lista
        Administrador novo = new Administrador(nome, login, senha);
        listaUsuarios.add(novo);
        
       
        usuarioLogado = novo;
        System.out.println("\nConta '" + login + "' criada com sucesso!");
        System.out.println("Você já está logado como " + nome);
    }
  
    public static void fazerLogin() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- LOGIN ---");
        
        System.out.print("Login: ");
        String login = teclado.next();
        
        System.out.print("Senha: ");
        String senha = teclado.next();
        
      
        for (Usuario u : listaUsuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                usuarioLogado = u; 
                System.out.println("✅ Bem-vindo, " + u.getNome() + "!");
                return; 
            }
        }
        
        System.out.println("❌ Login ou senha incorretos.");
    }

   
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
            
        
            System.out.println(tipo + " Nome: " + u.getNome() + " | Login: " + u.getLogin());
        }
        Sistema.pausar();
    }
    
 
    
    public static void criarJogo() {
        Scanner teclado = new Scanner(System.in);
        tabela.menuCriarJogo();

        String codigo = "";
        
       
        while (true) {
            System.out.print("Defina um CÓDIGO (PIN) para a sala: ");
            codigo = teclado.nextLine();
            
            boolean jaExiste = false;
            
            // Verifica se o código já está em uso
            for (Jogo j : listaJogos) {
                if (j.getCodigo().equals(codigo)) {
                    jaExiste = true;
                    break; 
                }
            }
            
            if (jaExiste) {
                System.out.println("❌ Erro: Esse código já está em uso por outra sala! Tente outro.");
            } else {
                break; 
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
        teclado.nextLine(); 

       
        Jogo novoJogo = new Jogo(codigo, tema, rodadas, maxParticipantes);
        
      
        novoJogo.setConfigPontuacao(pFacil, pMedio, pDificil);
        novoJogo.setPontosPerdidosPorPulo(penalidade);
        
       
        listaJogos.add(novoJogo);
        
        System.out.println("\nSala de '" + tema + "' criada com sucesso!");
        System.out.println("Avise seus amigos para entrarem usando o PIN: " + codigo);
    }
    
    
    
    public static void entrarEmJogo() {

        Scanner teclado = new Scanner(System.in);
        System.out.println("\n--- ENTRAR EM UM JOGO ---");
        
       
        System.out.print("Digite o código da sala: ");
        String codigoDigitado = teclado.next();
        
        
        boolean jogoEncontrado = false;
        
        for (Jogo j : listaJogos) {
            
            if (j.getCodigo().equals(codigoDigitado)) {
                
                System.out.println("✔️ Sala encontrada! Iniciando...");
        jogoEncontrado = true;
        
        
        if (j.jaJogou(usuarioLogado)) {
            System.out.println("❌ ERRO: Você já jogou esta partida! Não é permitido jogar novamente.");
            break; 
        }
        if (j.isSalaCheia()) {
            System.out.println("❌ ERRO: A sala está cheia! O limite de participantes foi atingido.");
            break; 
        }
        
        j.iniciar();
        
        
        j.registrarPartida(usuarioLogado);
        Sistema.pausar();
        break; 
            }
        }
        
        if (jogoEncontrado == false) {
            System.out.println("❌ Erro: Nenhuma sala encontrada com o código " + codigoDigitado);
        }
    }
   

    public static void fazerLogout() {
        if (usuarioLogado == null) {
            System.out.println("\n❌ Você nem está logado para desconectar!");
        } else {
            System.out.println("\n👋 Tchau, " + usuarioLogado.getNome() + "! Volte sempre.");
            usuarioLogado = null; 
        }
    }
 public static void mostrarRanking() {
        System.out.println("\n--- TELA DE RANKING ---");
        
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o codigo do jogo: ");
        
        
        String codigoDigitado = teclado.next(); 

        boolean achou = false;

        for (Jogo j : listaJogos) {
            if (j.getCodigo().equals(codigoDigitado)) {
                achou = true;
               System.out.println("\n✔️ Puxando os resultados da sala!");
                
                
                j.exibirRanking(); 
                break;
            }
        }

        if (!achou) {
            System.out.println("❌ Jogo nao encontrado com o codigo: " + codigoDigitado);
        }
        
        Sistema.pausar();
    }
   
    public static void pausar() {
        System.out.println("\n[ Pressione ENTER para voltar ao menu... ]");
        try {
            System.in.read();
            
            while (System.in.available() > 0) {
                System.in.read();
            }
        } catch (Exception e) {
            
        }
    }
    public static void inicializarDados() {
    Administrador adm1 = new Administrador("Vitor Vieira", "vitin", "123");
    Administrador adm2 = new Administrador("Pedro Rei Delas", "PedroH", "123");
    Jogador jog1 = new Jogador("Guiomar Netto", "netto", "123");
    Jogador jog2 = new Jogador("Francisco", "dylas", "123");

    listaUsuarios.add(adm1);
    listaUsuarios.add(adm2);
    listaUsuarios.add(jog1);
    listaUsuarios.add(jog2);
   
}
}