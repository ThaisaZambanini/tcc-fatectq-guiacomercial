import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Cidade } from "../../model/cidade.model";
import { TipoTelefone } from "../../enum/tipoTelefone.enum";
import { Estado } from "../../model/uf.model";
import { Telefone } from "../../model/telefone.model";
import { Categoria } from "../../model/categoria.model";
import { Observable, throwError } from 'rxjs';
import { NgFlashMessageService } from 'ng-flash-messages';
import { Empresa } from "../../model/empresa.model";
import { EstadoService } from "../../service/estado.service";
import { CidadeService } from "../../service/cidade.service";
import { EmpresaService } from "../../service/empresa.service";
import { CategoriaService } from "../../service/categoria.service";

@Component({
  selector: 'app-add-empresa',
  templateUrl: './add-empresa.component.html'
})
export class AddEmpresaComponent implements OnInit {
  addForm: FormGroup;
  cidades: Cidade[];
  estados: Estado[];
  categorias: Categoria[];
  empresa: Empresa;
  loading: boolean = false;
  submitted: boolean = false;
  telefone: Telefone;
  tipoTelefone: TipoTelefone;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private estadoService: EstadoService,
    private cidadeService: CidadeService,
    private empresaService: EmpresaService,
    private categoriaService: CategoriaService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.empresa = new Empresa();
    this.telefone = new Telefone();

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
      complemento: ['']
    });

    this.getCategorias();
    this.getEstados();
  }

  adicionaTelefone() {
    if (this.telefone.tipo !== null && this.telefone.tipo !== undefined
      && this.telefone.ddd !== null && this.telefone.ddd !== undefined
      && this.telefone.numero !== null && this.telefone.numero !== undefined) {
      console.log(this.telefone)
      // this.empresa.telefone.push(this.telefone);
      // this.telefone = new Telefone();
    } else {
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Campo obrigatório não preenchido!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
    }
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
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.loading = false;
    });
  }

  getCategorias() {
    this.categoriaService.getCategorias("").subscribe((data) => {
      this.categorias = data;
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.loading = false;
    });
  }

}
