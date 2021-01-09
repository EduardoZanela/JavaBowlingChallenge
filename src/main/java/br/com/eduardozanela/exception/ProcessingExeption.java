package br.com.eduardozanela.exception;

import br.com.eduardozanela.constant.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProcessingExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private ErrorCode messageCode;
	
	public ProcessingExeption(String message, ErrorCode fileReadError) {
		super(message);
		this.messageCode = fileReadError;
	}
	
	
	
}
