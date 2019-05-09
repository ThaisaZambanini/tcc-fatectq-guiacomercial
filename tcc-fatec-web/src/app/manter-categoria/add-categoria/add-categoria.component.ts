import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Categoria } from "../../model/categoria.model";

@Component({
  selector: 'app-add-categoria',
  templateUrl: './add-categoria.component.html'
})
export class AddCategoriaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
