package com.fatec.tcc.cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("SELECT cidade FROM Cidade cidade INNER JOIN FETCH cidade.estado estado where estado.id = :id")
	List<Cidade> findAllCidadesPorEstado(@Param("id") Long idEstado);

}