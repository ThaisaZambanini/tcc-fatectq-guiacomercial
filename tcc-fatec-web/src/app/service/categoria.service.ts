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

  getCategorias(categoria: string): Observable<Categoria[]> {
    let params = new HttpParams().set('categoria', categoria);
    return this.http.get(`${this.baseUrl}/`, {params}).pipe(map((response: any) => response.map((categoria: Categoria) => new Categoria().deserialize(categoria))));
  }

  adicionarCategoria(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.baseUrl + "/adicionar", categoria);
  }

  getCategoria(id: string): Observable<Categoria> {
    return this.http.get(`${this.baseUrl}/${id}`).pipe(map((response: any) => new Categoria().deserialize(response)));
  }

  alterarCategoria(categoria: Categoria): Observable<any> {
    return this.http.put(`${this.baseUrl}/alterar/${categoria.id}`, categoria);
  }

}
