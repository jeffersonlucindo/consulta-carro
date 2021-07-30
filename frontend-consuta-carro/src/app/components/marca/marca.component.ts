import { Component, OnInit,EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MarcaCreateComponent } from './marca-create/marca-create.component';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {

  emitirCarroCriado = new EventEmitter<string>();

  constructor(
    private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }


  abriDialogCreate(){
    const dialogRef = this.dialog.open(MarcaCreateComponent, {
      width: '50%',height: '80%'
    })
    dialogRef.afterClosed().subscribe(() =>{
      this.emitirCarroCriado.emit("Carro Criado");
    })
  }
}
