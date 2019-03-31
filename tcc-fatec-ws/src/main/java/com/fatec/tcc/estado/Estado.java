package com.fatec.tcc.estado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_UF", schema = "dbguiacomercial")
public class Estado {
	
	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_estado", sequenceName = "dbguiacomercial.sq_estado", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_estado")
	@Column(name = "co_seq_uf", nullable = false)
	private Long id;

	@Column(name = "nome", nullable = false)
	@NotNull
	private String nome;

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
}
