import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';
import { Categoria } from "../../model/categoria.model";

@Component({
  selector: 'app-edit-categoria',
  templateUrl: './edit-categoria.component.html'
})
export class EditCategoriaComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
