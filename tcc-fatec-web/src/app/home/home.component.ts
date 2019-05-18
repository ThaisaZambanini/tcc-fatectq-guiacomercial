import { Component, OnInit } from '@angular/core';
import { Usuario } from "../model/user.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  usuario: Usuario;

  constructor() { }

  ngOnInit() {
    this.usuario = new Usuario();
    this.usuario = JSON.parse(localStorage.getItem("usuario"));
  }

}
