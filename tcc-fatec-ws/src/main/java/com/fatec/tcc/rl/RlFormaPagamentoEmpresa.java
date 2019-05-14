package com.fatec.tcc.rl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.empresa.Empresa;
import com.fatec.tcc.formaPagamento.FormaPagamento;

@Entity
@Table(name = "rl_forma_pagamento_empresa", schema = "dbguiacomercial")
public class RlFormaPagamentoEmpresa {

	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_rl_forma_empresa", sequenceName = "dbguiacomercial.sq_rl_forma_empresa", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_rl_forma_empresa")
	@Column(name = "co_seq_rl_forma_empresa", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empresa empresa;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_forma_pagamento", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private FormaPagamento formaPagamento;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
