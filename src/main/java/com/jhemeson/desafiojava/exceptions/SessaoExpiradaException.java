package com.jhemeson.desafiojava.exceptions;

public class SessaoExpiradaException extends Exception {
    public SessaoExpiradaException(Long idSessao) {
        super("A sessão com o ID " + idSessao + " expirou.");
    }
}
