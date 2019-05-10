package com.fatec.tcc.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;
import com.fatec.tcc.commons.MensagemRetorno;
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

	@GetMapping("/estado/{id}")
	public ResponseEntity<List<Cidade>> getCidadesPorEstado(@PathVariable("id") Long idEstado)
			throws ResourceNotFoundException {
		List<Cidade> cidades = cidadeRepository.findAllCidadesPorEstado(idEstado);
		return ResponseEntity.ok().body(cidades);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> getCidade(@PathVariable(value = "id") Long cidadeId) {
		Cidade cidade = cidadeRepository.findCidadeIdFetch(cidadeId);
		if (cidade != null) {
			return ResponseEntity.ok().body(cidade);
		}
		return ResponseEntity.ok().body(null);
	}

	@PostMapping(value = "/adicionar/{id}", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> novaCidade(@RequestBody @Validated Cidade cidade,
			@PathVariable("id") Long idEstado) throws ResourceNotFoundException {
		if (cidadeRepository.findCidadeExiste(cidade.getNome(), Long.valueOf(idEstado)) > 0) {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setCodigoRetorno(1);
			retorno.setMensagem("Cidade " + cidade.getNome() + " já existe!");
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Optional<Estado> estado = estadoRepository.findById(idEstado);
		if (estado.isPresent()) {
			Estado estadoBanco = estado.get();
			cidade.setEstado(estadoBanco);
			Cidade cidadeSalvo = cidadeRepository.save(cidade);
			if (cidadeSalvo != null) {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setMensagem("Registro salvo com sucesso!");
				retorno.setCodigoRetorno(0);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
			}
		} else {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setMensagem("Estado não encontrado!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<MensagemRetorno>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<MensagemRetorno> updateCidade(@Validated @RequestBody Cidade cidade,
			@PathVariable(value = "id") Long cidadeId) {
		Optional<Cidade> optional = cidadeRepository.findById(cidade.getId());
		Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());

		if (optional.isPresent() && estado.isPresent()) {
			Cidade cidadeBanco = optional.get();
			cidadeBanco.setNome(cidade.getNome());
			cidadeBanco.setEstado(estado.get());
			final Cidade alteracaoEstado = cidadeRepository.save(cidadeBanco);
			if (alteracaoEstado != null) {
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

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<MensagemRetorno> deleteCidade(@PathVariable(value = "id") String cidadeId) {
		try {
			Cidade cidade = cidadeRepository.findById(Long.valueOf(cidadeId))
					.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado :: " + cidadeId));
			cidadeRepository.delete(cidade);
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Cidade foi removido com sucesso!");
			retorno.setCodigoRetorno(0);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Não foi possível remover a Cidade!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
