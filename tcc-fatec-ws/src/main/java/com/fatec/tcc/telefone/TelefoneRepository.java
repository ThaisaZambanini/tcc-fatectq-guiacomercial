package com.fatec.tcc.telefone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	@Query("SELECT telefone FROM Telefone telefone INNER JOIN telefone.empresa empresa where empresa.id = :idEmpresa")
	List<Telefone> findAllEmpresa(@Param("idEmpresa") Long idEmpresa);

}
