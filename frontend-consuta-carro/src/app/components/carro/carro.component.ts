import { Component, OnInit,EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

import { CreateComponent } from './create/create.component';

@Component({
  selector: 'app-carro',
  templateUrl: './carro.component.html',
  styleUrls: ['./carro.component.css']
})
export class CarroComponent implements OnInit {

  emitirCarroCriado = new EventEmitter<string>();

  constructor(private router: Router,
    private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  abriDialogCreate(){
    const dialogRef = this.dialog.open(CreateComponent, {
      width: '50%',height: '80%'
    })
    dialogRef.afterClosed().subscribe(() =>{
      this.emitirCarroCriado.emit("Carro Criado");
    })
  }
}
