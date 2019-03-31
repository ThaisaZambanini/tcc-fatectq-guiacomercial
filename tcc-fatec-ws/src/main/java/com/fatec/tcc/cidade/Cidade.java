package com.fatec.tcc.cidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fatec.tcc.estado.Estado;

@Entity
@Table(name = "TB_CIDADE", schema = "dbguiacomercial")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@NotNull
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotNull
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO")
	private Estado estado;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
