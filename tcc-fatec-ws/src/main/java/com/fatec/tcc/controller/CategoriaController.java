package com.fatec.tcc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.categoria.CategoriaRepository;
import com.fatec.tcc.commons.MensagemRetorno;

@RestController
@RequestMapping(path = "api/categorias")
@CrossOrigin
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAllCategorias(@RequestParam("categoria") Optional<String> categoria) {
		List<Categoria> lista = new ArrayList<>();
		if (categoria.isPresent()) {
			lista = categoriaRepository.findAllPorNome("%" + categoria.get().toUpperCase() + "%");
		} else {
			lista = categoriaRepository.findAll();
		}
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/cidade/{id}")
	public ResponseEntity<List<Categoria>> getAllCategorias(@PathVariable(value = "id") String id) {
		List<Categoria> lista = new ArrayList<>();
		lista = categoriaRepository.findCategoriaPorCidade(Long.valueOf(id));
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCidade(@PathVariable(value = "id") String categoriaId) {
		Optional<Categoria> categoria = categoriaRepository.findById(Long.valueOf(categoriaId));
		if (categoria.isPresent()) {
			return ResponseEntity.ok().body(categoria.get());
		}
		return ResponseEntity.ok().body(null);
	}

	@PostMapping(value = "", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> novaCidade(@RequestBody @Validated Categoria categoria)
			throws ResourceNotFoundException {
		if (categoriaRepository.findCategoriaExiste(categoria.getNome()) > 0) {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setCodigoRetorno(1);
			retorno.setMensagem("Categoria " + categoria.getNome() + " já existe!");
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Categoria categoriaSalvo = categoriaRepository.save(categoria);
			if (categoriaSalvo != null) {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setMensagem("Registro salvo com sucesso!");
				retorno.setCodigoRetorno(0);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
			} else {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setMensagem("Falha ao salvar o registro!");
				retorno.setCodigoRetorno(1);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<MensagemRetorno> updateCategoria(@Validated @RequestBody Categoria categoria,
			@PathVariable(value = "id") Long categoriaId) {
		Optional<Categoria> optional = categoriaRepository.findById(categoriaId);

		if (optional.isPresent()) {
			Categoria categoriaBanco = optional.get();
			categoriaBanco.setNome(categoria.getNome());
			categoriaBanco.setIcone(categoria.getIcone());
			final Categoria alteracaoCategoria = categoriaRepository.save(categoriaBanco);
			if (alteracaoCategoria != null) {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setMensagem("Registro salvo com sucesso!");
				retorno.setCodigoRetorno(0);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
			} else {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setMensagem("Falha ao salvar o registro!");
				retorno.setCodigoRetorno(1);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setMensagem("Registro não encontrado!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MensagemRetorno> deleteCategoria(@PathVariable(value = "id") Long categoriaId) {
		try {
			Categoria categoria = categoriaRepository.findById(categoriaId)
					.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada :: " + categoriaId));
			categoriaRepository.delete(categoria);
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Categoria foi removido com sucesso!");
			retorno.setCodigoRetorno(0);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Não foi possível remover a Categoria!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}