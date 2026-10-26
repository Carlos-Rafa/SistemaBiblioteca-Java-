package Biblioteca;

import Biblioteca.dominio.Biblioteca;
import Biblioteca.dominio.Livro;
import Biblioteca.dominio.Usuario;

import java.util.Scanner;

public class teste {
    public static void main(String[] args) {

        boolean cont = false;



        Biblioteca biblioteca = new Biblioteca();
        Scanner input = new Scanner(System.in);

        while (!cont) {

            System.out.println("---------SISTEMA DE BIBLIOTECA---------");


            System.out.println("[1] - Cadastrar Livro");
            System.out.println("[2] - Listar Livros");
            System.out.println("[3] - Cadastrar Usuario");
            System.out.println("[4] - Emprestar Livro");
            System.out.println("[5] - Consultar Usuario");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opcao: ");
            int op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1:


                    System.out.print("Digite o nome do livro: ");
                    String titulo = input.nextLine();
                    System.out.print("Digite o nome do escritor: ");
                    String escritor = input.nextLine();
                    System.out.print("Informe o SBN do livro: ");
                    int sbn = Integer.parseInt(input.nextLine());


                    Livro livro = new Livro(titulo, escritor, sbn, true);
                    biblioteca.cadastrarLivro(livro);
                    break;

                case 2:
                    biblioteca.listarLivro();
                    break;

                case 3:

                    System.out.print("Informe o id do usuario: ");
                    int id_user = Integer.parseInt(input.nextLine());
                    System.out.print("Informe o nome do usuario: ");
                    String nome = input.nextLine();
                    Usuario usuario = new Usuario(id_user, nome);
                    biblioteca.cadastrarUsuario(usuario);
                    break;

                case 4:
                    biblioteca.listarLivro();

                    System.out.println("Que livro voce quer emprestado: ");
                    String nome_livro = input.nextLine();
                    System.out.println("Informe o seu nome: ");
                    String nome_usario = input.nextLine();
                    biblioteca.emprestarLivro(nome_livro,nome_usario);
                    break;

                case 5:
                    System.out.print("Informe o id do usuario: ");
                    int source_id = Integer.parseInt(input.nextLine());
                    biblioteca.listarUsuario(source_id);
                    break;

                case 6:
                    System.out.println("BOA LEITURA E VOLTE SEMPRE!");
                    cont = true;

            }
        }


    }
}
