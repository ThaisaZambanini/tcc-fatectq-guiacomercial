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

import com.fatec.tcc.commons.MensagemRetorno;
import com.fatec.tcc.formaPagamento.FormaPagamento;
import com.fatec.tcc.formaPagamento.FormaPagamentoRepository;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresaRepository;

@RestController
@RequestMapping(path = "api/formasPagamento")
@CrossOrigin
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private RlFormaPagamentoEmpresaRepository rlFormaPagamentoEmpresaRepository;

	@GetMapping("")
	public ResponseEntity<List<FormaPagamento>> getAllFormasPagamento(
			@RequestParam("formaPagamento") Optional<String> categoria) {
		List<FormaPagamento> lista = new ArrayList<>();
		if (categoria.isPresent()) {
			lista = formaPagamentoRepository.findAllPorNome("%" + categoria.get().toUpperCase() + "%");
		} else {
			lista = formaPagamentoRepository.findAll();
		}
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> getFormaPagamento(@PathVariable(value = "id") String formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(Long.valueOf(formaPagamentoId));
		if (formaPagamento.isPresent()) {
			return ResponseEntity.ok().body(formaPagamento.get());
		}
		return ResponseEntity.ok().body(null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MensagemRetorno> updateFormaPagamento(@Validated @RequestBody FormaPagamento forma,
			@PathVariable(value = "id") Long formaId) {
		Optional<FormaPagamento> optional = formaPagamentoRepository.findById(formaId);

		if (optional.isPresent()) {
			FormaPagamento formaBanco = optional.get();
			formaBanco.setDescricao(forma.getDescricao());
			final FormaPagamento alteracaoForma = formaPagamentoRepository.save(formaBanco);
			if (alteracaoForma != null) {
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

	@PostMapping(value = "", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> novaFormaPagamento(@RequestBody @Validated FormaPagamento formaPagamento)
			throws ResourceNotFoundException {
		if (formaPagamentoRepository.findFormaPagamentoExiste(formaPagamento.getDescricao()) > 0) {
			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setCodigoRetorno(1);
			retorno.setMensagem("Forma de Pagamento " + formaPagamento.getDescricao() + " já existe!");
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			FormaPagamento formaPagamentoSalvo = formaPagamentoRepository.save(formaPagamento);
			if (formaPagamentoSalvo != null) {
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

	@DeleteMapping("/{id}")
	public ResponseEntity<MensagemRetorno> deleteFormaPagamento(@PathVariable(value = "id") String id) {
		Optional<FormaPagamento> forma = formaPagamentoRepository.findById(Long.valueOf(id));

		if (forma.isPresent()) {

			if (rlFormaPagamentoEmpresaRepository.findFormaPagamentoExisteVinculo(Long.valueOf(id)) > 0) {
				MensagemRetorno retorno = new MensagemRetorno("Atenção",
						"Não é possível remover Forma de Pagamento, pois possui vínculos!");
				retorno.setCodigoRetorno(1);
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			formaPagamentoRepository.delete(forma.get());
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Forma de Pagamento foi removido com sucesso!");
			retorno.setCodigoRetorno(0);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		} else {
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Não foi possível remover a Forma de Pagamento!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}