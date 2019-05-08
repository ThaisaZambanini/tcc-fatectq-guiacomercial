import { Component, OnInit } from '@angular/core';
import { Estado } from "../../model/uf.model";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { EstadoService } from "../../service/estado.service";

@Component({
  selector: 'app-add-uf',
  templateUrl: './add-uf.component.html'
})
export class AdicionarUfComponent implements OnInit {
  addForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private estadoService: EstadoService) { }

  //Método inicial, é chamado no carregamento da página
  ngOnInit() {

    this.addForm = this.formBuilder.group({
      nome: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.addForm.invalid) {
      return;
    }

    this.estadoService.adicionarEstado(new Estado().deserialize(this.addForm.value))
      .subscribe(data => {
        console.log(data);
      });
  }
}
