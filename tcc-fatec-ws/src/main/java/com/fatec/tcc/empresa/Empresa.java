package com.fatec.tcc.empresa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.endereco.Endereco;
import com.fatec.tcc.formaPagamento.FormaPagamento;
import com.fatec.tcc.horario.Horario;
import com.fatec.tcc.telefone.Telefone;

@Entity
@Table(name = "TB_EMPRESA", schema = "dbguiacomercial")
public class Empresa {

	@Id
	@SequenceGenerator(name = "dbguiacomercial.sq_empresa", sequenceName = "dbguiacomercial.sq_empresa", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_empresa")
	@Column(name = "co_seq_empresa", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "logo")
	private String logo;

	@Column(name = "email")
	private String email;

	@Column(name = "link_site")
	private String linkSite;

	@Column(name = "link_facebook")
	private String linkFacebook;

	@Column(name = "link_instagram")
	private String linkInstagram;

	@Column(name = "link_twitter")
	private String linkTwitter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categoria categoria;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Horario> horarios;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Telefone> telefones;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<FormaPagamento> formaPagamento;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Horario> getHorarios() {
		if (horarios == null) {
			horarios = new ArrayList<>();
		}
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkSite() {
		return linkSite;
	}

	public void setLinkSite(String linkSite) {
		this.linkSite = linkSite;
	}

	public String getLinkFacebook() {
		return linkFacebook;
	}

	public void setLinkFacebook(String linkFacebook) {
		this.linkFacebook = linkFacebook;
	}

	public String getLinkInstagram() {
		return linkInstagram;
	}

	public void setLinkInstagram(String linkInstagram) {
		this.linkInstagram = linkInstagram;
	}

	public String getLinkTwitter() {
		return linkTwitter;
	}

	public void setLinkTwitter(String linkTwitter) {
		this.linkTwitter = linkTwitter;
	}

	public List<Telefone> getTelefones() {
		if (telefones == null) {
			telefones = new ArrayList<>();
		}
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<FormaPagamento> getFormaPagamento() {
		if (formaPagamento == null) {
			formaPagamento = new ArrayList<>();
		}
		return formaPagamento;
	}

	public void setFormaPagamento(List<FormaPagamento> formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

}
