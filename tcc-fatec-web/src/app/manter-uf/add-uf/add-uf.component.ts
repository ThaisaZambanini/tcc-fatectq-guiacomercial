import { Component, OnInit } from '@angular/core';
import { Estado } from "../../model/uf.model";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { EstadoService } from "../../service/estado.service";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-add-uf',
  templateUrl: './add-uf.component.html'
})
export class AdicionarUfComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private ngFlashMessageService: NgFlashMessageService) { }

  //Método inicial, é chamado no carregamento da página
  ngOnInit() {
    this.addForm = this.formBuilder.group({
      nome: ['', Validators.required]
    });
  }

  voltar() {
    this.router.navigate(['manter-uf']);
  }

  //chamado quando clica no botão adicionar para dar submit no formulario
  onSubmit() {
    this.submitted = true;
    //verifica se tem campos em brancos
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

    const estado = new Estado().deserialize(this.addForm.value);
    // chama o serviço para adicionar os dados
    this.estadoService.adicionarEstado(estado)
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
