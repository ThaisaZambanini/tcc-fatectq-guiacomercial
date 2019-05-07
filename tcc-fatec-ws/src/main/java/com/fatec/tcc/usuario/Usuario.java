package com.fatec.tcc.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_usuario", schema = "dbguiacomercial")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_usuario")
	@SequenceGenerator(name = "dbguiacomercial.sq_usuario", sequenceName = "dbguiacomercial.sq_usuario", initialValue = 1, allocationSize = 1)
	@Column(name = "co_seq_usuario", nullable = false)
	@JsonIgnore
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "cpf", nullable = false)
	private String cpf;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
