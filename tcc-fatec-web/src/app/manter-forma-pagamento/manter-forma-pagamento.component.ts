import { Component, OnInit } from '@angular/core';
import { FormaPagamento } from "../model/forma-pagamento.model";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormaPagamentoService } from "../service/forma-pagamento.service";

@Component({
  selector: 'app-manter-forma-pagamento',
  templateUrl: './manter-forma-pagamento.component.html'
})
export class ManterFormaPagamentoComponent implements OnInit {
  addForm: FormGroup;
  formasPagamento: FormaPagamento[];
  loading: boolean = false;
  pageActual: number = 1;
  submitted: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private formaPagamentoService: FormaPagamentoService,
    private ngFlashMessageService: NgFlashMessageService
  ) { }

  ngOnInit() {
    this.loading = true;
    this.formasPagamento = new Array();

    this.addForm = this.formBuilder.group({
      id: [''],
      nome: ['', Validators.required]
    });

    this.getFormasPagamento("");
  }

  getFormasPagamento(formaPagamento: string) {
    this.formaPagamentoService.getFormasPagamento(formaPagamento).subscribe((data) => {
      this.formasPagamento = data;
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
    this.getFormasPagamento(this.addForm.controls.nome.value);
  }

  addFormaPagamento() {
    this.router.navigate(['add-forma-pagamento']);
  }

  excluirForma(forma: FormaPagamento) {
    this.loading = true;

    this.formaPagamentoService.excluirFormaPagamento(forma.id.toString())
      .subscribe(data => {
        this.loading = false;
        var res = JSON.parse(JSON.stringify(data));
        if (res.codigoRetorno === 0) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });

          this.getFormasPagamento("");
          window.scrollTo(5000, 0);
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
        window.scrollTo(5000, 0);
      }, (err) => {
        this.loading = false;
        var res = JSON.parse(JSON.stringify(err));
        this.ngFlashMessageService.showFlashMessage({
          messages: [res.error.mensagem || "Falha ao excluir!"],
          dismissible: true,
          timeout: false,
          type: 'danger'
        });
        window.scrollTo(5000, 0);
      });
  }

  limparCampos() {
    this.loading = true;
    this.getFormasPagamento("");
    this.addForm.reset();
    for (const key in this.addForm.controls) {
      this.addForm.get(key).clearValidators();
      this.addForm.get(key).updateValueAndValidity();
    }
  }

  editarForma(forma: FormaPagamento): void {
    localStorage.removeItem("editFormaPagamentoId");
    localStorage.setItem("editFormaPagamentoId", forma.id.toString());
    this.router.navigate(['edit-forma-pagamento']);
  }

}
