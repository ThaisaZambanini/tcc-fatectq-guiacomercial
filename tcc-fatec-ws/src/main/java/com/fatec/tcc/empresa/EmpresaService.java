package com.fatec.tcc.empresa;

import com.fatec.tcc.dto.EmpresaDTO;

public interface EmpresaService {

	EmpresaDTO findAllPorCategoria(Long idCategoria, int paginaAtual, int paginaLimite);

	EmpresaDTO findAllPorCategoriaTermo(Long idCategoria, String termo, int paginaAtual, int paginaLimite);

}
