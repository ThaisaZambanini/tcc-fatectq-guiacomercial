import { Component, OnInit } from '@angular/core';
import { Cidade } from "../../model/cidade.model";
import { Estado } from "../../model/uf.model";
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { CidadeService } from "../../service/cidade.service";
import { EstadoService } from "../../service/estado.service";

@Component({
  selector: 'app-edit-cidade',
  templateUrl: './edit-cidade.component.html'
})
export class EditCidadeComponent implements OnInit {
  estados: Estado[];
  loading: boolean = false;
  addForm: FormGroup;
  submitted: boolean = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private cidadeService: CidadeService, private ngFlashMessageService: NgFlashMessageService) { }

  ngOnInit() {
    this.loading = true;
    this.addForm = this.formBuilder.group({
      id: [],
      estado: ['', Validators.required],
      nome: ['', Validators.required]
    });

    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });

    const editCidadeId = localStorage.getItem("editCidadeId");
    localStorage.removeItem("editCidadeId");
    if (!editCidadeId) {
      this.router.navigate(['manter-cidade']);
      return;
    } else {
      this.cidadeService.getCidade(editCidadeId)
        .subscribe(data => {
          this.addForm.setValue(data);
          const cidade = new Cidade().deserialize(data)
          this.addForm.controls['estado'].patchValue(cidade.estado.id);
        });
    }
  }

  voltar() {
    this.router.navigate(['manter-cidade']);
  }

  compareFn(c1: Estado, c2: Estado): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    //verifica se tem campos em brancos
    if (this.addForm.invalid) {
      this.loading = false;
      this.ngFlashMessageService.showFlashMessage({
        messages: ["Dados inválidos!"],
        dismissible: true,
        timeout: false,
        type: 'danger'
      });
      return;
    }

    const cidade = new Cidade().deserialize(this.addForm.value);
    cidade.estado.id = this.addForm.controls.estado.value;
    console.log(cidade);

    this.cidadeService.alterarCidade(cidade)
      .subscribe(data => {
        var res = JSON.parse(JSON.stringify(data));
        if (res.codigoRetorno === 0) {
          this.ngFlashMessageService.showFlashMessage({
            messages: [res.mensagem],
            dismissible: true,
            timeout: false,
            type: 'success'
          });
          this.router.navigate(['manter-cidade']);
          this.loading = false;
        } else {
          if (data != null) {
            this.loading = false;
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
