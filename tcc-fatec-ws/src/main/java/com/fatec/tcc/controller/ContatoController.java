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
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.commons.MensagemRetorno;
import com.fatec.tcc.contato.Contato;
import com.fatec.tcc.contato.ContatoRepository;

@RestController
@RequestMapping(path = "api/contato")
@CrossOrigin
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	@GetMapping("/")
	public ResponseEntity<List<Contato>> getAllContatos() {
		List<Contato> lista = contatoRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> novoContato(@RequestBody @Validated Contato contato)
			throws ResourceNotFoundException {

		Contato contato2 = contatoRepository.save(contato);
		if (contato2 != null) {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setTitulo("Sucesso!");
			retorno.setMensagem("Dados salvos com sucesso! Em breve entraremos em contato.");
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		}
		return new ResponseEntity<MensagemRetorno>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
