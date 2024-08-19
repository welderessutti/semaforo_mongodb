package br.com.fiap.semaforo_mongodb.advice;

import br.com.fiap.semaforo_mongodb.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

//    // Essa exceção de token expirado não está sendo capturado, preciso verificar como fazer
//    // para ser capturada pelo handler.
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(TokenExpiredException.class)
//    public Map<String, String> handleTokenExpired(TokenExpiredException error) {
//        Map<String, String> errorMap = new HashMap<>();
//        errorMap.put("erro", "Token expirado!");
//        System.out.println("TokenExpiredException capturada!");
//        return errorMap;
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<>();
        List<FieldError> campos = error.getBindingResult().getFieldErrors();

        for (FieldError campo : campos) {
            errorMap.put(campo.getField(), campo.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrityViolation(DataIntegrityViolationException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Usuário já cadastrado!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public Map<String, String> handleOptimisticLockingFailure(OptimisticLockingFailureException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", "Versão do documento inválida!");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public Map<String, String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SemaforoNaoEncontradoException.class)
    public Map<String, String> handleSemaforoNaoEncontrado(SemaforoNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SensorNaoEncontradoException.class)
    public Map<String, String> handleSensorNaoEncontrado(SensorNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LeituraSensorNaoEncontradaException.class)
    public Map<String, String> handleLeituraSensorNaoEncontrada(LeituraSensorNaoEncontradaException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventoNaoEncontradoException.class)
    public Map<String, String> handleEventoNaoEncontrado(EventoNaoEncontradoException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SemaforoPossuiReferenciaException.class)
    public Map<String, String> handleSemaforoPossuiReferencia(SemaforoPossuiReferenciaException error) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("erro", error.getMessage());
        return errorMap;
    }
}
