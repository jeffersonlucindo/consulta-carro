import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { MarcaService } from '../../service/marca.service';
import { MarcaDeleteComponent } from '../marca-delete/marca-delete.component';
import { MarcaUpdateComponent } from '../marca-update/marca-update.component';
import { MarcaComponent } from '../marca.component';

@Component({
  selector: 'app-marca-head',
  templateUrl: './marca-head.component.html',
  styleUrls: ['./marca-head.component.css']
})
export class MarcaHeadComponent implements OnInit {

  marcas: MarcaDTO[];
  displayedColumns = ['nome', 'origem', 'action']
  dataSource ;

  constructor(
    private marcaService: MarcaService,
    private dialog: MatDialog,
    private marcaComponente: MarcaComponent) { }

  ngOnInit(): void {
    this.buscar();
    this.marcaComponente.emitirCarroCriado.subscribe(() => {
      this.buscar();
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  applyFilter2(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    if(filterValue.length == 2){
      this.dataSource.filter = filterValue.trim().toLowerCase();
    } 
    if(filterValue.length != 2) {
      this.dataSource.filter = '';
    }    
  }

  abriDialogUpdate(marcaDTO: MarcaDTO){
    const dialogRef = this.dialog.open(MarcaUpdateComponent, {
      width: '50%',height: '80%'
    })
    dialogRef.componentInstance.marca = marcaDTO 
    dialogRef.afterClosed().subscribe(() =>{      
      this.buscar();
    })
  }

  buscar() {
    this.marcaService.buscar().subscribe(resposta =>{      
      this.marcas = resposta.object
      this.dataSource = new MatTableDataSource(this.marcas);
    })
  }

  abriDialogDelete(marcaDTO: MarcaDTO){
    const dialogRef = this.dialog.open(MarcaDeleteComponent, {
      width: '20%',height: '20%'
    })
    dialogRef.componentInstance.marca = marcaDTO 
    dialogRef.afterClosed().subscribe(() =>{
      this.buscar();
    })
  }
}
