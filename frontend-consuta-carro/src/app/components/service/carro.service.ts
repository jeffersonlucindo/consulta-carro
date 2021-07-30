import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Resposta } from '../model/resposta.model';
import { Carro } from '../model/carro.model';

@Injectable({
    providedIn: 'root'
})
export class CarroService {
    
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

    criarCarro(carro: Carro){
        return this.http.post<Resposta>(this.baseUrl + '/carros/adicionar',carro);
    }

    buscar(){
        return this.http.get<Resposta>(this.baseUrl + '/carros/listar');
    }

    deletar(carro: Carro){
        return this.http.post<Resposta>(this.baseUrl + '/carros/deletar/' + carro.idCarro,null);
    }

    buscarPorId(id: string){
        return this.http.get<Resposta>(this.baseUrl + '/carros/'+ id);
    }

    atualizar(carro: Carro){
        return this.http.post<Resposta>(this.baseUrl + '/carros/atualizar',carro);
    }
}