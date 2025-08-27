public class User {
    private String nome;
    private String senha;
    private String level; // Admin, Editor, Leitor

    public User(String nome, String senha, String level) {
        this.nome = nome;
        this.senha = senha;
        this.level = level;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getLevel() {
        return level;
    }
}
