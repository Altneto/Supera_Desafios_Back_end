package br.com.banco.exceptions;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LocalDate data;
    private String mensagem;
    private String detalhes;
    private String uri;

    public ExceptionResponse(LocalDate data, String mensagem, String detalhes, String uri) {
        this.data = data;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.uri = uri;
    }
}

