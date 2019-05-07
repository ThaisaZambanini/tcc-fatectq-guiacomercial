package com.fatec.tcc.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fatec.tcc.commons.GeralUtil;

public class UsuarioServiceImpl implements UsuarioService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Usuario autenticarUsuario(String cpf, String senha) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT usuario FROM Usuario usuario ");
		sb.append("WHERE usuario.cpf = :cpf");

		Query query = em.createQuery(sb.toString(), Usuario.class);
		query.setParameter("cpf", cpf);
		Usuario usuario = (Usuario) query.getSingleResult();

		String senhaDecriptografada = GeralUtil.descriptografiaBase64Decoder(usuario.getSenha());
		if (senhaDecriptografada.equals(senha)) {
			return usuario;
		}

		return null;
	}

}
