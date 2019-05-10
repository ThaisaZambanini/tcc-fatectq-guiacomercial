import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Cidade } from "../../model/cidade.model";
import { Estado } from "../../model/uf.model";
import { CidadeService } from "../../service/cidade.service";
import { EstadoService } from "../../service/estado.service";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-add-cidade',
  templateUrl: './add-cidade.component.html'
})
export class AdicionarCidadeComponent implements OnInit {
  addForm: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  estados: Estado[];

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService, private cidadeService: CidadeService, private ngFlashMessageService: NgFlashMessageService) { }

  ngOnInit() {
    this.loading = true;
    this.addForm = this.formBuilder.group({
      estado: ['', Validators.required],
      nome: ['', Validators.required]
    });

    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  voltar() {
    this.router.navigate(['manter-cidade']);
  }

  onSubmit() {
    this.submitted = true;
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
    const cidade = new Cidade().deserialize(this.addForm.value);

    this.cidadeService.adicionarCidade(this.addForm.controls.estado.value, cidade).subscribe(data => {
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
