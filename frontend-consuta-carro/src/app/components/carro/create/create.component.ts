import { Component, OnInit } from '@angular/core';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { Carro } from '../../model/carro.model';
import { CarroService } from '../../service/carro.service';
import { MarcaService } from '../../service/marca.service';
import { MatDialog } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  carro: Carro

  marcas: MarcaDTO[];

  carroForm: FormGroup;

  constructor(
    private carroService: CarroService, 
    private marcasService: MarcaService,
    private dialogRef: MatDialog,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getForm();
    this.buscarMarcas();
  }

  salvar() {
    this.carro = this.carroForm.value
    this.carro.marcaDTO = this.carroForm.value.marca
    console.log('criando carro');
    this.carroService.criarCarro(this.carro).subscribe(resposta =>{
      if(resposta.sucesso){
        this.carroService.mostrarMensagem(resposta.messagem);
        this.cancelar();
      } else {
        this.carroService.mostrarMensagem(resposta.messagem);
        this.cancelar();
      }
      
    })    
  }

  cancelar(){
    this.dialogRef.closeAll();    
  }

  buscarMarcas(){
    this.marcasService.buscar().subscribe(resposta =>{
      this.marcas = resposta.object
    });
  }
  getForm(){
    this.carroForm = this.formBuilder.group({
      idCarro: [],
      nome: [null, [Validators.required]],
      kmPorGalao: [null, [Validators.required]],
      cilindros: [null, [Validators.required]],
      cavalorDeForca: [null, [Validators.required]],
      peso: [null, [Validators.required]],
      aceleracao: [null, [Validators.required]],
      ano: [null, [Validators.required]],
      origem: [null, [Validators.required]],
      marca: [null, [Validators.required]]
    })
  }
  comparador(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}
