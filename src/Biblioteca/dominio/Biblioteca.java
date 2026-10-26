package Biblioteca.dominio;

import Biblioteca.interfaces.RegraEmprestimo;

public class Biblioteca implements RegraEmprestimo {
    Livro[] acervo = new Livro[100];
    Usuario[] usuarios = new Usuario[100];
    private int id_livro = 0;

    public void cadastrarLivro(Livro livro) {
        for (int i = 0; i < acervo.length; i++) {
            if (acervo[i] == null) {
                acervo[i] = livro;
                id_livro++;
                return;
            }
        }
    }

    public void cadastrarUsuario(Usuario user) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = user;
                return;
            }
        }
    }

    public void listarLivro() {
        boolean encontrou = false;
        for (Livro lista : acervo) {
            if (lista != null && lista.status) {
                System.out.println("----------LIVRO----------");
                System.out.println("Titulo: " + lista.titulo);
                System.out.println("Escritor: " + lista.autor);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum livro disponível no momento.");
        }
    }

    public void emprestarLivro(String nome_livro, String nome_usuario) {
        // Validação contra entradas vazias/nulas
        if (nome_livro == null || nome_livro.trim().isEmpty() ||
                nome_usuario == null || nome_usuario.trim().isEmpty()) {
            System.out.println("Erro: Nome do livro ou do usuário não pode estar em branco.");
            return;
        }

        Livro livro_escolhido = null;
        Usuario usuario_encontrado = null;

        // 1. Localiza o livro disponível no acervo
        for (Livro lista : acervo) {
            if (lista != null && lista.status && lista.titulo.equalsIgnoreCase(nome_livro.trim())) {
                livro_escolhido = lista;
                break;
            }
        }

        if (livro_escolhido == null) {
            System.out.println("Erro: Livro não encontrado ou já emprestado.");
            return;
        }

        // 2. Localiza o usuário
        for (Usuario u : usuarios) {
            if (u != null && u.getNome().equalsIgnoreCase(nome_usuario.trim())) {
                usuario_encontrado = u;
                break;
            }
        }

        if (usuario_encontrado == null) {
            System.out.println("Erro: Usuário não cadastrado.");
            return;
        }

        // 3. Verifica o limite e realiza o empréstimo
        if (!validarLimite(usuario_encontrado)) {
            System.out.println("Empréstimo cancelado: Limite de livros atingido para este usuário.");
            return;
        }

        usuario_encontrado.AdicionarLivro(livro_escolhido);
        livro_escolhido.status = false;
        System.out.println("Empréstimo realizado com sucesso!");
    }

    private void listarLivrosDisponiveis() {
        for (Livro lista : acervo) {
            if (lista != null && lista.status) {
                System.out.println("----------LIVRO DISPONÍVEL----------");
                System.out.println("Titulo: " + lista.titulo);
                System.out.println("Escritor: " + lista.autor);
            }
        }
    }

    public void listarUsuario(int id_usuario) {
        for (Usuario lista : usuarios) {
            if (lista != null && lista.getId() == id_usuario) {
                System.out.println("-----USUARIO-----");
                System.out.println("ID: " + lista.getId());
                System.out.println("Nome: " + lista.nome);
                System.out.println("------LIVROS EMPRESTADOS-----");
                lista.ListarLivrosEmprestados();
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    // Método de Devolução de Livro
    public void devolverLivro(String nome_livro, String nome_usuario) {
        if (nome_livro == null || nome_livro.trim().isEmpty() ||
                nome_usuario == null || nome_usuario.trim().isEmpty()) {
            System.out.println("Erro: Dados inválidos para devolução.");
            return;
        }

        Usuario usuario_encontrado = null;

        for (Usuario u : usuarios) {
            if (u != null && u.getNome().equalsIgnoreCase(nome_usuario.trim())) {
                usuario_encontrado = u;
                break;
            }
        }

        if (usuario_encontrado == null) {
            System.out.println("Erro: Usuário não encontrado.");
            return;
        }

        // Procura o livro no array de empréstimos do usuário
        Livro[] livrosDoUsuario = usuario_encontrado.getLivrosEmprestados();
        boolean devolvido = false;

        for (int i = 0; i < livrosDoUsuario.length; i++) {
            if (livrosDoUsuario[i] != null && livrosDoUsuario[i].titulo.equalsIgnoreCase(nome_livro.trim())) {
                // Atualiza o status no acervo
                livrosDoUsuario[i].status = true;
                // Remove o livro do usuário
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
        int contador_livro = 0;
        Livro[] livros = usuario.getLivrosEmprestados();

        for (int i = 0; i < livros.length; i++) {
            if (livros[i] != null) {
                contador_livro++;
            }
        }

        // Retorna false se o número de livros for MAIOR OU IGUAL ao tamanho do array (limite atingido)
        if (contador_livro >= livros.length) {
            return false;
        }

        return true;
    }
}