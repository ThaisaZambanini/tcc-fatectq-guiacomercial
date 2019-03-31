package com.fatec.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;

@RestController
@RequestMapping(path = "api/cidade")
@CrossOrigin
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/")
	public ResponseEntity<List<Cidade>> getAllCidades() {
		List<Cidade> lista = cidadeRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{idEstado}")
	public ResponseEntity<Cidade> getUsersById(@PathVariable(value = "idEstado") Long userId)
			throws ResourceNotFoundException {
		Cidade user = cidadeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + userId));

		return ResponseEntity.ok().body(user);
	}

}
