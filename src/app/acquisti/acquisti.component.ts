import { ProdottoInAcquistoService } from './../services/prodottoinacquisto/prodottoinacquisto.service';
import { ProdottoInAcquisto } from './../services/prodottoinacquisto/prodottoinacquisto';
import { AcquistoService } from './../services/acquisto/acquisto.service';
import { Component, OnInit } from '@angular/core';
import { Acquisto } from '../services/acquisto/acquisto';

@Component({
  selector: 'app-ordini',
  templateUrl: './ordini.component.html',
  styleUrls: ['./ordini.component.css']
})
export class OrdiniComponent implements OnInit {
  public ordiniEffettuati: Acquisto[] = [];
  public lineeOrdini: ProdottoInAcquisto[][] = [];
  public isLoggedIn = false;
  public userProfile: null = null; /**na bella schifezza*/
  
  constructor(private acquistoService: AcquistoService, private piaService: ProdottoInAcquistoService) { }

  public async ngOnInit() {
    this.acquistoService.getByEmail(this.userProfile?.email as string).subscribe((result: ordine[]) =>{
      result.forEach((a: Acquisto) => {
        this.ordiniEffettuati.push(a);
        this.loService.getLineeOrdine(a.id).subscribe((resLO: ProdottoInAcquisto[]) => {
          this.lineeOrdini.push(resLO);
        });
      });
    });
  }
}