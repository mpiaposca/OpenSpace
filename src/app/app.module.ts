import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClientModule } from "@angular/common/http";
import { MatButtonModule } from "@angular/material/button";
import { BrowserModule } from "@angular/platform-browser";
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { BarraComponent } from "./barra/barra.component";
import { CarrelloComponent } from "./carrello/carrello.component";
import { HomeComponent } from "./home/home.component";
import { ProdottoService } from "./services/prodotto/prodotto.service";
import { StoreComponent } from "./store/store.component";
import { AcquistiComponent } from './acquisti/acquisti.component';
import { NgModule } from '@angular/core';
import { UtenteService } from './services/utente/utente.service';
import { AcquistoService } from './services/acquisto/acquisto.service';



@NgModule({
  declarations: [AppComponent, StoreComponent, BarraComponent, CarrelloComponent, HomeComponent, AcquistiComponent],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    MatSnackBarModule,
    MatFormFieldModule
  ],
  providers: [ ProdottoService, AcquistoService, UtenteService],
  bootstrap: [AppComponent],
})
export class AppModule {}