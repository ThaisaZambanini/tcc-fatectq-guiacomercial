package com.fatec.tcc.commons;

public class MensagemRetorno {

	private String titulo;
	private String mensagem;
	
	public MensagemRetorno(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}
	
	public MensagemRetorno() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
