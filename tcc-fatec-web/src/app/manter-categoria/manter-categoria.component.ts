import { Component, OnInit } from '@angular/core';
import { Categoria } from "../model/categoria.model";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NgFlashMessageService } from 'ng-flash-messages';
import { CategoriaService } from "../service/categoria.service";


@Component({
  selector: 'app-manter-categoria',
  templateUrl: './manter-categoria.component.html'
})
export class ManterCategoriaComponent implements OnInit {
  addForm: FormGroup;
  categorias: Categoria[];
  nenhumResultado: boolean = true;
  loading: boolean = false;
  pageActual: number = 1;
  submitted: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private categoriaService: CategoriaService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.loading = true;

    this.addForm = this.formBuilder.group({
      nome: ['', Validators.required]
    });

    this.getCategorias("");
  }

  getCategorias(categoria: string) {
    this.categoriaService.getCategorias(categoria).subscribe((data) => {
      this.nenhumResultado = false;
      this.categorias = data;

      if (this.categorias.length === 0) {
        this.nenhumResultado = true;
      }
      this.loading = false;
    }, (err) => {
      console.log(err);
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
    this.getCategorias(this.addForm.controls.nome.value);
  }

  addCategoria() {
    this.router.navigate(['add-categoria']);
  }

  limparCampos() {
    this.loading = true;
    this.getCategorias("");
    this.addForm.reset();
    for (const key in this.addForm.controls) {
      this.addForm.get(key).clearValidators();
      this.addForm.get(key).updateValueAndValidity();
    }
  }

  editarCategoria(categoria: Categoria): void {
    localStorage.removeItem("editCategoriaId");
    localStorage.setItem("editCategoriaId", categoria.id.toString());
    this.router.navigate(['edit-categoria']);
  }
}
