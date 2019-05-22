package com.fatec.tcc.categoria;

import java.util.List;

public interface CategoriaService {

	List<Categoria> findCategoriaPorCidade(Long id);
	
}
