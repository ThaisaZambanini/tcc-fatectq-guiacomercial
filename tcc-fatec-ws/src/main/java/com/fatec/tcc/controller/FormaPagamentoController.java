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

import com.fatec.tcc.empresa.Empresa;
import com.fatec.tcc.empresa.EmpresaRepository;
import com.fatec.tcc.formaPagamento.FormaPagamento;
import com.fatec.tcc.formaPagamento.FormaPagamentoRepository;

@RestController
@RequestMapping(path = "api/formaPagamento")
@CrossOrigin
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping("/")
	public ResponseEntity<List<FormaPagamento>> getAllFormasPagamento() {
		List<FormaPagamento> lista = formaPagamentoRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<List<FormaPagamento>> novaFormaPagamento(
			@RequestBody @Validated List<FormaPagamento> formasPagamento, @RequestParam("idEmpresa") Long idEmpresa)
			throws ResourceNotFoundException {
		Empresa empresa = empresaRepository.findById(idEmpresa)
				.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrado :: " + idEmpresa));

		if (empresa != null) {
			/*
			 * formasPagamento.forEach(x -> { x.setEmpresa(empresa); });
			 */

			List<FormaPagamento> horariosSavos = formaPagamentoRepository.saveAll(formasPagamento);
			return new ResponseEntity<List<FormaPagamento>>(horariosSavos, HttpStatus.OK);
		}
		return new ResponseEntity<List<FormaPagamento>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
