import { Component, OnInit } from '@angular/core';
import { Estado } from "../../model/uf.model";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { EstadoService } from "../../service/estado.service";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-edit-uf',
  templateUrl: './edit-uf.component.html'
})
export class AlterarUfComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private ngFlashMessageService: NgFlashMessageService) { }

  //Método inicial, é chamado no carregamento da página
  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: [],
      nome: ['', Validators.required]
    });

    const editEstadoId = localStorage.getItem("editEstadoId");
    localStorage.removeItem("editEstadoId");
    if (!editEstadoId) {
      this.router.navigate(['manter-uf']);
      return;
    } else {
      this.estadoService.getEstado(editEstadoId)
        .subscribe(data => {
          console.log(data)
          this.addForm.setValue(data);
        });
    }
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
        messages: ["Dados inválidos!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      return;
    }

    const estado = new Estado().deserialize(this.addForm.value);
    console.log(estado)
    this.estadoService.alterarEstado(estado)
      .subscribe(data => {
        var res = JSON.parse(JSON.stringify(data));
        console.log(data)
        if (res.codigoRetorno === 0) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });
          this.router.navigate(['manter-uf']);
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
