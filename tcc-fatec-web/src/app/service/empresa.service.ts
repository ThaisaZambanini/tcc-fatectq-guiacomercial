import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Empresa } from "../model/empresa.model";
import { environment } from '../../environments/environment';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { map } from 'rxjs/operators';

@Injectable()
export class EmpresaService {
  constructor(private http: HttpClient) { }
  baseUrl = environment.baseUrl + "empresa";
}
