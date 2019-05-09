import { Component, OnInit } from '@angular/core';
import { Estado } from "../model/uf.model";
import { Router } from "@angular/router";
import { EstadoService } from "../service/estado.service";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-manter-uf',
  templateUrl: './manter-uf.component.html'
})
export class ManterUfComponent implements OnInit {
  estados: Estado[];
  nenhumResultado: boolean = false;
  loading: boolean = false;

  constructor(private router: Router, private estadoService: EstadoService, private ngFlashMessageService: NgFlashMessageService) { }

  pageActual = 1;

  //Método inicial, é chamado no carregamento da página
  ngOnInit() {
    this.loading = true;
    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;

      if (this.estados.length === 0) {
        this.nenhumResultado = true;
      }
      this.loading = false;
    }, (err) => {
      console.log(err);
      this.loading = false;
    });
  }

  editarEstado(estado: Estado): void {
    localStorage.removeItem("editEstadoId");
    localStorage.setItem("editEstadoId", estado.id.toString());
    this.router.navigate(['edit-uf']);
  }

  excluirEstado(estado: Estado): void {
      this.loading = true;

    this.estadoService.excluirEstado(estado.id.toString())
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

          this.estadoService.getEstados().subscribe((data) => {
            this.estados = data;

            if (this.estados.length === 0) {
              this.nenhumResultado = true;
            }
          }, (err) => {
            console.log(err);
          });
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
          messages: [res.error.mensagem || "Falha ao excluir!"],
          dismissible: true,
          timeout: false,
          type: 'danger'
        });
      });
  }

  addEstado() {
    this.router.navigate(['add-uf']);
  }

}
