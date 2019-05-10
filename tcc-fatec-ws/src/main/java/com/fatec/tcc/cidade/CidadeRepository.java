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
	
	@Query("SELECT count(cidade) FROM Cidade cidade INNER JOIN cidade.estado estado where cidade.nome = :nome and estado.id = :id")
	int findCidadeExiste(@Param("nome") String nome, @Param("id") Long id);

	@Query("SELECT cidade FROM Cidade cidade INNER JOIN FETCH cidade.estado estado where cidade.id = :id")
	Cidade findCidadeIdFetch(@Param("id") Long idCidade);

}