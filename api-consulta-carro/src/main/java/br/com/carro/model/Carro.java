package br.com.carro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Carro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarro;
	
	private String nome;
	private Long kmPorGalao;
	private String cilindros;
	private String cavalorDeForca;
	private Long peso;
	private String aceleracao;
	private String ano;
	private String origem;
	
	@ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "idMarca", nullable = false)
    private Marca marca;
}
