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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.categoria.CategoriaRepository;
import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;
import com.fatec.tcc.commons.Constantes;
import com.fatec.tcc.commons.SimNao;

@RestController
@RequestMapping(path = "api/categoria")
@CrossOrigin
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@GetMapping("/")
	public ResponseEntity<List<Categoria>> getAllCategorias(
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade) {
		List<Categoria> lista = categoriaRepository.findAllPorCidades(idCidade);
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<Categoria> novaCidade(@RequestBody @Validated Categoria categoria,
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade) throws ResourceNotFoundException {
		Cidade cidade = cidadeRepository.findById(idCidade)
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada :: " + idCidade));

		if (cidade != null) {
			categoria.setCidade(cidade);
			categoria.setAtivo(SimNao.S);
			Categoria categoriaSalvo = categoriaRepository.save(categoria);
			if (categoriaSalvo != null) {
				return new ResponseEntity<Categoria>(categoriaSalvo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
