package com.fatec.tcc.rl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RlFormaPagamentoEmpresaRepository extends JpaRepository<RlFormaPagamentoEmpresa, Long> {

	@Query("SELECT count(rl) FROM RlFormaPagamentoEmpresa rl INNER JOIN rl.formaPagamento formaPagamento where formaPagamento.id = :id")
	int findFormaPagamentoExisteVinculo(Long id);

	@Query("SELECT rl FROM RlFormaPagamentoEmpresa rl INNER JOIN rl.formaPagamento formaPagamento INNER JOIN rl.empresa empresa where formaPagamento.id = :id and empresa.id = :id2")
	RlFormaPagamentoEmpresa findFormaEmpresa(Long id, Long id2);

}
