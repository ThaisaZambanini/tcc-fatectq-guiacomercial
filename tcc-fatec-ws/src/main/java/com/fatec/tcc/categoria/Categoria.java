package com.fatec.tcc.categoria;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.commons.SimNao;

@Entity
@Table(name = "TB_CATEGORIA", schema = "dbguiacomercial")
public class Categoria {

	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_categoria", sequenceName = "dbguiacomercial.sq_categoria", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_categoria")
	@Column(name = "co_seq_categoria", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@NotNull
	@Column(name = "icone", nullable = false)
	private String icone;

	@Transient
	private Cidade cidade;

	@Enumerated(EnumType.STRING)
	@Column(name = "st_ativo")
	@JsonIgnore
	private SimNao ativo;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "rl_categoria_cidade", joinColumns = { @JoinColumn(name = "id_cidade") }, inverseJoinColumns = {
			@JoinColumn(name = "id_categoria") })
	private Set<Cidade> cidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIcone() {
		return icone;
	}

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

}
