import { Component, OnInit } from '@angular/core';
import { FormaPagamento } from "../../model/forma-pagamento.model";
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-edit-forma-pagamento',
  templateUrl: './edit-forma-pagamento.component.html'
})
export class AlterarFormaPagamentoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
