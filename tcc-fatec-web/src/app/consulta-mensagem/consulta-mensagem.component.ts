import { Component, OnInit } from '@angular/core';
import { MensagemUsuario } from "../model/mensagem-usuario.model";
import { Router } from "@angular/router";
import { NgFlashMessageService } from 'ng-flash-messages';
import { MensagemUsuarioService } from "../service/mensagem-usuario.service";

@Component({
  selector: 'app-consulta-mensagem',
  templateUrl: './consulta-mensagem.component.html'
})
export class ConsultaMensagemComponent implements OnInit {
  mensagens: MensagemUsuario[];
  nenhumResultado: boolean = false;
  loading: boolean = false;
  pageActual: number = 1;
  submitted: boolean = false;

  constructor(private router: Router, private mensagemService: MensagemUsuarioService, private ngFlashMessageService: NgFlashMessageService) { }

  ngOnInit() {
    this.loading = true;
    this.mensagemService.getMensagens().subscribe((data) => {
      this.mensagens = data;
      console.log(this.mensagens)
      if (this.mensagens.length === 0) {
        this.nenhumResultado = true;
      }
      this.loading = false;
    }, (err) => {
      this.loading = false;
    });
  }

  voltar() {
    this.router.navigate(['home']);
  }


}
