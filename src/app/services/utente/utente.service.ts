import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Utente } from './utente';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UtenteService {
  private apiServerUrl = environment.apiBaseUrl; 

  constructor(private http: HttpClient) { }

}