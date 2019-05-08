import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { UserService } from "../service/user.service";
import { MensagemRetorno } from "../model/mensagem.model";

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
      senha: ['', Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    const cpf = this.loginForm.controls.cpf.value;
    const senha = this.loginForm.controls.senha.value;
    this.service.getUsuarioAutenticado(cpf, senha).subscribe((data: {}) => {
      if (data) {
        var resp = JSON.parse(JSON.stringify(data));
        if (resp.codigoRetorno === 0) {
          this.router.navigate(['home']);
        } else {
          this.invalidLogin = true;
        }
      } else {
        this.invalidLogin = true;
      }
    }, (err) => {
      console.log(err);
      this.invalidLogin = true;
    });
  }
}
