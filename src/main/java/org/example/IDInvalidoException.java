package org.example;

public class IDInvalidoException extends RuntimeException {
    private String mensagem;
    public IDInvalidoException(String s) {
        this.mensagem = s;
    }

    public String getMensagem() {
        return mensagem;
    }
}
