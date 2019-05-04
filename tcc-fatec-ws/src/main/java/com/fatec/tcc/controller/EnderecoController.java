package com.fatec.tcc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;
import com.fatec.tcc.commons.Constantes;
import com.fatec.tcc.endereco.Endereco;
import com.fatec.tcc.endereco.EnderecoRepository;

@RestController
@RequestMapping(path = "api/endereco")
@CrossOrigin
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/")
	public ResponseEntity<List<Endereco>> getAllEnderecos() {
		List<Endereco> lista = enderecoRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/cidade")
	public ResponseEntity<List<Endereco>> getAllEnderecosPorCidade(
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade) throws ResourceNotFoundException {
		List<Endereco> lista = enderecoRepository.findAllCidadesPorEstado(idCidade);
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<Endereco> novoEndereco(@RequestBody @Validated Endereco endereco,
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade) throws ResourceNotFoundException {
		Cidade cidade = cidadeRepository.findById(idCidade)
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado :: " + idCidade));

		if (cidade != null) {
			endereco.setCidade(cidade);
			Endereco enderecoSalvo = enderecoRepository.save(endereco);
			if (enderecoSalvo != null) {
				return new ResponseEntity<Endereco>(enderecoSalvo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Endereco>(HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
