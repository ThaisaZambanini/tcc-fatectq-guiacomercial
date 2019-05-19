import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Estado } from "../model/uf.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class EstadoService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "estados";

  getEstados(): Observable<Estado[]> {
    return this.http.get(this.baseUrl).pipe(map((response: any) => response.map((estado: Estado) => new Estado().deserialize(estado))));
  }

  adicionarEstado(estado: Estado): Observable<Estado> {
    return this.http.post<Estado>(this.baseUrl, estado);
  }

  getEstado(id: string): Observable<Estado> {
    return this.http.get(`${this.baseUrl}/${id}`).pipe(map((response: any) => new Estado().deserialize(response)));
  }

  alterarEstado(estado: Estado): Observable<any> {
    return this.http.put(`${this.baseUrl}/alterar/${estado.id}`, estado);
  }

  excluirEstado(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deletar/${id}`);
  }

}
