import { ProdottoInAcquisto } from './prodottoinacquisto';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class ProdottoInAcquistoService {
  private apiServerUrl = environment.apiBaseUrl; 
  
  constructor(private http: HttpClient) { }

  getProdottoInAcquisto(acquistoId: number): Observable<ProdottoInAcquisto[]>{
    return this.http.get<ProdottoInAcquisto[]>(this.apiServerUrl+"/acquisto/prodottoInAcquisto/"+acquistoId);
  }
}