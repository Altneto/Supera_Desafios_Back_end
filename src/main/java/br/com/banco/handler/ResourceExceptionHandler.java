package br.com.banco.handler;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.banco.exceptions.DataNotFoundException;
import br.com.banco.exceptions.ExceptionResponse;
import br.com.banco.exceptions.ParamNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> dataNotFoundException(DataNotFoundException e, HttpServletRequest request) {
        String error = "Dado não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse err = new ExceptionResponse(LocalDate.now(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
	
	@ExceptionHandler(ParamNotFoundException.class)
    public ResponseEntity<ExceptionResponse> paramNotFoundException(DataNotFoundException e, HttpServletRequest request) {
        String error = "Parametro não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse err = new ExceptionResponse(LocalDate.now(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
