package Biblioteca.dominio;

public class Livro {

    private String titulo;
    private String autor;
    private int sbn;
    private boolean status;

    public Livro(String titulo, String autor, int sbn, boolean status) {
        this.titulo = titulo;
        this.autor = autor;
        this.sbn = sbn;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getSbn() {
        return sbn;
    }

    public void setSbn(int sbn) {
        this.sbn = sbn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}