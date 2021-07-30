package br.com.carro.model.dto;

import org.springframework.stereotype.Component;

import br.com.carro.model.Carro;
import lombok.Data;

@Data
@Component
public class RespostaDTO {
	
	private String messagem;
	private Object object;
	private Boolean sucesso;
}
