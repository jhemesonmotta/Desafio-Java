package com.jhemeson.desafiojava.exceptions;

public class VotoExistenteException extends Exception {
    public VotoExistenteException(String cpf, Long idSessao) {
        super("O associado " + cpf + " já votou na sessão " + idSessao + ". Apenas um voto é permitido.");
    }
}
