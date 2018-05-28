package br.com.itau.testeitau.model;

public enum ListaOrigemEnum{
	VINGADORES("Vingadores"), 
	LIGA("Liga da Justiça");

	private String descricao;
	
	ListaOrigemEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
}
