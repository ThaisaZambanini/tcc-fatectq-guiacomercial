package com.fatec.tcc.empresa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.endereco.Endereco;
import com.fatec.tcc.formaPagamento.FormaPagamento;
import com.fatec.tcc.horario.Horario;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresa;
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categoria categoria;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Horario> horarios;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private Set<Telefone> telefones;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private Set<RlFormaPagamentoEmpresa> formaPagamento;

	@Transient
	private List<FormaPagamento> listaFormaPagamento;

	@Transient
	private List<Telefone> listaTelefonesRemovidos;

	@Transient
	private List<Horario> listaHorariosRemovidos;

	@Transient
	private List<FormaPagamento> listaFormaPagamentoRemovido;

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

	public Set<Telefone> getTelefones() {
		if (telefones == null) {
			telefones = new HashSet<>();
		}
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<RlFormaPagamentoEmpresa> getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(Set<RlFormaPagamentoEmpresa> formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(List<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public List<Telefone> getListaTelefonesRemovidos() {
		if (listaTelefonesRemovidos == null) {
			listaTelefonesRemovidos = new ArrayList<>();
		}
		return listaTelefonesRemovidos;
	}

	public void setListaTelefonesRemovidos(List<Telefone> listaTelefonesRemovidos) {
		this.listaTelefonesRemovidos = listaTelefonesRemovidos;
	}

	public List<Horario> getListaHorariosRemovidos() {
		if (listaHorariosRemovidos == null) {
			listaHorariosRemovidos = new ArrayList<>();
		}
		return listaHorariosRemovidos;
	}

	public void setListaHorariosRemovidos(List<Horario> listaHorariosRemovidos) {
		this.listaHorariosRemovidos = listaHorariosRemovidos;
	}

	public List<FormaPagamento> getListaFormaPagamentoRemovido() {
		if (listaFormaPagamentoRemovido == null) {
			listaFormaPagamentoRemovido = new ArrayList<>();
		}
		return listaFormaPagamentoRemovido;
	}

	public void setListaFormaPagamentoRemovido(List<FormaPagamento> listaFormaPagamentoRemovido) {
		this.listaFormaPagamentoRemovido = listaFormaPagamentoRemovido;
	}

}
