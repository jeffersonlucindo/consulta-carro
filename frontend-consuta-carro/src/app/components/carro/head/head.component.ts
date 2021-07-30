import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CarroComponent } from '../carro.component';
import { Carro } from '../../model/carro.model';
import { CarroService } from '../../service/carro.service';
import { DeleteComponent } from '../delete/delete.component';
import { UpdateComponent } from '../update/update.component';
import { DetailsComponent } from '../details/details.component';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-head',
  templateUrl: './head.component.html',
  styleUrls: ['./head.component.css']
})
export class HeadComponent implements OnInit {

  
  carros: Carro[];
  displayedColumns = ['nome', 'origem', 'ano', 'details', 'action']
  dataSource ;
  nome: string = '';
  
  constructor(private carroService: CarroService,
    private dialog: MatDialog,
    private carroComponent: CarroComponent) { }

  ngOnInit(): void {
    this.buscar();
    this.carroComponent.emitirCarroCriado.subscribe(() => {
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
  abriDialogUpdate(carro: Carro){
    const dialogRef = this.dialog.open(UpdateComponent, {
      width: '50%',height: '80%'
    })
    dialogRef.componentInstance.carro = carro 
    dialogRef.afterClosed().subscribe(() =>{
      this.buscar();
    })
  }
  buscar() {
    this.carroService.buscar().subscribe(resposta =>{      
      this.carros = resposta.object
      this.dataSource = new MatTableDataSource(this.carros);
    })
  }

  abriDialogDelete(carro: Carro){
    const dialogRef = this.dialog.open(DeleteComponent, {
      width: '20%',height: '20%'
    })
    dialogRef.componentInstance.carro = carro 
    dialogRef.afterClosed().subscribe(() =>{
      this.buscar();
    })
  }

  abriDialogDetails(carro: Carro){
    const dialogRef = this.dialog.open(DetailsComponent, {
      width: '20%',height: '55%'
    })
    dialogRef.componentInstance.carro = carro 
  }

}
