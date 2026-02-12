package quiz;

public class Administrador extends Usuario {
    
    // Construtor: Apenas repassa os dados para o pai (Usuario)
    public Administrador(String nome, String login, String senha) {
        super(nome, login, senha);
    }
    
    // --- MÉTODOS EXCLUSIVOS DO ADMIN ---
    
    // No futuro, podemos colocar aqui métodos como:
    // cadastrarPergunta(), deletarUsuario(), etc.
    // Por enquanto, deixe vazio.
    
    
    public void mostrarDados() {
        System.out.println("Sou um ADMINISTRADOR: " + this.getNome());
    }
}