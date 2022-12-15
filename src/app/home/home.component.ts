import { Component, NgModule, OnInit, ViewChild } from '@angular/core';
import { Prodotto } from '../services/prodotto/prodotto';
import { StoreComponent } from '../store/store.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  mostraC: boolean = false;
  @ViewChild(StoreComponent)
  private child!: StoreComponent;
  prodottiCarrello: Prodotto[] = [];
  
  constructor() {}
  ngOnInit(): void {
  }

  mostraCarrello(mostra: boolean){
    this.mostraC = mostra;
    this.child.mostraCarrello();
  }

  addToCart(prodotti: Prodotto[]){
      this.prodottiCarrello = prodotti;
  }
}