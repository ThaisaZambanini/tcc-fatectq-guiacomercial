package com.fatec.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.commons.GeralUtil;
import com.fatec.tcc.usuario.Usuario;
import com.fatec.tcc.usuario.UsuarioRepository;

@RestController
@RequestMapping(path = "api/usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> lista = usuarioRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/autenticar")
	public Usuario autenticarUsuario(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha) {
		Usuario usuarioAutenticado = usuarioRepository.autenticarUsuario(cpf, senha);
		if (usuarioAutenticado != null) {
			return usuarioAutenticado;
		}
		return null;
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<Usuario> novoUsuario(@RequestBody @Validated Usuario usuario)
			throws ResourceNotFoundException {

		if (usuario != null) {
			String senha = usuario.getSenha();
			usuario.setSenha(GeralUtil.criptografiaBase64Encoder(senha));
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
			if (usuarioSalvo != null) {
				return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
