package com.fatec.tcc.empresa;

import java.util.List;
import java.util.Optional;

import com.fatec.tcc.dto.EmpresaDTO;

public interface EmpresaService {

	EmpresaDTO findAllPorCategoria(Long idCategoria, int paginaAtual, int paginaLimite);

	EmpresaDTO findAllPorCategoriaTermo(Long idCategoria, String termo, int paginaAtual, int paginaLimite);

	List<Empresa> findAllEmpresas();

	List<Empresa> findAllEmpresasWeb(Optional<Long> estado, Optional<Long> cidade, Optional<String> logradouro,
			Optional<String> cep, Optional<String> numero);

}
