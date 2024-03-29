import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Usuario } from "../model/user.model";
import { environment } from '../../environments/environment';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "usuario";

  getUsuarios(): Observable<any> {
    return this.http.get<Usuario>(this.baseUrl + "/");
  }

  getUsuarioAutenticado(cpf: string, senha: string): Observable<Usuario> {
    let headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' });
    let params: HttpParams = new HttpParams();
    params.set('cpf', cpf);
    params.set('senha', senha);

    return this.http.get<Usuario>(this.baseUrl + "/autenticar?cpf=" + cpf + "&senha=" + senha, { headers: headers });
  }

  public isAuthenticated(): boolean {
    let usuario = new Usuario();
    usuario = JSON.parse(localStorage.getItem("usuario"));

    return usuario !== null && usuario !== undefined;
  }

}
