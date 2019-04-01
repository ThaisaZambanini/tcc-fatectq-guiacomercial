package com.fatec.tcc.dto;

import java.util.List;

import com.fatec.tcc.empresa.Empresa;

public class EmpresaDTO {

	private List<Empresa> empresas;
	private boolean mais;
	private int total;

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public boolean isMais() {
		return mais;
	}

	public void setMais(boolean mais) {
		this.mais = mais;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
