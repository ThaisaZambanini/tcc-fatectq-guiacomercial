import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {
  menuAtivo: string;

  constructor(private router: Router) { }

  ngOnInit() {
  }

  setMenu(menuAtual: string) {
    this.menuAtivo = menuAtual;
  }

  sair() {
    localStorage.removeItem("usuario");
    this.router.navigate(['login']);
  }

}
