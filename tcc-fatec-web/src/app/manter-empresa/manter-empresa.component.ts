import { Component, OnInit } from '@angular/core';
import { Empresa } from "../model/empresa.model";
import { Cidade } from "../model/cidade.model";
import { Estado } from "../model/uf.model";
import { FiltroEmpresa } from "../model/filtroEmpresa.model";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NgFlashMessageService } from 'ng-flash-messages';
import { EstadoService } from "../service/estado.service";
import { CidadeService } from "../service/cidade.service";
import { EmpresaService } from "../service/empresa.service";

@Component({
  selector: 'app-manter-empresa',
  templateUrl: './manter-empresa.component.html'
})
export class ManterEmpresaComponent implements OnInit {
  addForm: FormGroup;
  filtro: FiltroEmpresa;
  cidades: Cidade[];
  estados: Estado[];
  empresas: Empresa[]
  nenhumResultado: boolean = true;
  loading: boolean = false;
  pageActual: number = 1;
  submitted: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private estadoService: EstadoService,
    private cidadeService: CidadeService,
    private empresaService: EmpresaService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.loading = true;

    this.addForm = this.formBuilder.group({
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      logradouro: [''],
      cep: [''],
      numero: ['']
    });

    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.loading = false;
    });
  }

  voltar() {
    this.router.navigate(['home']);
  }

  buscaCidade() {
    this.loading = true;
    this.cidadeService.getCidadesPorEstado(this.addForm.controls.estado.value).subscribe((data) => {
      this.cidades = data;
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    if (this.addForm.invalid) {
      this.loading = false;
      return;
    }

    console.log(this.addForm.value)
    const filtro = new FiltroEmpresa().deserialize(this.addForm.value);
    console.log(filtro)

    this.empresaService.getEmpresasFiltroWeb(filtro).subscribe((data) => {
      this.empresas = data;
      this.nenhumResultado = false;

      if (this.empresas.length === 0) {
        this.nenhumResultado = true;
      }
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

}
