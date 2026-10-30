package Biblioteca.dominio;

public class Usuario {
    private int id;
    private String nome;
    private Livro[] livrosEmprestados = new Livro[3];

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarLivro(Livro livroEscolhido) {
        for (int i = 0; i < livrosEmprestados.length; i++) {
            if (livrosEmprestados[i] == null) {
                livrosEmprestados[i] = livroEscolhido;
                return;
            }
        }
    }

    public void listarLivrosEmprestados() {
        boolean temLivros = false;
        for (Livro l : livrosEmprestados) {
            if (l != null) {
                System.out.println("Livro: " + l.getTitulo());
                System.out.println("Escritor: " + l.getAutor());
                System.out.println("SBN: " + l.getSbn());
                temLivros = true;
            }
        }
        if (!temLivros) {
            System.out.println("Nenhum livro emprestado no momento.");
        }
    }

    public Livro[] getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}