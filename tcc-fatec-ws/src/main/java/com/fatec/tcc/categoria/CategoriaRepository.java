package com.fatec.tcc.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query("SELECT count(categoria) FROM Categoria categoria where categoria.nome = :nome")
	int findCategoriaExiste(String nome);

	@Query("SELECT categoria FROM Categoria categoria where UPPER(categoria.nome) LIKE :nome")
	List<Categoria> findAllPorNome(String nome);

}
