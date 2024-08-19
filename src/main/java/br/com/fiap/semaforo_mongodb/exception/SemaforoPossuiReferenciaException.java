package br.com.fiap.semaforo_mongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SemaforoPossuiReferenciaException extends RuntimeException {

    public SemaforoPossuiReferenciaException(String mensagem) { super(mensagem); }
}
