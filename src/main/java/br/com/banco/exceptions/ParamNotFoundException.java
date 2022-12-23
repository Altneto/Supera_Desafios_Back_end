package br.com.banco.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public ParamNotFoundException(String message) {
		this.message = message;
	}
}
