import { Component, OnInit } from '@angular/core';
import { Estado } from "../model/uf.model";
import { Router } from "@angular/router";
import { EstadoService } from "../service/estado.service";

@Component({
  selector: 'app-manter-uf',
  templateUrl: './manter-uf.component.html'
})
export class ManterUfComponent implements OnInit {
  estados: Estado[];
  nenhumResultado: boolean = false;

  constructor(private router: Router, private estadoService: EstadoService) { }

  pageActual = 1;

  //Método inicial, é chamado no carregamento da página
  ngOnInit() {
    this.estadoService.getEstados().subscribe((data) => {
      this.estados = data;

      if (this.estados.length === 0) {
        this.nenhumResultado = true;
      }
    }, (err) => {
      console.log(err);
    });
  }

  addEstado() {
    this.router.navigate(['add-uf']);
  }

}