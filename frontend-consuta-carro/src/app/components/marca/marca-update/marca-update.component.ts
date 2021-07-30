import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MarcaDTO } from '../../model/marcaDTO.model';
import { MarcaService } from '../../service/marca.service';

@Component({
  selector: 'app-marca-update',
  templateUrl: './marca-update.component.html',
  styleUrls: ['./marca-update.component.css']
})
export class MarcaUpdateComponent implements OnInit {

  @Input() marca: MarcaDTO;
  
  marcaForm: FormGroup;

  constructor(private marcaService: MarcaService, 
    private formBuilder: FormBuilder,
    private dialogRef: MatDialog) { }

  ngOnInit(): void {
    this.getForm();
   this.marcaForm.setValue(this.marca);
    
  }

  private getForm(){
    this.marcaForm = this.formBuilder.group({
      idMarca: [],
      nome: [null, [Validators.required]],      
      origem: [null, [Validators.required]]
    })
  }

  atualizar() {
    this.marca = this.marcaForm.value
    this.marcaService.atualizar(this.marca).subscribe(
      resposta =>{
        if(resposta.sucesso){
          this.marcaService.mostrarMensagem(resposta.messagem);

        } else {
          this.marcaService.mostrarMensagem(resposta.messagem);
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
