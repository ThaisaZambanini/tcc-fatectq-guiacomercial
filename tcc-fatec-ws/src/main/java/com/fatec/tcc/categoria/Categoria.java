package com.fatec.tcc.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	public void setIcone(String icone) {
		this.icone = icone;
	}

	public String getIcone() {
		return icone;
	}

}
