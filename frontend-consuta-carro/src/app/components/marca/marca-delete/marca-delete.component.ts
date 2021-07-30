import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { MarcaService } from '../../service/marca.service';

@Component({
  selector: 'app-marca-delete',
  templateUrl: './marca-delete.component.html',
  styleUrls: ['./marca-delete.component.css']
})
export class MarcaDeleteComponent implements OnInit {

  @Input() marca: MarcaDTO;

  constructor(
    private marcaService: MarcaService,
    private dialogRef: MatDialog) { }

  ngOnInit(): void {
  }

  deletar(){
    this.marcaService.deletar(this.marca).subscribe(resposta =>{
      if(resposta.sucesso){
        this.dialogRef.closeAll()
        this.marcaService.mostrarMensagem(resposta.messagem);
      } else{
        this.dialogRef.closeAll()
        this.marcaService.mostrarMensagem(resposta.messagem);
      }
    })
  }

  cancelar(){
    this.dialogRef.closeAll()
  }

}
