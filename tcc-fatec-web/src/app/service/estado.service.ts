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
  baseUrl = environment.baseUrl + "estado";

  getEstados(): Observable<Estado[]> {
    return this.http.get(this.baseUrl + "/").pipe(map((response: any) => response.map((estado: Estado) => new Estado().deserialize(estado))));
  }

  adicionarEstado(estado: Estado): Observable<Estado> {
    return this.http.post<Estado>(this.baseUrl + "/adicionar", estado);
  }

}
