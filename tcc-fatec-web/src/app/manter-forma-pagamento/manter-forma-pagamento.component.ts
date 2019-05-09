import { Component, OnInit } from '@angular/core';
import { FormaPagamento } from "../model/forma-pagamento.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-manter-forma-pagamento',
  templateUrl: './manter-forma-pagamento.component.html'
})
export class ManterFormaPagamentoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
