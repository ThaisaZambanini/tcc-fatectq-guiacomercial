import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;
  constructor(private formBuilder: FormBuilder, private router: Router, public service: UserService, ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      cpf: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.service.getUsuarios().subscribe((data: {}) => {
      console.log(data);
    }, (err) => {
      console.log(err);
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    if (this.loginForm.controls.cpf.value == '40700081879' && this.loginForm.controls.password.value == '123') {
      this.router.navigate(['home']);
    } else {
      this.invalidLogin = true;
    }
  }
}
