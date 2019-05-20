import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Empresa } from "../model/empresa.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';
import { FiltroEmpresa } from "../model/filtroEmpresa.model";

@Injectable()
export class EmpresaService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "empresas";

  getEmpresasFiltroWeb(filtro: FiltroEmpresa): Observable<Empresa[]> {
    let params = new HttpParams()
      .set('cidade', filtro.cidade)
      .set('estado', filtro.estado);

    return this.http.get(`${this.baseUrl}/filtro`, { params }).pipe(map((response: any) => response.map((empresa: Empresa) => new Empresa().deserialize(empresa))));
  }

  adicionarEmpresa(empresa: Empresa): Observable<any> {
    return this.http.post<Empresa>(${this.baseUrl}, empresa);
  }

  getEmpresa(id: string): Observable<Empresa> {
    return this.http.get(`${this.baseUrl}/${id}`).pipe(map((response: any) => new Empresa().deserialize(response)));
  }

}
