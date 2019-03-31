package com.fatec.tcc.commons;

public enum SimNao {

	S("Ativo"), N("Inativo");

	SimNao(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
