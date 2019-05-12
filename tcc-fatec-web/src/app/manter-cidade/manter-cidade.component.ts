import { Component, OnInit } from '@angular/core';
import { Cidade } from "../model/cidade.model";
import { Estado } from "../model/uf.model";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NgFlashMessageService } from 'ng-flash-messages';
import { EstadoService } from "../service/estado.service";
import { CidadeService } from "../service/cidade.service";

@Component({
  selector: 'app-manter-cidade',
  templateUrl: './manter-cidade.component.html'
})
export class ManterCidadeComponent implements OnInit {
  addForm: FormGroup;
  cidades: Cidade[];
  estados: Estado[];
  nenhumResultado: boolean = true;
  loading: boolean = false;
  pageActual: number = 1;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private cidadeService: CidadeService, private ngFlashMessageService: NgFlashMessageService) { }

  ngOnInit() {
    this.loading = true;

    this.addForm = this.formBuilder.group({
      estado: ['', Validators.required]
    });

    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
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

    this.cidadeService.getCidadesPorEstado(this.addForm.controls.estado.value).subscribe((data) => {
      this.cidades = data;
      this.nenhumResultado = false;

      if (this.cidades.length === 0) {
        this.nenhumResultado = true;
      }
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  addCidade() {
    this.router.navigate(['add-cidade']);
  }

  editarCidade(cidade: Cidade): void {
    localStorage.removeItem("editCidadeId");
    localStorage.setItem("editCidadeId", cidade.id.toString());
    this.router.navigate(['edit-cidade']);
  }

  excluirCidade(cidade: Cidade): void {
    this.loading = true;

    this.cidadeService.excluirCidade(cidade.id.toString())
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

          document.getElementById('buscar').click();
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

}
