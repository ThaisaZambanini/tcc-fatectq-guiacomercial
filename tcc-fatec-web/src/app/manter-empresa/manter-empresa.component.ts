import { Component, OnInit } from '@angular/core';
import { Empresa } from "../model/empresa.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-manter-empresa',
  templateUrl: './manter-empresa.component.html'
})
export class ManterEmpresaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
