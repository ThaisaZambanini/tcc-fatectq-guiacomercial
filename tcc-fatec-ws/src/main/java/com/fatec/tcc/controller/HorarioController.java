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
import com.fatec.tcc.horario.Horario;
import com.fatec.tcc.horario.HorarioRepository;

@RestController
@RequestMapping(path = "api/horario")
@CrossOrigin
public class HorarioController {

	@Autowired
	private HorarioRepository horarioRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping("/")
	public ResponseEntity<List<Horario>> getAllHorarios(@RequestParam("idEmpresa") Long idEmpresa) {
		List<Horario> lista = horarioRepository.findAllEmpresa(idEmpresa);
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<List<Horario>> novoHorario(@RequestBody @Validated List<Horario> horarios,
			@RequestParam("idEmpresa") Long idEmpresa) throws ResourceNotFoundException {
		Empresa empresa = empresaRepository.findById(idEmpresa)
				.orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrado :: " + idEmpresa));

		if (empresa != null) {
			horarios.forEach(x -> {
				x.setEmpresa(empresa);
			});

			List<Horario> horariosSavos = horarioRepository.saveAll(horarios);
			return new ResponseEntity<List<Horario>>(horariosSavos, HttpStatus.OK);
		}
		return new ResponseEntity<List<Horario>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
