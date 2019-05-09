import { Component, OnInit } from '@angular/core';
import { Cidade } from "../model/cidade.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';

@Component({
  selector: 'app-manter-cidade',
  templateUrl: './manter-cidade.component.html'
})
export class ManterCidadeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
