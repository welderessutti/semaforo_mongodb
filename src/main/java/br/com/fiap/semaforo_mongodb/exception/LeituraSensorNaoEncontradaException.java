package br.com.fiap.semaforo_mongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeituraSensorNaoEncontradaException extends RuntimeException {

    public LeituraSensorNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
