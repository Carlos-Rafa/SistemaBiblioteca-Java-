package Biblioteca.dominio;

public class Usuario {
    private int id;
    public String nome;
    private Livro [] livrosEmprestados = new Livro[3];

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public void AdicionarLivro(Livro livroEscolhido) {

        for (int i =0; i < livrosEmprestados.length ; i++){
            if ( livrosEmprestados[i] == null){
                livrosEmprestados[i] = livroEscolhido;
                return;
            }
        }
    }

    public void ListarLivrosEmprestados() {
        for(Livro l: livrosEmprestados){
            if(l != null) {
                System.out.println("Livro: " + l.titulo);
                System.out.println("Escritor: " + l.autor);
                System.out.println("SBN" + l.sbn);
            }
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
