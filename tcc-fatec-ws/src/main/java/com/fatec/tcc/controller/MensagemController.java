package com.fatec.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fatec.tcc.mensagem.EmailService;
import com.fatec.tcc.mensagem.Mensagem;
import com.fatec.tcc.mensagem.MensagemRepository;
import com.fatec.tcc.usuario.UsuarioRepository;

@RestController
@RequestMapping(path = "api/mensagem")
@CrossOrigin
public class MensagemController {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/")
	public ResponseEntity<List<Mensagem>> getAllMensagens() {
		List<Mensagem> lista = mensagemRepository.findAll();
		return ResponseEntity.ok().body(lista);
	}

	@PostMapping(value = "/adicionar", headers = "Content-Type=application/json")
	public ResponseEntity<MensagemRetorno> newUsuario(@RequestBody @Validated Mensagem mensagem) {
		Mensagem mensagemSalvo = mensagemRepository.save(mensagem);
		if (mensagemSalvo != null) {
//			List<Usuario> usuarios = usuarioRepository.findAll();
//			Usuario admin = usuarios.get(0);
//			emailService.sendSimpleMessage(admin.getEmail(), "Nova Mensagem!",
//					"Nova mensagem enviado pelo aplicativo!");
//			emailService.sendSimpleMessage(mensagem.getEmail(), "App Guia Comercial",
//					"Recebemos sua mensagem e em breve entraremos em contato!");

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
