import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { MarcaService } from '../../service/marca.service';

@Component({
  selector: 'app-marca-create',
  templateUrl: './marca-create.component.html',
  styleUrls: ['./marca-create.component.css']
})
export class MarcaCreateComponent implements OnInit {

  marcas: MarcaDTO[];

  marcaForm: FormGroup;

  constructor(
    private marcasService: MarcaService,
    private dialogRef: MatDialog,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getForm();
    this.buscarMarcas();
  }

  criarMarca(): void {
    const marca = this.marcaForm.value
    
    
    this.marcasService.criarMarca(marca).subscribe(resposta =>{
      if(resposta.sucesso){
        this.marcasService.mostrarMensagem(resposta.messagem);
        this.cancelar();
      } else {
        this.marcasService.mostrarMensagem(resposta.messagem);
        this.cancelar();
      }
      
    })    
  }

  cancelar(){
    this.dialogRef.closeAll();
    //this.router.navigate(['/carro']);
  }

  buscarMarcas(){
    this.marcasService.buscar().subscribe(resposta =>{
      this.marcas = resposta.object
    });
  }

  buscar(){
    this.marcasService.buscar().subscribe(resposta =>{
      this.marcas = resposta.object
    });
  }

  getForm(){
    this.marcaForm = this.formBuilder.group({
      idMarca: [],
      nome: [null, [Validators.required]],      
      origem: [null, [Validators.required]]      
    })
  }
  comparador(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}
