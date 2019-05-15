import { Component, OnInit } from '@angular/core';
import { FormaPagamento } from "../../model/forma-pagamento.model";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormaPagamentoService } from "../../service/forma-pagamento.service";

@Component({
  selector: 'app-edit-forma-pagamento',
  templateUrl: './edit-forma-pagamento.component.html'
})
export class AlterarFormaPagamentoComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  formaPagamento: FormaPagamento;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private formaPagamentoService: FormaPagamentoService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.loading = true;
    this.formaPagamento = new FormaPagamento();
    this.addForm = this.formBuilder.group({
      id: [],
      descricao: ['', Validators.required]
    });

    const editFormaPagamentoId = localStorage.getItem("editFormaPagamentoId");
    localStorage.removeItem("editFormaPagamentoId");
    if (!editFormaPagamentoId) {
      this.router.navigate(['manter-forma-pagamento']);
      return;
    } else {
      this.formaPagamentoService.getFormaPagamento(editFormaPagamentoId)
        .subscribe(data => {
          this.formaPagamento = new FormaPagamento().deserialize(data)
          this.loading = false;
        });
    }
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
    this.formaPagamentoService.alterarFormaPagamento(formaPagamento)
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
          this.router.navigate(['manter-forma-pagamento']);
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
