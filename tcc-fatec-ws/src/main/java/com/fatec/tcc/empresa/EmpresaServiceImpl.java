package com.fatec.tcc.empresa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.fatec.tcc.dto.EmpresaDTO;
import com.fatec.tcc.formaPagamento.FormaPagamento;
import com.fatec.tcc.horario.Horario;

public class EmpresaServiceImpl implements EmpresaService {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public EmpresaDTO findAllPorCategoria(Long idCategoria, int paginaAtual, int paginaLimite) {
		EmpresaDTO dto = new EmpresaDTO();

		StringBuilder sb = new StringBuilder();
		sb.append("FROM Empresa empresa ");
		sb.append("INNER JOIN FETCH empresa.categoria categoria ");
		sb.append("INNER JOIN FETCH empresa.endereco endereco ");
		sb.append("WHERE categoria.id = :idCategoria ");

		Query query = em.createQuery(sb.toString(), Empresa.class);
		query.setParameter("idCategoria", idCategoria);
		query.setFirstResult(paginaAtual);
		query.setMaxResults(paginaLimite);
		List<Empresa> empresas = query.getResultList();

		if (!empresas.isEmpty()) {
			dto.setEmpresas(empresas);
			dto.setTotal(empresas.size());

			StringBuilder sbCount = new StringBuilder();
			sbCount.append("SELECT COUNT(empresa.id) FROM Empresa empresa ");
			sbCount.append("INNER JOIN empresa.categoria categoria ");
			sbCount.append("WHERE categoria.id = :idCategoria ");
			Query countQuery = em.createQuery(sbCount.toString());
			countQuery.setParameter("idCategoria", idCategoria);
			countQuery.setFirstResult(paginaAtual);

			Long countResults = (Long) countQuery.getSingleResult();
			if (countResults > paginaLimite) {
				dto.setMais(true);
			}
		}
		return dto;
	}

	@Override
	public EmpresaDTO findAllPorCategoriaTermo(Long idCategoria, String termo, int paginaAtual, int paginaLimite) {
		EmpresaDTO dto = new EmpresaDTO();
		HashMap<String, Object> parametros = new HashMap<>();

		StringBuilder sb = new StringBuilder();
		sb.append("FROM Empresa empresa ");
		sb.append("INNER JOIN FETCH empresa.categoria categoria ");
		sb.append("INNER JOIN FETCH empresa.endereco endereco ");
		sb.append("WHERE 1 = 1 ");
		if (StringUtils.isNotBlank(termo)) {
			sb.append(" AND (UPPER(categoria.nome) LIKE :termo ");
			sb.append(" OR UPPER(empresa.nome) LIKE :termo) ");
			parametros.put("termo", "%" + termo.toUpperCase() + "%");
		}
		if (idCategoria != null) {
			sb.append("AND categoria.id = :idCategoria");
			parametros.put("idCategoria", idCategoria);
		}

		Query query = em.createQuery(sb.toString(), Empresa.class);
		query.setFirstResult(paginaAtual);
		query.setMaxResults(paginaLimite);
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

		List<Empresa> empresas = query.getResultList();

		if (!empresas.isEmpty()) {
			dto.setEmpresas(empresas);
			dto.setTotal(empresas.size());

			StringBuilder sbCount = new StringBuilder();
			sbCount.append("SELECT COUNT(empresa.id) FROM Empresa empresa ");
			sbCount.append("INNER JOIN empresa.categoria categoria ");
			sbCount.append("WHERE categoria.id = :idCategoria ");
			Query countQuery = em.createQuery(sbCount.toString());
			countQuery.setParameter("idCategoria", idCategoria);
			countQuery.setFirstResult(paginaAtual);
			Long countResults = (Long) countQuery.getSingleResult();
			if (countResults > paginaLimite) {
				dto.setMais(true);
			}
		}
		return dto;
	}

	@Override
	public List<Empresa> findAllEmpresas() {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Empresa empresa ");
		sb.append("INNER JOIN FETCH empresa.categoria categoria ");
		sb.append("INNER JOIN FETCH empresa.endereco endereco ");
		sb.append(" INNER JOIN FETCH endereco.cidade cidade ");
		sb.append(" INNER JOIN FETCH cidade.estado estado ");

		Query query = em.createQuery(sb.toString(), Empresa.class);
		return query.getResultList();
	}

	@Override
	public List<Empresa> findAllEmpresasWeb(Optional<Long> estado, Optional<Long> cidade, Optional<String> logradouro,
			Optional<String> cep, Optional<String> numero) {
		HashMap<String, Object> parametros = new HashMap<>();
		StringBuilder sb = new StringBuilder();

		sb.append("FROM Empresa empresa ");
		sb.append("INNER JOIN FETCH empresa.categoria categoria ");
		sb.append("INNER JOIN FETCH empresa.endereco endereco ");
		sb.append(" INNER JOIN FETCH endereco.cidade cidade ");
		sb.append(" INNER JOIN FETCH cidade.estado estado ");
		sb.append("WHERE estado.id = :estado ");

		if (cidade.isPresent()) {
			sb.append("and cidade.id = :cidade ");
			parametros.put("cidade", cidade.get());
		}

		if (logradouro.isPresent()) {
			sb.append("AND endereco.logradouro = :logradouro");
			parametros.put("logradouro", logradouro.get());
		}

		if (cep.isPresent()) {
			sb.append("AND endereco.cep = :cep");
			parametros.put("cep", cep.get());
		}

		if (numero.isPresent()) {
			sb.append("AND endereco.numero = :numero");
			parametros.put("numero", numero.get());
		}

		parametros.put("estado", estado.get());

		Query query = em.createQuery(sb.toString(), Empresa.class);
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		return query.getResultList();
	}

	@Override
	public Empresa findEmpresaFetch(Long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Empresa empresa ");
		sb.append("INNER JOIN FETCH empresa.categoria categoria ");
		sb.append("INNER JOIN FETCH empresa.endereco endereco ");
		sb.append("INNER JOIN FETCH endereco.cidade cidade ");
		sb.append("INNER JOIN FETCH cidade.estado estado ");
		sb.append("LEFT JOIN FETCH empresa.horarios horarios ");
		sb.append("LEFT JOIN FETCH empresa.telefones telefones ");
		sb.append("LEFT JOIN FETCH empresa.formaPagamento formaPagamento ");
		sb.append("WHERE empresa.id = :empresa ");

		Query query = em.createQuery(sb.toString(), Empresa.class);
		query.setParameter("empresa", id);

		Empresa empresa = (Empresa) query.getSingleResult();
		List<FormaPagamento> listaFormasPagamento = new ArrayList<>();
		if (empresa != null) {
			empresa.getFormaPagamento().forEach(x -> {
				listaFormasPagamento.add(x.getFormaPagamento());
			});
		}

		empresa.setListaFormaPagamento(listaFormasPagamento);

		List<Horario> lista = empresa.getHorarios().stream().distinct().collect(Collectors.toList());
		empresa.getHorarios().clear();
		empresa.setHorarios(lista);
		if (empresa.getHorarios() != null && !empresa.getHorarios().isEmpty()) {

			Collections.sort(empresa.getHorarios(), new Comparator<Horario>() {
				@Override
				public int compare(Horario horario1, Horario horario2) {
					return horario1.getDiaSemana().compareTo(horario2.getDiaSemana());
				}
			});
		}

		if (!empresa.getTelefones().isEmpty()) {
			empresa.getTelefones().forEach(x -> {
				x.setTipoApresentacao(x.getTipo().getDescricao());
			});
		}

		return empresa;
	}
}
