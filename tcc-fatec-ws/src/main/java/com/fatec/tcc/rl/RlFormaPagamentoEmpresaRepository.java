package com.fatec.tcc.rl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RlFormaPagamentoEmpresaRepository extends JpaRepository<RlFormaPagamentoEmpresa, Long>{

	@Query("SELECT count(rl) FROM RlFormaPagamentoEmpresa rl INNER JOIN rl.formaPagamento formaPagamento where formaPagamento.id = :id")
	int findFormaPagamentoExisteVinculo(Long id);

}
