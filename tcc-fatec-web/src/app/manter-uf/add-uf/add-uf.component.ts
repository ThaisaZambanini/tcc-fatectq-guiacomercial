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
    //verifica se tem campos em brancos
    if (this.addForm.invalid) {
      return;
    }

    // chama o serviço para adicionar os dados
    this.estadoService.adicionarEstado(new Estado().deserialize(this.addForm.value))
      .subscribe(data => {
        var res = JSON.parse(JSON.stringify(data));

        if (res.codigoRetorno === 1) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });
          this.addForm.reset();
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
