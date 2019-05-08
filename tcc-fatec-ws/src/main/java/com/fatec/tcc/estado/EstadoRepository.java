package com.fatec.tcc.estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query("SELECT count(estado) FROM Estado estado where estado.nome = :nome")
	int findEstadoExiste(@Param("nome") String nome);

	
}
