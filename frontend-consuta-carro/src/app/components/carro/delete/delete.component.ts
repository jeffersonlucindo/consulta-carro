import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Carro } from '../../model/carro.model';
import { CarroService } from '../../service/carro.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  @Input() carro : Carro;

  constructor(
    private carroService: CarroService,
    private dialogRef: MatDialog) { }

  ngOnInit(): void {
  }

  deletar(){
    this.carroService.deletar(this.carro).subscribe(resposta =>{
      if(resposta.sucesso){
        this.dialogRef.closeAll()
        this.carroService.mostrarMensagem(resposta.messagem);
      } else{
        this.carroService.mostrarMensagem(resposta.messagem);
      }
    })
  }

  cancelar(){
    this.dialogRef.closeAll()
  }
}
