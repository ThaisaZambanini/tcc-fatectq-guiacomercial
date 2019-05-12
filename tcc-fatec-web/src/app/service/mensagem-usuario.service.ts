import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { MensagemUsuario } from "../model/mensagem-usuario.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class MensagemUsuarioService {

  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "mensagem";

  getMensagens(): Observable<MensagemUsuario[]> {
    return this.http.get(`${this.baseUrl}/`).pipe(map((response: any) => response.map((mensagem: MensagemUsuario) => new MensagemUsuario().deserialize(mensagem))));
  }

}
