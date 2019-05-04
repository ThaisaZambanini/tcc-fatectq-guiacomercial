package com.fatec.tcc.commons;

public enum TipoTelefone {

	F("Fone"), W("WhatsApp"), C("Celular");

	private TipoTelefone(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
