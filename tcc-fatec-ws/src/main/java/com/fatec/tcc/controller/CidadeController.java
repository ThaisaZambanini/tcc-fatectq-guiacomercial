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

import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;
import com.fatec.tcc.estado.Estado;
import com.fatec.tcc.estado.EstadoRepository;

@RestController
@RequestMapping(path = "api/cidade")
@CrossOrigin
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping("/")
	public ResponseEntity<List<Cidade>> getAllCidades() {
		List<Cidade> lista = cidadeRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/estado")
	public ResponseEntity<List<Cidade>> getCidadesPorEstado(@RequestParam("idEstado") Long idEstado)
			throws ResourceNotFoundException {
		List<Cidade> cidades = cidadeRepository.findAllCidadesPorEstado(idEstado);
		return ResponseEntity.ok().body(cidades);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<Cidade> novaCidade(@RequestBody @Validated Cidade cidade) throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(cidade.getIdEstado())
				.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado :: " + cidade.getIdEstado()));

		if (estado != null) {
			cidade.setEstado(estado);
			Cidade cidadeSalvo = cidadeRepository.save(cidade);
			if (cidadeSalvo != null) {
				return new ResponseEntity<Cidade>(cidadeSalvo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Cidade>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
