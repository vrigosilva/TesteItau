package br.com.itau.testeitau.exception;

public class JogadorNaoEncontradoException extends Exception{

	private static final long serialVersionUID = 1L;
	

	public JogadorNaoEncontradoException(){	}

	public JogadorNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	
}
