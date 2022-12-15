import { AppModule } from '../../app.module';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Prodotto } from './prodotto';

@Injectable()

export class ProdottoService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getProdotti(pn: number): Observable<Prodotto[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("pageNumber",pn);
    return this.http.get<Prodotto[]>(this.apiServerUrl+"/prodotto/paged", {params: queryParams});
  }

  
}