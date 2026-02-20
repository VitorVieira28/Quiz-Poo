package quiz;

public class Jogador extends Usuario {
    
    // Construtor do Jogador
    public Jogador(String nome, String login, String senha) {
        
        super(nome, login, senha);
    }
    
    // Método toString: Ajuda a imprimir os dados do jogador
    @Override
    public String toString() {
        return "Jogador: " + this.getNome() + " (" + this.getLogin() + ")";
    }
}