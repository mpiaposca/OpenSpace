import { Component, EventEmitter, NgModule, Output } from '@angular/core';

@Component({
  selector: 'app-barra',
  templateUrl: './barra.component.html',
  styleUrls: ['./barra.component.css']
})

export class BarraComponent {
  @Output() mostraC = new EventEmitter<boolean>();
  showHome: boolean = false;
  
  public async ngOnInit() {
  }

  public mostraCarrello(){
    this.mostraC.emit(true);
    this.showHome = true;
  }

  public mostraHome(){
    this.mostraC.emit(false);
    this.showHome = false;
  }

}