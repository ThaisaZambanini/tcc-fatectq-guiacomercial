package com.fatec.tcc.categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query("SELECT categoria FROM Categoria categoria INNER JOIN categoria.cidade cidade where cidade.id = :id")
	List<Categoria> findAllPorCidades(@Param("id") Long idCidade);

}
