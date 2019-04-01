package com.fatec.tcc.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.categoria.CategoriaRepository;
import com.fatec.tcc.commons.Constantes;
import com.fatec.tcc.dto.EmpresaDTO;
import com.fatec.tcc.empresa.Empresa;
import com.fatec.tcc.empresa.EmpresaRepository;
import com.fatec.tcc.endereco.Endereco;
import com.fatec.tcc.endereco.EnderecoRepository;

@RestController
@RequestMapping(path = "api/empresa")
@CrossOrigin
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@GetMapping("/categoria")
	public ResponseEntity<EmpresaDTO> getEmpresaPorCategoria(
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade,
			@RequestParam("idCategoria") Long idCategoria, int paginaAtual, int paginaLimite) {
		EmpresaDTO dto = null;
		dto = empresaRepository.findAllPorCategoria(idCategoria, paginaAtual, paginaLimite);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<Empresa> novaEmpresa(@RequestBody @Validated Empresa empresa,
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade) throws ResourceNotFoundException {

		Categoria categoria = null;
		if (empresa.getCategoria() != null) {
			categoria = categoriaRepository.findById(empresa.getCategoria().getId())
					.orElseThrow(() -> new ResourceNotFoundException(
							"Categoria não encontrada :: " + empresa.getCategoria().getId()));
		}

		Endereco endereco = null;
		if (empresa.getEndereco() != null) {
			endereco = enderecoRepository.findById(empresa.getEndereco().getId()).orElseThrow(
					() -> new ResourceNotFoundException("Endereço não encontrada :: " + empresa.getEndereco().getId()));
		}

		if (endereco != null && categoria != null) {
			empresa.setCategoria(categoria);
			empresa.setEndereco(endereco);
			Empresa empresaSalvo = empresaRepository.save(empresa);
			if (empresaSalvo != null) {
				return new ResponseEntity<Empresa>(empresaSalvo, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Empresa>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
