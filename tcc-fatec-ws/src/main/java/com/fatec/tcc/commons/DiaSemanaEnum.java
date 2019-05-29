package com.fatec.tcc.commons;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DiaSemanaEnum {

	SEGUNDA(1, "Segunda"), TERCA(2, "Terça"), QUARTA(3, "Quarta"), QUINTA(4, "Quinta"), SEXTA(5, "Sexta"), SABADO(6,
			"Sábado"), DOMINGO(7, "Domingo");

	private int order;
	private String descricao;

	private DiaSemanaEnum(int order, String descricao) {
		this.order = order;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getOrder() {
		return order;
	}

	@JsonCreator
	public static DiaSemanaEnum fromString(String string) {
		return fromString(string);
	}

}
