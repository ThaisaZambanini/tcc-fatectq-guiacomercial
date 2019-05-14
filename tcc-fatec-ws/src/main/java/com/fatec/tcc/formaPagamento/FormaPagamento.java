package com.fatec.tcc.formaPagamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_forma_pagamento", schema = "dbguiacomercial")
public class FormaPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_forma_pagamento")
	@SequenceGenerator(name = "dbguiacomercial.sq_forma_pagamento", sequenceName = "dbguiacomercial.sq_forma_pagamento", initialValue = 1, allocationSize = 50)
	@Column(name = "co_seq_forma_pagamento", nullable = false)
	@JsonIgnore
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
