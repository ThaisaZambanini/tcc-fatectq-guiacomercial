import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { FormaPagamento } from "../../model/forma-pagamento.model";

@Component({
  selector: 'app-add-forma-pagamento',
  templateUrl: './add-forma-pagamento.component.html'
})
export class AdicionarPagamentoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
