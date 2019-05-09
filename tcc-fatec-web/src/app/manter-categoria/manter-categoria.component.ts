import { Component, OnInit } from '@angular/core';
import { Categoria } from "../model/categoria.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-manter-categoria',
  templateUrl: './manter-categoria.component.html'
})
export class ManterCategoriaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
