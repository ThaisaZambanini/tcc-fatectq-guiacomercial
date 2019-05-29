package com.fatec.tcc.horario;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fatec.tcc.commons.DiaSemanaEnum;
import com.fatec.tcc.commons.HoraHandler;
import com.fatec.tcc.empresa.Empresa;

@Entity
@Table(name = "tb_horario", schema = "dbguiacomercial")
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dbguiacomercial.sq_horario")
	@SequenceGenerator(name = "dbguiacomercial.sq_horario", sequenceName = "dbguiacomercial.sq_horario", initialValue = 1, allocationSize = 50)
	@Column(name = "co_seq_horario", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "dia", nullable = false)
	@Enumerated(EnumType.STRING)
	private DiaSemanaEnum diaSemana;

	@Temporal(TemporalType.TIME)
	@Column(name = "horainicial")
	@JsonDeserialize(using = HoraHandler.class)
	private Date horarioInicial;

	@Temporal(TemporalType.TIME)
	@Column(name = "horafinal")
	@JsonDeserialize(using = HoraHandler.class)
	private Date horarioFinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Empresa empresa;

	@Transient
	private String horario;

	@Transient
	private String diaSemanaApp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiaSemanaEnum getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHorario() {
		if (getHorarioInicial() != null) {
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
			return formatTime.format(getHorarioInicial()) + " às " + formatTime.format(getHorarioFinal());
		}
		return "Fechado";
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(Date horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public Date getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(Date horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public String getDiaSemanaApp() {
		return getDiaSemana().getDescricao();
	}

	public void setDiaSemanaApp(String diaSemanaApp) {
		this.diaSemanaApp = diaSemanaApp;
	}

}
