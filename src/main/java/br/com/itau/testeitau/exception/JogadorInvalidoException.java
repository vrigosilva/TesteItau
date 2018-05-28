package br.com.itau.testeitau.exception;

import java.util.List;

import org.springframework.validation.ObjectError;

public class JogadorInvalidoException extends Exception{

	private static final long serialVersionUID = 1L;
	private List<ObjectError> errors;
	
	public JogadorInvalidoException(List<ObjectError> list){}
	public JogadorInvalidoException(String mensagem){
		super(mensagem);
	}
	
	@Override
	public String getMessage() {
		String mess="";
		if(errors!= null){
			errors.forEach(e ->  mess.concat(" || " + e.getDefaultMessage()) );
			return mess;
		}
		return super.getMessage();
	}
}
