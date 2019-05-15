package com.fatec.tcc.formaPagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	@Query("SELECT forma FROM FormaPagamento forma where UPPER(forma.descricao) LIKE :descricao")
	List<FormaPagamento> findAllPorNome(String descricao);
	
	@Query("SELECT count(forma) FROM FormaPagamento forma where forma.descricao = :descricao")
	int findFormaPagamentoExiste(String descricao);
	
}
