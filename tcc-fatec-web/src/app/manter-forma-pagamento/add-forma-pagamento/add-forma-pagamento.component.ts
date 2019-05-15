import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormaPagamentoService } from "../../service/forma-pagamento.service";
import { FormaPagamento } from "../../model/forma-pagamento.model";

@Component({
  selector: 'app-add-forma-pagamento',
  templateUrl: './add-forma-pagamento.component.html'
})
export class AdicionarPagamentoComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
    formaPagamento: FormaPagamento;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private formaPagamentoService: FormaPagamentoService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.formaPagamento = new FormaPagamento();
    this.addForm = this.formBuilder.group({
      id: [''],
      descricao: ['', Validators.required]
    });
  }

  voltar() {
    this.router.navigate(['manter-forma-pagamento']);
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

    const formaPagamento = new FormaPagamento().deserialize(this.formaPagamento);
    // chama o serviço para adicionar os dados
    this.formaPagamentoService.adicionarFormaPagamento(formaPagamento)
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
