package com.jhemeson.desafiojava.exceptions;

public class CPFInvalidoException extends Exception {
    public CPFInvalidoException(String cpf) {
        super("O CPF " + cpf + " é inválido.");
    }
}
