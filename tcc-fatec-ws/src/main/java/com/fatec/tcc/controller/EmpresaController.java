package com.fatec.tcc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.tcc.categoria.Categoria;
import com.fatec.tcc.categoria.CategoriaRepository;
import com.fatec.tcc.cidade.Cidade;
import com.fatec.tcc.cidade.CidadeRepository;
import com.fatec.tcc.commons.Constantes;
import com.fatec.tcc.commons.MensagemRetorno;
import com.fatec.tcc.dto.EmpresaDTO;
import com.fatec.tcc.empresa.Empresa;
import com.fatec.tcc.empresa.EmpresaRepository;
import com.fatec.tcc.endereco.Endereco;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresa;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresaRepository;

@RestController
@RequestMapping(path = "api/empresas")
@CrossOrigin
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private RlFormaPagamentoEmpresaRepository rlFormaPagamentoEmpresaRepository;

	@GetMapping("/")
	public ResponseEntity<List<Empresa>> getAllEmpresas() {
		List<Empresa> empresas = empresaRepository.findAllEmpresas();
		return ResponseEntity.ok().body(empresas);
	}

	@GetMapping("/filtro")
	public ResponseEntity<List<Empresa>> getEmpresasFiltroWeb(@RequestParam("estado") Optional<Long> estado,
			@RequestParam("cidade") Optional<Long> cidade, @RequestParam("logradouro") Optional<String> logradouro,
			@RequestParam("cep") Optional<String> cep, @RequestParam("numero") Optional<String> numero) {
		List<Empresa> empresas = empresaRepository.findAllEmpresasWeb(estado, cidade, logradouro, cep, numero);

		empresas.forEach(x -> {
			Endereco endereco = x.getEndereco();
			x.getEndereco().setCompleto(endereco.getLogradouro() + ", " + endereco.getBairro() + ", "
					+ endereco.getNumero() + ", CEP: " + endereco.getCep());
		});

		return ResponseEntity.ok().body(empresas);
	}

	@GetMapping("/busca")
	public ResponseEntity<EmpresaDTO> getEmpresaPorCategoriaTermo(
			@RequestHeader(value = Constantes.CIDADE_HEADER) Long idCidade,
			@RequestParam("idCategoria") Optional<Long> idCategoria, @RequestParam("termo") Optional<String> termo,
			@RequestParam("paginaAtual") int paginaAtual, @RequestParam("paginaLimite") int paginaLimite) {
		EmpresaDTO dto = null;
		dto = empresaRepository.findAllPorCategoriaTermo(idCategoria.isPresent() ? idCategoria.get() : null,
				termo.isPresent() ? termo.get() : org.apache.commons.lang3.StringUtils.EMPTY, paginaAtual,
				paginaLimite);
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> getEmpresaPorId(@PathVariable(value = "id") Long id) {
		Empresa empresa = empresaRepository.findEmpresaFetch(id);
		if (empresa != null) {
			return ResponseEntity.ok().body(empresa);
		}
		return new ResponseEntity<Empresa>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping
	public ResponseEntity<MensagemRetorno> novaEmpresa(@RequestBody @Validated Empresa empresa)
			throws ResourceNotFoundException {

		if (empresa.getCategoria() != null) {
			Optional<Categoria> categoria = categoriaRepository.findById(empresa.getCategoria().getId());
			if (categoria.isPresent()) {
				empresa.setCategoria(categoria.get());
			} else {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setCodigoRetorno(1);
				retorno.setMensagem("Categoria não encontrada!");
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		if (!empresa.getHorarios().isEmpty()) {
			empresa.getHorarios().forEach(x -> {
				x.setEmpresa(empresa);
			});
		}

		if (!empresa.getTelefones().isEmpty()) {
			empresa.getTelefones().forEach(x -> {
				x.setEmpresa(empresa);
			});
		}

		if (empresa.getEndereco() != null && empresa.getEndereco().getCidade() != null) {
			Optional<Cidade> cidade = cidadeRepository.findById(empresa.getEndereco().getCidade().getId());
			if (cidade.isPresent())
				empresa.getEndereco().setCidade(cidade.get());
			else {
				MensagemRetorno retorno = new MensagemRetorno();
				retorno.setCodigoRetorno(1);
				retorno.setMensagem("Cidade não encontrada!");
				return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		Empresa empresaSalvo = empresaRepository.save(empresa);
		if (empresaSalvo != null) {

			empresa.getListaFormaPagamento().forEach(x -> {
				RlFormaPagamentoEmpresa rl = new RlFormaPagamentoEmpresa();
				rl.setEmpresa(empresaSalvo);
				rl.setFormaPagamento(x);
				rlFormaPagamentoEmpresaRepository.save(rl);
			});

			MensagemRetorno retorno = new MensagemRetorno();
			retorno.setMensagem("Registro salvo com sucesso!");
			retorno.setCodigoRetorno(0);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		}

		MensagemRetorno retorno = new MensagemRetorno();
		retorno.setCodigoRetorno(1);
		retorno.setMensagem("Não foi possível salvar os dados!");
		return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
