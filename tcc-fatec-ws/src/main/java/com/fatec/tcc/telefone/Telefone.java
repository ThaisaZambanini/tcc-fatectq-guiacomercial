package com.fatec.tcc.telefone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.commons.TipoTelefone;
import com.fatec.tcc.empresa.Empresa;

@Entity
@Table(name = "tb_telefone", schema = "dbguiacomercial")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_telefone")
	@SequenceGenerator(name = "dbguiacomercial.sq_telefone", sequenceName = "dbguiacomercial.sq_telefone", initialValue = 1, allocationSize = 1)
	@Column(name = "co_seq_telefone", nullable = false)
	@JsonIgnore
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTelefone tipo;

	@Column(name = "ddd", nullable = false)
	private Integer ddd;

	@Column(name = "numero", nullable = false)
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresa empresa;

	@Transient
	private String exibicao;

	@Transient
	private String discagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getExibicao() {
		exibicao = "(" + getDdd().toString() + ") " + getNumero();
		return exibicao;
	}

	public void setExibicao(String exibicao) {
		this.exibicao = exibicao;
	}

	public String getDiscagem() {
		if (getTipo() == TipoTelefone.W) {
			discagem = "55" + getDdd().toString() + getNumero();
		} else {
			discagem = "0" + getDdd() + getNumero();
		}
		return discagem.replace("-", "");
	}

	public void setDiscagem(String discagem) {
		this.discagem = discagem;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

}
