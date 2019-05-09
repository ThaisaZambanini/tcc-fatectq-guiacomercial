import { Component, OnInit } from '@angular/core';
import { Cidade } from "../model/cidade.model";
import { Estado } from "../model/uf.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';
import { EstadoService } from "../service/estado.service";
import { CidadeService } from "../service/cidade.service";

@Component({
  selector: 'app-manter-cidade',
  templateUrl: './manter-cidade.component.html'
})
export class ManterCidadeComponent implements OnInit {
  cidades: Cidade[];
  estados: Estado[];
  nenhumResultado: boolean = false;
  loading: boolean = false;
  pageActual: number = 1;

  constructor(private router: Router, private estadoService: EstadoService, private cidadeService: CidadeService, private ngFlashMessageService: NgFlashMessageService) { }

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

}
