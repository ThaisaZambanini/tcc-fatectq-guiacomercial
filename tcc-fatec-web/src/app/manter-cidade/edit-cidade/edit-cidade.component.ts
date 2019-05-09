import { Component, OnInit } from '@angular/core';
import { Cidade } from "../../model/cidade.model";
import { NgFlashMessageService } from 'ng-flash-messages';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-edit-cidade',
  templateUrl: './edit-cidade.component.html'
})
export class EditCidadeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
