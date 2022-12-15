import { Acquisto } from "../acquisto/acquisto"
import { Prodotto } from "../prodotto/prodotto"


export interface ProdottoInAcquisto {
  id?:number,
  acquisto:Acquisto,
  quantita:number,
  prezzo: number,
  product: Prodotto

}