import { Component, OnInit } from '@angular/core';
import { Categoria } from "../model/categoria.model";
import { Cidade } from "../model/cidade.model";
import { Estado } from "../model/uf.model";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NgFlashMessageService } from 'ng-flash-messages';
import { EstadoService } from "../service/estado.service";
import { CidadeService } from "../service/cidade.service";
import { CategoriaService } from "../service/categoria.service";

@Component({
  selector: 'app-manter-categoria',
  templateUrl: './manter-categoria.component.html'
})
export class ManterCategoriaComponent implements OnInit {
  addForm: FormGroup;
  cidades: Cidade[];
  estados: Estado[];
  categoria: Categoria[];
  nenhumResultado: boolean = true;
  loading: boolean = false;
  pageActual: number = 1;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private cidadeService: CidadeService, private categoriaService: CategoriaService, private ngFlashMessageService: NgFlashMessageService) { }

  ngOnInit() {
    this.loading = true;

    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.loading = false;
    });

    // this.categoriaService.getCategorias().subscribe((data) => {
    //   this.categorias = data;
    //   this.loading = false;
    // }, (err) => {
    //   console.log(err);
    //   this.loading = false;
    // });

  }

}
