package com.fatec.tcc.horario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

	@Query("SELECT horario FROM Horario horario INNER JOIN horario.empresa empresa where empresa.id = :idEmpresa")
	List<Horario> findAllEmpresa(@Param("idEmpresa") Long idEmpresa);

}
