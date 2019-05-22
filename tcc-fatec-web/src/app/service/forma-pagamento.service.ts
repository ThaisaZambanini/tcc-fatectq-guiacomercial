import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { FormaPagamento } from "../model/forma-pagamento.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class FormaPagamentoService {

  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "formasPagamento";

  getFormasPagamento(formaPagamento: string): Observable<FormaPagamento[]> {
    if (formaPagamento !== '' && formaPagamento !== null && formaPagamento !== undefined) {
      let params = new HttpParams().set('formaPagamento', formaPagamento);
      return this.http.get(`${this.baseUrl}`, { params }).pipe(map((response: any) => response.map((forma: FormaPagamento) => new FormaPagamento().deserialize(forma))));
    } else {
      let params = new HttpParams();
      return this.http.get(`${this.baseUrl}`, { params }).pipe(map((response: any) => response.map((forma: FormaPagamento) => new FormaPagamento().deserialize(forma))));
    }
  }

  adicionarFormaPagamento(forma: FormaPagamento): Observable<FormaPagamento> {
    return this.http.post<FormaPagamento>(`${this.baseUrl}`, forma);
  }

  getFormaPagamento(id: string): Observable<FormaPagamento> {
    return this.http.get(`${this.baseUrl}/${id}`).pipe(map((response: any) => new FormaPagamento().deserialize(response)));
  }

  alterarFormaPagamento(forma: FormaPagamento): Observable<any> {
    return this.http.put(`${this.baseUrl}/${forma.id}`, forma);
  }

  excluirFormaPagamento(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

}
