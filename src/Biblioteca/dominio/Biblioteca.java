package Biblioteca.dominio;

import Biblioteca.interfaces.RegraEmprestimo;

public class Biblioteca implements RegraEmprestimo {
    private final Livro[] acervo = new Livro[5];
    private final Usuario[] usuarios = new Usuario[5];

    public void cadastrarLivro(Livro livro) {
        for (int i = 0; i < acervo.length; i++) {
            if (acervo[i] == null) {
                acervo[i] = livro;
                System.out.println("Livro cadastrado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Acervo cheio! Não é possível cadastrar mais livros.");
    }

    public void cadastrarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;
                System.out.println("Usuário cadastrado com sucesso!");
                return;
            }
        }
        System.out.println("Erro: Limite de usuários atingido!");
    }

    public void listarLivro() {
        boolean encontrou = false;
        for (Livro livro : acervo) {
            if (livro != null && livro.isStatus()) {
                System.out.println("---------- LIVRO ----------");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Escritor: " + livro.getAutor());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro disponível no momento.");
        }
    }

    public void emprestarLivro(String nomeLivro, String nomeUsuario) {
        if (nomeLivro == null || nomeLivro.trim().isEmpty() || nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.out.println("Erro: Nome do livro ou do usuário não pode estar em branco.");
            return;
        }

        Livro livroEscolhido = null;
        Usuario usuarioEncontrado = null;

        for (Livro lista : acervo) {
            if (lista != null && lista.isStatus() && lista.getTitulo().equalsIgnoreCase(nomeLivro.trim())) {
                livroEscolhido = lista;
                break;
            }
        }

        if (livroEscolhido == null) {
            System.out.println("Erro: Livro não encontrado ou já emprestado.");
            return;
        }

        for (Usuario u : usuarios) {
            if (u != null && u.getNome().equalsIgnoreCase(nomeUsuario.trim())) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Erro: Usuário não cadastrado.");
            return;
        }

        if (!validarLimite(usuarioEncontrado)) {
            System.out.println("Empréstimo cancelado: Limite de livros atingido para este usuário.");
            return;
        }

        usuarioEncontrado.adicionarLivro(livroEscolhido);
        livroEscolhido.setStatus(false);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void listarUsuario(int idUsuario) {
        for (Usuario lista : usuarios) {
            if (lista != null && lista.getId() == idUsuario) {
                System.out.println("----- USUÁRIO -----");
                System.out.println("ID: " + lista.getId());
                System.out.println("Nome: " + lista.getNome());
                System.out.println("------ LIVROS EMPRESTADOS -----");
                lista.listarLivrosEmprestados();
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    public void devolverLivro(String nomeLivro, String nomeUsuario) {
        if (nomeLivro == null || nomeLivro.trim().isEmpty() || nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            System.out.println("Erro: Dados inválidos para devolução.");
            return;
        }

        Usuario usuarioEncontrado = null;

        for (Usuario u : usuarios) {
            if (u != null && u.getNome().equalsIgnoreCase(nomeUsuario.trim())) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        Livro[] livrosDoUsuario = usuarioEncontrado.getLivrosEmprestados();
        boolean devolvido = false;

        for (int i = 0; i < livrosDoUsuario.length; i++) {
            if (livrosDoUsuario[i] != null && livrosDoUsuario[i].getTitulo().equalsIgnoreCase(nomeLivro.trim())) {
                livrosDoUsuario[i].setStatus(true);
                livrosDoUsuario[i] = null;
                devolvido = true;
                System.out.println("Livro devolvido com sucesso!");
                break;
            }
        }

        if (!devolvido) {
            System.out.println("Erro: O usuário não possui este livro emprestado.");
        }
    }

    @Override
    public boolean validarLimite(Usuario usuario) {
        int contadorLivro = 0;
        Livro[] livros = usuario.getLivrosEmprestados();

        for (Livro livro : livros) {
            if (livro != null) {
                contadorLivro++;
            }
        }

        return contadorLivro < livros.length;
    }
}