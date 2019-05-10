import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Categoria } from "../model/categoria.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class CategoriaService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "categoria";

  // getCategorias(estado: string): Observable<Categoria[]> {
  //   return this.http.get(`${this.baseUrl}/${estado}`).pipe(map((response: any) => response.map((categoria: Categoria) => new Categoria().deserialize(cidade))));
  // }

}
