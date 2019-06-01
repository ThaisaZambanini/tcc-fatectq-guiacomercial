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
import com.fatec.tcc.horario.HorarioRepository;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresa;
import com.fatec.tcc.rl.RlFormaPagamentoEmpresaRepository;
import com.fatec.tcc.telefone.TelefoneRepository;

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

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private HorarioRepository horarioRepository;

	@GetMapping("")
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

	@PutMapping("/{id}")
	public ResponseEntity<MensagemRetorno> updateEmpresa(@Validated @RequestBody Empresa empresa,
			@PathVariable(value = "id") Long empresaId) {

		if (!empresa.getListaTelefonesRemovidos().isEmpty()) {
			empresa.getListaTelefonesRemovidos().forEach(x -> {
				x.setEmpresa(empresa);
			});

			telefoneRepository.deleteAll(empresa.getListaTelefonesRemovidos());
		}

		if (!empresa.getListaHorariosRemovidos().isEmpty()) {
			empresa.getListaHorariosRemovidos().forEach(x -> {
				x.setEmpresa(empresa);
			});

			horarioRepository.deleteAll(empresa.getListaHorariosRemovidos());
		}

		if (!empresa.getListaFormaPagamentoRemovido().isEmpty()) {
			empresa.getListaFormaPagamentoRemovido().forEach(x -> {
				RlFormaPagamentoEmpresa rl = rlFormaPagamentoEmpresaRepository.findFormaEmpresa(x.getId(),
						empresa.getId());
				if (rl != null) {
					rlFormaPagamentoEmpresaRepository.delete(rl);
				}
			});
		}

		Empresa empresaBanco = empresaRepository.findEmpresaFetch(empresaId);

		if (empresaBanco != null) {
			empresaBanco.setCategoria(empresa.getCategoria());
			empresaBanco.setEmail(empresa.getEmail());
			empresaBanco.setLinkFacebook(empresa.getLinkFacebook());
			empresaBanco.setLinkInstagram(empresa.getLinkInstagram());
			empresaBanco.setLinkSite(empresa.getLinkSite());
			empresaBanco.setLinkTwitter(empresa.getLinkTwitter());
			empresaBanco.setNome(empresa.getNome());

			if (empresa.getCategoria() != null) {
				Optional<Categoria> categoria = categoriaRepository.findById(empresa.getCategoria().getId());
				if (categoria.isPresent()) {
					empresaBanco.setCategoria(categoria.get());
				} else {
					MensagemRetorno retorno = new MensagemRetorno();
					retorno.setCodigoRetorno(1);
					retorno.setMensagem("Categoria não encontrada!");
					return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			if (!empresa.getHorarios().isEmpty()) {
				empresa.getHorarios().forEach(x -> {
					x.setEmpresa(empresaBanco);
				});
			}

			empresaBanco.setHorarios(empresa.getHorarios());

			if (!empresa.getTelefones().isEmpty()) {
				empresa.getTelefones().forEach(x -> {
					x.setEmpresa(empresaBanco);
				});
			}

			empresaBanco.setTelefones(empresa.getTelefones());

			if (empresa.getEndereco() != null && empresa.getEndereco().getCidade() != null) {
				Optional<Cidade> cidade = cidadeRepository.findById(empresa.getEndereco().getCidade().getId());
				if (cidade.isPresent())
					empresaBanco.getEndereco().setCidade(cidade.get());
				else {
					MensagemRetorno retorno = new MensagemRetorno();
					retorno.setCodigoRetorno(1);
					retorno.setMensagem("Cidade não encontrada!");
					return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			final Empresa alteracaoEmpresa = empresaRepository.save(empresaBanco);

			empresa.getListaFormaPagamento().forEach(x -> {
				RlFormaPagamentoEmpresa rl = rlFormaPagamentoEmpresaRepository.findFormaEmpresa(x.getId(),
						empresa.getId());
				if (rl == null) {
					RlFormaPagamentoEmpresa rl2 = new RlFormaPagamentoEmpresa();
					rl2.setEmpresa(empresaBanco);
					rl2.setFormaPagamento(x);
					rlFormaPagamentoEmpresaRepository.save(rl2);
				}
			});

			if (alteracaoEmpresa != null) {
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
	public ResponseEntity<MensagemRetorno> deleteEmpresa(@PathVariable(value = "id") Long empresaId) {
		try {
			Empresa empresaBanco = empresaRepository.findEmpresaFetch(empresaId);
			empresaRepository.delete(empresaBanco);
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Empresa foi removido com sucesso!");
			retorno.setCodigoRetorno(0);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.OK);
		} catch (Exception e) {
			MensagemRetorno retorno = new MensagemRetorno("Atenção", "Não foi possível remover a Empresa!");
			retorno.setCodigoRetorno(1);
			return new ResponseEntity<MensagemRetorno>(retorno, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
