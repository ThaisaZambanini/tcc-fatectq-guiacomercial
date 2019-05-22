package com.fatec.tcc.categoria;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CategoriaServiceImpl implements CategoriaService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Categoria> findCategoriaPorCidade(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("Select distinct categoria FROM Empresa empresa ");
		sb.append("INNER JOIN empresa.categoria categoria ");
		sb.append("INNER JOIN empresa.endereco endereco ");
		sb.append("INNER JOIN endereco.cidade cidade ");
		sb.append("WHERE cidade.id = :idCidade ");

		Query query = em.createQuery(sb.toString(), Categoria.class);
		query.setParameter("idCidade", id);
		return query.getResultList();
	}

}
