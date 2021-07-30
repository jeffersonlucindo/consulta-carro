import { MarcaDTO } from "../model/marcaDTO.model";


export interface Carro {
    
    idCarro?: number;
    nome: string;
    kmPorGalao: number;
    cilindros: string;
    cavalorDeForca: string;
    peso: number;
    aceleracao: string;
    ano: string;
    origem: string;
    marcaDTO: MarcaDTO;
}