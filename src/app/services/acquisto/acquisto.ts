import { ProdottoInAcquisto } from "../prodottoinacquisto/prodottoinacquisto"
import { Utente } from "../utente/utente"
export interface Acquisto {
  id?:number,
  utente: Utente,
  purchaseTime?:Date,
  listaProdotti: ProdottoInAcquisto[]
}