package com.fatec.tcc.empresa;

import org.springframework.data.repository.query.Param;

import com.fatec.tcc.dto.EmpresaDTO;

public interface EmpresaService {

	EmpresaDTO findAllPorCategoria(@Param("idCategoria") Long idCategoria, int paginaAtual, int paginaLimite);

}
