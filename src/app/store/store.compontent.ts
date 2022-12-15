import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Prodotto } from '../services/prodotto/prodotto';
import { ProdottoService } from '../services/prodotto/prodotto.service';

@Component({
  selector: 'app-store',
  templateUrl: '../store.component.html',
  styleUrls: ['../store.component.css'],
})
export class StoreComponent implements OnInit {
  pageN: number = 0;
  private finite: boolean = false;
  prodottiR1: Prodotto[] = [];
  prodottiR2: Prodotto[] = [];
  prodottiR3: Prodotto[] = [];
  mostraProdotti: boolean = false;
  prodottiCarrello: Prodotto[] = [];
  @Output() mostraC = new EventEmitter<Prodotto[]>();

  constructor( private ProdottoService: ProdottoService) { }

  public ngOnInit(){
    this.getProdotti(this.pageN);
  }

  public getProdotti(page: number){
    var i : number = 0;
    this.ProdottoService.getProdotti(page).subscribe((result: Prodotto[]) => {
      var r1: number = result.length/3;
     
      if(result[0] != undefined){
        this.prodottiR1 = [];
        this.prodottiR2 = [];
        this.prodottiR3 = [];
        result.forEach((t: Prodotto) => {
          if( i<5){
            this.prodottiR1.push(t);
          }
          if(i >= 5 && i <10){
            this.prodottiR2.push(t);
          }
          if(i >= 10){
            this.prodottiR3.push(t);
          }       
          i++; 
        });
        this.finite = false;
      } else{
        this.finite = true;
        this.pageN--;
      }
     
    });
    this.mostraProdotti = true;
  }

  public nextPage(){
    if(!this.finite){  
      this.pageN++;
      this.getProdotti(this.pageN);
    }
  }

  public prevPage(){
    if(this.pageN > 0){
      this.pageN--;
      this.getProdotti(this.pageN);
    }
  }

  public addToCart(prodotto: Prodotto){
    this.prodottiCarrello.push(prodotto);
  }

  public mostraCarrello(){
    this.mostraC.emit(this.prodottiCarrello);
  }
}