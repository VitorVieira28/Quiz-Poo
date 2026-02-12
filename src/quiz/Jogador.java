package quiz;

public class Jogador extends Usuario {
    
    // Construtor do Jogador
    public Jogador(String nome, String login, String senha) {
        // O "super" pega os dados recebidos e manda para a classe Usuario guardar
        super(nome, login, senha);
    }
    
    // Método toString: Ajuda a imprimir os dados do jogador de forma bonita no console
    @Override
    public String toString() {
        return "Jogador: " + this.getNome() + " (" + this.getLogin() + ")";
    }
}