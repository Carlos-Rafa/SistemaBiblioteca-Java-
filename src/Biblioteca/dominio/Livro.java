package Biblioteca.dominio;

public class Livro  {

    public String titulo;
    public String autor;
    public int sbn;
    public boolean status;

    public Livro( String titulo, String autor, int sbn, boolean status) {

        this.titulo = titulo;
        this.autor = autor;
        this.sbn = sbn;
        this.status = status;
    }



    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getSbn() {
        return sbn;
    }

    public boolean isStatus() {
        return status;
    }
}
