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
  baseUrl = environment.baseUrl + "formaPagamento";

}
