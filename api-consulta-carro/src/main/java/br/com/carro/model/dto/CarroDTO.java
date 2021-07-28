package br.com.carro.model.dto;

import lombok.Data;

@Data
public class CarroDTO {
	
	private Long idCarro;
	private String nome;
	private Long kmPorGalao;
	private String cilindros;
	private String cavalorDeForca;
	private Long peso;
	private String aceleracao;
	private String ano;
	private String origem;
	
    private MarcaDTO marcaDTO;

}
