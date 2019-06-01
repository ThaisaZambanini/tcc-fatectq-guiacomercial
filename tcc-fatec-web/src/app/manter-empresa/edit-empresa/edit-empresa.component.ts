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
  selector: 'app-edit-empresa',
  templateUrl: './edit-empresa.component.html'
})
export class EditEmpresaComponent implements OnInit {
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
    this.empresa.listaTelefonesRemovidos = new Array();
    this.empresa.listaFormaPagamentoRemovido = new Array();

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

    const editEmpresaId = localStorage.getItem("editEmpresaId");
    localStorage.removeItem("editEmpresaId");
    if (!editEmpresaId) {
      this.router.navigate(['manter-empresa']);
      return;
    } else {
      this.empresaService.getEmpresa(editEmpresaId)
        .subscribe(data => {
          this.empresa = new Empresa().deserialize(data)
          this.buscaCidade(this.empresa.endereco.cidade.estado.id.toString())
          this.addForm.controls['estado'].patchValue(this.empresa.endereco.cidade.estado.id);
          this.addForm.controls['categoria'].patchValue(this.empresa.categoria.id);
        });
    }
  }

  buscaCidade(idEstado: string) {
    this.cidadeService.getCidadesPorEstado(idEstado).subscribe((data) => {
      this.cidades = data;
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  getEstados() {
    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
      console.log(data)
      this.loading = false;
    }, (err) => {
      this.loading = false;
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
      console.log(data)
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  voltar() {
    this.router.navigate(['manter-empresa']);
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    //verifica se tem campos em brancos
    if (this.addForm.invalid) {
      this.loading = false;
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Dados inválidos!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      return;
    }

    this.empresaService.alterarEmpresa(this.empresa)
      .subscribe(data => {
        var res = JSON.parse(JSON.stringify(data));
        if (res.codigoRetorno === 0) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });
          this.router.navigate(['manter-empresa']);
          this.loading = false;
        } else {
          if (data != null) {
            this.loading = false;
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
      if (this.telefone.tipo === 'F') {
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
      if (item === formaPagamento) {

        if(this.empresa.listaFormaPagamentoRemovido === null) {
          this.empresa.listaFormaPagamentoRemovido = [];
        }
        this.empresa.listaFormaPagamentoRemovido.push(item);
        this.empresa.listaFormaPagamento.splice(index, 1);

      }
    });
  }

  excluirHorario(horario: Horario) {
    this.empresa.horarios.forEach((item, index) => {
      if (item === horario) {
        if (item.id !== null && item.id !== undefined) {
          if (this.empresa.listaHorariosRemovidos === null) {
            this.empresa.listaHorariosRemovidos = [];
          }
          this.empresa.listaHorariosRemovidos.push(item);
        }
        this.empresa.horarios.splice(index, 1);
      }
    });
  }

  excluirTelefone(telefone: Telefone) {
    this.empresa.telefones.forEach((item, index) => {
      if (item === telefone) {
        if (telefone.id !== null && telefone.id !== undefined) {
          if (this.empresa.listaTelefonesRemovidos === null) {
            this.empresa.listaTelefonesRemovidos = [];
          }
          this.empresa.listaTelefonesRemovidos.push(item);
        }
        this.empresa.telefones.splice(index, 1);
      }
    });
  }

}
