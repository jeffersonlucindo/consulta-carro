import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MarcaDTO } from '../model/marcaDTO.model';
import { Resposta } from '../model/resposta.model';


@Injectable({
    providedIn: 'root'
})
export class MarcaService {
    
    private baseUrl = 'http://localhost:8080/api-carros';

    constructor(private snackBar: MatSnackBar, 
        private http: HttpClient){}

    mostrarMensagem(msg: string): void {
        this.snackBar.open(msg, 'X',{
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top"
        })
    }

    criarMarca(marca: MarcaDTO){
        return this.http.post<Resposta>(this.baseUrl + '/marcas/adicionar',marca);
    }

    buscar(){
        return this.http.get<Resposta>(this.baseUrl + '/marcas/listar');
    }

    deletar(carro: MarcaDTO){
        return this.http.post<Resposta>(this.baseUrl + '/marcas/deletar/' + carro.idMarca,null);
    }

    buscarPorId(id: string){
        return this.http.get<Resposta>(this.baseUrl + '/marcas/'+ id);
    }

    atualizar(carro: MarcaDTO){
        return this.http.post<Resposta>(this.baseUrl + '/marcas/atualizar',carro);
    }
}