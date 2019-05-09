import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Empresa } from "../../model/empresa.model";

@Component({
  selector: 'app-add-empresa',
  templateUrl: './add-empresa.component.html'
})
export class AddEmpresaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
