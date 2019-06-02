import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  exibeMenu: boolean;

  constructor(public router: Router) { }

  ngOnInit() {
    let url = window.location.href;
    if (url.includes("login")) {
      this.exibeMenu = false;
    } else {
      this.exibeMenu = true;
    }
  }
}
