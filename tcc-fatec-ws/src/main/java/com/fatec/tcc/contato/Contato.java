package com.fatec.tcc.contato;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CONTATO", schema = "dbguiacomercial")
public class Contato {

	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_contato", sequenceName = "dbguiacomercial.sq_contato", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_contato")
	@Column(name = "co_seq_contato", nullable = false)
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "assunto", nullable = false)
	private String assunto;

	@Column(name = "mensagem", nullable = false)
	private String mensagem;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
