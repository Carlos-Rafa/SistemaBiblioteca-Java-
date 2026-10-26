package Biblioteca.interfaces;

import Biblioteca.dominio.Usuario;

public interface RegraEmprestimo {

    boolean validarLimite(Usuario usuario);
}
