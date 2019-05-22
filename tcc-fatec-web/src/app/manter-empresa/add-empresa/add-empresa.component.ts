import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Cidade } from "../../model/cidade.model";
import { Estado } from "../../model/uf.model";
import { Telefone } from "../../model/telefone.model";
import { Horario } from "../../model/horario.model";
import { Categoria } from "../../model/categoria.model";
import { FormaPagamento } from "../../model/forma-pagamento.model";
import { Observable, throwError } from 'rxjs';
import { NgFlashMessageService } from 'ng-flash-messages';
import { Empresa } from "../../model/empresa.model";
import { EstadoService } from "../../service/estado.service";
import { CidadeService } from "../../service/cidade.service";
import { EmpresaService } from "../../service/empresa.service";
import { CategoriaService } from "../../service/categoria.service";
import { FormaPagamentoService } from "../../service/forma-pagamento.service";

@Component({
  selector: 'app-add-empresa',
  templateUrl: './add-empresa.component.html'
})
export class AddEmpresaComponent implements OnInit {
  addForm: FormGroup;
  cidades: Cidade[];
  estados: Estado[];
  formasPagamento: FormaPagamento[];
  categorias: Categoria[];
  horario: Horario;
  empresa: Empresa;
  loading: boolean = false;
  submitted: boolean = false;
  telefone: Telefone;
  formaPagamento: FormaPagamento;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private estadoService: EstadoService,
    private cidadeService: CidadeService,
    private empresaService: EmpresaService,
    private categoriaService: CategoriaService,
    private ngFlashMessageService: NgFlashMessageService,
    private formaPagamentoService: FormaPagamentoService
  ) { }

  ngOnInit() {
    this.loading = true;
    this.empresa = new Empresa();
    this.telefone = new Telefone();
    this.horario = new Horario();
    this.formaPagamento = new FormaPagamento();
    this.formasPagamento = new Array();

    this.addForm = this.formBuilder.group({
      nome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      linkSite: [''],
      linkFacebook: [''],
      linkInstagram: [''],
      linkTwitter: [''],
      categoria: ['', Validators.required],
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      logradouro: ['', Validators.required],
      numero: ['', Validators.required],
      bairro: ['', Validators.required],
      cep: ['', Validators.required],
      complemento: [''],
      tipo: [''],
      ddd: [''],
      numeroTelefone: [''],
      diaSemana: [''],
      horarioInicial: [''],
      horarioFinal: [''],
      formaPagamento: ['']
    });

    this.getCategorias();
    this.getEstados();
    this.getFormasPagamento();
    this.loading = false;
  }

  adicionarHorario() {
    if (this.horario.diaSemana !== null && this.horario.diaSemana !== undefined
      && this.horario.horarioInicial !== null && this.horario.horarioInicial !== undefined
      && this.horario.horarioFinal !== null && this.horario.horarioFinal !== undefined) {
      this.empresa.horarios.push(this.horario);
      this.horario = new Horario();
    } else {
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Campo obrigatório não preenchido!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      window.scrollTo(5000, 0);
      return;
    }
  }

  adicionarTelefone() {
    if (this.telefone.tipo !== null && this.telefone.tipo !== undefined
      && this.telefone.ddd !== null && this.telefone.ddd !== undefined
      && this.telefone.numero !== null && this.telefone.numero !== undefined) {
      if (this.telefone.tipo === 'T') {
        this.telefone.tipoApresentacao = 'Telefone';
      } else if (this.telefone.tipo === 'W') {
        this.telefone.tipoApresentacao = 'Whatsapp';
      } else if (this.telefone.tipo === 'C') {
        this.telefone.tipoApresentacao = 'Celular';
      }

      this.empresa.telefones.push(this.telefone);
      this.telefone = new Telefone();
    } else {
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Campo obrigatório não preenchido!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      window.scrollTo(5000, 0);
      return;
    }
  }

  adicionarFormaPagamento() {
    if (this.formaPagamento.id !== null && this.formaPagamento.id !== undefined
      && this.formaPagamento.descricao !== null && this.formaPagamento.descricao !== undefined) {
      this.empresa.listaFormaPagamento.push(this.formaPagamento);
      console.log(this.empresa.listaFormaPagamento)
      this.formaPagamento = new FormaPagamento();
    }
  }

  excluirFormaPagamento(formaPagamento: FormaPagamento) {
    this.empresa.listaFormaPagamento.forEach((item, index) => {
      if (item === formaPagamento) this.empresa.listaFormaPagamento.splice(index, 1);
    });
  }

  excluirHorario(horario: Horario) {
    this.empresa.horarios.forEach((item, index) => {
      if (item === horario) this.empresa.horarios.splice(index, 1);
    });
  }

  excluirTelefone(telefone: Telefone) {
    this.empresa.telefones.forEach((item, index) => {
      if (item === telefone) this.empresa.telefones.splice(index, 1);
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.addForm.invalid) {
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Campo obrigatório não preenchido!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      this.loading = false;
      return;
    }
    this.loading = true;

    this.empresaService.adicionarEmpresa(this.empresa).subscribe(data => {
      var res = JSON.parse(JSON.stringify(data));
      this.loading = false;
      if (res.codigoRetorno === 0) {
        this.ngFlashMessageService.showFlashMessage({
          messages: [res.mensagem],
          dismissible: true,
          timeout: false,
          type: 'success'
        });
        this.empresa = new Empresa();
        this.submitted = false;
      } else {
        if (data != null) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'danger'
          });
        }
      }
    }, (err) => {
      this.loading = false;
      var res = JSON.parse(JSON.stringify(err));
      this.ngFlashMessageService.showFlashMessage({
        messages: [res.error.mensagem || "Falha ao salvar!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
    });
  }

  buscaCidade() {
    this.loading = true;
    this.cidadeService.getCidadesPorEstado(this.empresa.endereco.cidade.estado.id.toString()).subscribe((data) => {
      this.cidades = data;
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  voltar() {
    this.router.navigate(['manter-empresa']);
  }

  getEstados() {
    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
    }, (err) => {
      console.log(err);
    });
  }

  getFormasPagamento() {
    this.formaPagamentoService.getFormasPagamento("").subscribe((data) => {
      this.formasPagamento = data;
    }, (err) => {
      console.log(err);
    });
  }

  getCategorias() {
    this.categoriaService.getCategorias("").subscribe((data) => {
      this.categorias = data;
    }, (err) => {
      console.log(err);
    });
  }

}
