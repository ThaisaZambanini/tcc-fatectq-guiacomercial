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
import com.fatec.tcc.telefone.Telefone;
import com.fatec.tcc.telefone.TelefoneRepository;

@RestController
@RequestMapping(path = "api/telefone")
@CrossOrigin
public class TelefoneController {

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping("/")
	public ResponseEntity<List<Telefone>> getAllTelefones(@RequestParam("idEmpresa") Long idEmpresa) {
		List<Telefone> lista = telefoneRepository.findAllEmpresa(idEmpresa);
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<List<Telefone>> novoTelefone(@RequestBody @Validated List<Telefone> telefones,
			@RequestParam("idEmpresa") Long idEmpresa) throws ResourceNotFoundException {
		Empresa empresa = empresaRepository.findById(idEmpresa)
				.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrado :: " + idEmpresa));

		if (empresa != null) {
			telefones.forEach(x -> {
				x.setEmpresa(empresa);
			});

			List<Telefone> horariosSavos = telefoneRepository.saveAll(telefones);
			return new ResponseEntity<List<Telefone>>(horariosSavos, HttpStatus.OK);
		}
		return new ResponseEntity<List<Telefone>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
