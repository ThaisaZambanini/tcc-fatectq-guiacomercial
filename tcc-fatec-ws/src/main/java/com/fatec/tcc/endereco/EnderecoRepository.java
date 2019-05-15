package com.fatec.tcc.endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query("SELECT endereco FROM Endereco endereco INNER JOIN FETCH endereco.cidade cidade where cidade.id = :id")
	List<Endereco> findAllCidadesPorEstado(@Param("id") Long idCidade);

}
