import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Estado } from "../model/uf.model";
import { environment } from '../../environments/environment';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable()
export class EstadoService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "estado";

  getEstados(): Observable<any> {
    return this.http.get<Estado>(this.baseUrl + "/");
  }

  adicionarEstado(estado: Estado) {
  }

}
