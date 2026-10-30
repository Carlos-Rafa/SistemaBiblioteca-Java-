package Biblioteca;

import Biblioteca.dominio.Biblioteca;
import Biblioteca.dominio.Livro;
import Biblioteca.dominio.Usuario;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        boolean sair = false;

        Biblioteca biblioteca = new Biblioteca();
        Scanner input = new Scanner(System.in);

        while (!sair) {
            System.out.println("\n--------- SISTEMA DE BIBLIOTECA ---------");
            System.out.println("[1] - Cadastrar Livro");
            System.out.println("[2] - Listar Livros");
            System.out.println("[3] - Cadastrar Usuário");
            System.out.println("[4] - Emprestar Livro");
            System.out.println("[5] - Consultar Usuário");
            System.out.println("[6] - Devolver Livro");
            System.out.println("[7] - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(input.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do livro: ");
                    String titulo = input.nextLine();
                    System.out.print("Digite o nome do escritor: ");
                    String escritor = input.nextLine();
                    System.out.print("Informe o ISBN do livro: ");
                    int isbn = Integer.parseInt(input.nextLine());

                    Livro livro = new Livro(titulo, escritor, isbn, true);
                    biblioteca.cadastrarLivro(livro);
                    break;

                case 2:
                    biblioteca.listarLivro();
                    break;

                case 3:
                    System.out.print("Informe o ID do usuário: ");
                    int idUser = Integer.parseInt(input.nextLine());
                    System.out.print("Informe o nome do usuário: ");
                    String nome = input.nextLine();

                    Usuario usuario = new Usuario(idUser, nome);
                    biblioteca.cadastrarUsuario(usuario);
                    break;

                case 4:
                    biblioteca.listarLivro();

                    System.out.print("Que livro você quer emprestado: ");
                    String nomeLivro = input.nextLine();
                    System.out.print("Informe o seu nome: ");
                    String nomeUsuario = input.nextLine();

                    biblioteca.emprestarLivro(nomeLivro, nomeUsuario);
                    break;

                case 5:
                    System.out.print("Informe o ID do usuário: ");
                    int idUsuarioConsulta = Integer.parseInt(input.nextLine());
                    biblioteca.listarUsuario(idUsuarioConsulta);
                    break;

                case 6:
                    System.out.print("Digite o nome do livro: ");
                    String nomeLivroDevolucao = input.nextLine();
                    System.out.print("Digite o nome do usuário: ");
                    String nomeUsuarioDevolucao = input.nextLine();

                    biblioteca.devolverLivro(nomeLivroDevolucao, nomeUsuarioDevolucao);
                    break;

                case 7:
                    System.out.println("BOA LEITURA E VOLTE SEMPRE!");
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        input.close();
    }
}
