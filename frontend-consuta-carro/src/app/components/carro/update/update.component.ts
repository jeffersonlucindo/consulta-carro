import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MarcaService } from '../../service/marca.service';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { Carro } from '../../model/carro.model';
import { CarroService } from '../../service/carro.service';
import { HeadComponent } from '../head/head.component';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {

  @Input() carro: Carro;
  marcas: MarcaDTO[];
  carroForm: FormGroup;

  constructor(private carroService: CarroService,     
    private marcaService: MarcaService,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialog) { }

  ngOnInit(): void {
    this.getForm();
    this.marcaService.buscar().subscribe(resposta =>{
      this.marcas = resposta.object
    })    
    this.carroForm.setValue(this.carro);

  }

  private getForm(){
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
  atualizar() {
    this.carro = this.carroForm.value
    this.carroService.atualizar(this.carro).subscribe(
      resposta =>{
        if(resposta.sucesso){
          this.carroService.mostrarMensagem(resposta.messagem);

        } else {
          this.carroService.mostrarMensagem(resposta.messagem);
        }
    })
    this.cancelar();
  }

  cancelar(){
    this.dialogRef.closeAll();    
  } 
  comparador(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}
