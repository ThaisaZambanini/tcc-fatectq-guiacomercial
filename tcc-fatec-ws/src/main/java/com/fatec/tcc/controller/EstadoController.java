package com.fatec.tcc.controller;

import java.util.List;

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

import com.fatec.tcc.commons.MensagemRetorno;
import com.fatec.tcc.estado.Estado;
import com.fatec.tcc.estado.EstadoRepository;

@RestController
@RequestMapping(path = "api/estado")
@CrossOrigin
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping("/")
	public ResponseEntity<List<Estado>> getAllEstados() {
		List<Estado> lista = estadoRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> newUsuario(@RequestBody @Validated Estado estado) {
		if (estadoRepository.findEstadoExiste(estado.getNome()) > 0) {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setCodigoRetorno(1);
			retorno.setMensagem("Estado " + estado.getNome() + " já existe!");
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Estado estadoSalvo = estadoRepository.save(estado);
			if (estadoSalvo != null) {
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

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Estado> updateUser(@Validated @RequestBody Estado estado,
			@PathVariable(value = "id") Long estadoId) throws ResourceNotFoundException {
		Estado estadoBanco = estadoRepository.findById(estado.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado :: " + estado.getId()));
		estadoBanco.setNome(estado.getNome());
		final Estado alteracaoEstado = estadoRepository.save(estadoBanco);
		return ResponseEntity.ok(alteracaoEstado);
	}

	@DeleteMapping("/deletar/{id}")
	public MensagemRetorno deleteUser(@PathVariable(value = "id") Long estadoId) {
		try {
			Estado estado = estadoRepository.findById(estadoId)
					.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado :: " + estadoId));
			estadoRepository.delete(estado);
			return new MensagemRetorno("Atenção", "Estado foi removido com sucesso!");
		} catch (Exception e) {
			return new MensagemRetorno("Atenção", "Não foi possível remover o Estado!");
		}
	}
}
