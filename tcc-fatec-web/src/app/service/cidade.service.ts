import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Cidade } from "../model/cidade.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class CidadeService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "cidades";

  getCidadesPorEstado(estado: string): Observable<Cidade[]> {
    return this.http.get(`${this.baseUrl}/estado/${estado}`).pipe(map((response: any) => response.map((cidade: Cidade) => new Cidade().deserialize(cidade))));
  }

  adicionarCidade(estado: string, cidade: Cidade): Observable<any> {
    return this.http.post<Cidade>(`${this.baseUrl}/estado/${estado}`, cidade);
  }

  getCidade(id: string): Observable<Cidade> {
    return this.http.get(`${this.baseUrl}/${id}`).pipe(map((response: any) => new Cidade().deserialize(response)));
  }

  alterarCidade(cidade: Cidade): Observable<any> {
    return this.http.put(`${this.baseUrl}/${cidade.id}`, cidade);
  }

  excluirCidade(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
