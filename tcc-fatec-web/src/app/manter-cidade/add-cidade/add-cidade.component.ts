import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Cidade } from "../../model/cidade.model";

@Component({
  selector: 'app-add-cidade',
  templateUrl: './add-cidade.component.html'
})
export class AdicionarCidadeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
