package com.fatec.tcc.endereco;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.cidade.Cidade;

@Entity
@Table(name = "TB_ENDERECO", schema = "dbguiacomercial")
public class Endereco {

	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_endereco", sequenceName = "dbguiacomercial.sq_endereco", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_endereco")
	@Column(name = "co_seq_endereco", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "logradouro")
	private String logradouro;

	@NotNull
	@Column(name = "cep")
	private String cep;

	@NotNull
	@Column(name = "bairro")
	private String bairro;

	@NotNull
	@Column(name = "numero")
	private String numero;

	@Column(name = "complemento")
	private String complemento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cidade")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Cidade cidade;

	@Transient
	private String linha1;

	@Transient
	private String linha2;

	@Transient
	private String linha3;

	@Transient
	private String completo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getLinha1() {
		return getLogradouro() + ", " + getNumero();
	}

	public void setLinha1(String linha1) {
		this.linha1 = linha1;
	}

	public String getLinha2() {
		return getCep() + " - " + getBairro();
	}

	public void setLinha2(String linha2) {
		this.linha2 = linha2;
	}

	public String getLinha3() {
		return linha3;
	}

	public void setLinha3(String linha3) {
		this.linha3 = linha3;
	}

	public String getCompleto() {
		return completo;
	}

	public void setCompleto(String completo) {
		this.completo = completo;
	}

}
