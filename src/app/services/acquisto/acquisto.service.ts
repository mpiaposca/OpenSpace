import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Acquisto } from './acquisto';

@Injectable({
  providedIn: 'root'
})
export class AcquistoService {
  private apiServerUrl = environment.apiBaseUrl; 
  
  constructor(private http: HttpClient) { }

  crea(a: Acquisto){
    this.http.post<Acquisto>(this.apiServerUrl+"/acquisto", a).subscribe();
  }

  getByEmail(email: string): Observable<Acquisto[]>{
    return this.http.get<Acquisto[]>(this.apiServerUrl+"/acquisto/getByEmail/"+email);
  }
}