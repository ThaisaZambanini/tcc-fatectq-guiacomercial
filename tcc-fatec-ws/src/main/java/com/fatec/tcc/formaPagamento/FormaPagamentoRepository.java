package com.fatec.tcc.formaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	/*@Query("SELECT formaPagamento FROM FormaPagamento formaPagamento INNER JOIN formaPagamento.empresa empresa where empresa.id = :idEmpresa")
	List<FormaPagamento> findAllEmpresa(@Param("idEmpresa") Long idEmpresa);*/

	
}
