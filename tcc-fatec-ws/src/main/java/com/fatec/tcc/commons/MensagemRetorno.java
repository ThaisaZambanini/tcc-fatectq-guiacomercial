package com.fatec.tcc.commons;

public class MensagemRetorno {

	/** 0 - Sucesso, 1 - Falha */
	private int codigoRetorno;
	private String titulo;
	private String mensagem;

	public MensagemRetorno(String titulo, String mensagem) {
		this.titulo = titulo;
		this.mensagem = mensagem;
	}

	public MensagemRetorno() {
	}

	public MensagemRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
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

	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

}
