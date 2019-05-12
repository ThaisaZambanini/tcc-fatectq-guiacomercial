import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Categoria } from "../../model/categoria.model";
import { NgFlashMessageService } from 'ng-flash-messages';
import { CategoriaService } from "../../service/categoria.service";
import { BrowserModule } from '@angular/platform-browser'
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-edit-categoria',
  templateUrl: './edit-categoria.component.html'
})
export class EditCategoriaComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  fileName: string;
  filePreview: string

  constructor(private sanitizer: DomSanitizer, private formBuilder: FormBuilder, private router: Router, private categoriaService: CategoriaService, private ngFlashMessageService: NgFlashMessageService) {
  }

  ngOnInit() {
    this.loading = true;
    this.addForm = this.formBuilder.group({
      id: [],
      nome: ['', Validators.required],
      icone: ['']
    });

    const editCategoriaId = localStorage.getItem("editCategoriaId");
    localStorage.removeItem("editCategoriaId");
    if (!editCategoriaId) {
      this.router.navigate(['manter-categoria']);
      return;
    } else {
      this.categoriaService.getCategoria(editCategoriaId)
        .subscribe(data => {
          const categoria = new Categoria().deserialize(data)
          this.addForm.setValue(categoria);
          this.filePreview = categoria.icone;
          this.fileName = categoria.nome;
          this.loading = false;
        });
    }
  }

  onFileChanged(event) {

    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.fileName = file.name + " " + file.type;
        this.filePreview = 'data:image/png' + ';base64,' + reader.result.split(',')[1];
      };
    }
  }

  sanitize(url: string) {
    return this.sanitizer.bypassSecurityTrustUrl(url);
  }

  voltar() {
    this.router.navigate(['manter-categoria']);
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    //verifica se tem campos em brancos
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

    if (this.filePreview === undefined || this.filePreview === "") {
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Campo obrigatório não preenchido!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      this.loading = false;
      return;
    }

    const categoria = new Categoria().deserialize(this.addForm.value);
    categoria.icone = this.filePreview;
    // chama o serviço para adicionar os dados
    this.categoriaService.alterarCategoria(categoria)
      .subscribe(data => {
        var res = JSON.parse(JSON.stringify(data));
        this.loading = false;
        if (res.codigoRetorno === 0) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });
          this.router.navigate(['manter-categoria']);
          this.loading = false;
        } else {
          this.loading = false;
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
}
