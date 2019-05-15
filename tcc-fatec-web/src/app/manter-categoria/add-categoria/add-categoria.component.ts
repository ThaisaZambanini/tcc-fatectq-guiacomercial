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
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html'
})
export class AddCategoriaComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  fileName: string;
  filePreview: string

  constructor(
    private sanitizer: DomSanitizer,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoriaService: CategoriaService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      nome: ['', Validators.required]
    });
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
    this.categoriaService.adicionarCategoria(categoria)
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
          this.addForm.reset();
          for (const key in this.addForm.controls) {
            this.addForm.get(key).clearValidators();
            this.addForm.get(key).updateValueAndValidity();
          }
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
