package br.com.carro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carro.model.Carro;
import br.com.carro.model.dto.CarroDTO;
import br.com.carro.service.CarroService;

@RestController
@RequestMapping("carros")
public class CarroController {
	
	@Autowired
	private CarroService service;
	
	@Autowired
	MessageSource message;
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Carro> listar(){
		return service.listar();
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome){
		ResponseEntity<?> responseEntity = null;
		try {
			Carro carro = service.buscarPorNome(nome);
			if(carro != null) {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(carro);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
			}
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/origem/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorOrigem(@PathVariable("nome") String nomeOrigem){
		ResponseEntity<?> responseEntity = null;
		try {
			List<Carro> carroList = service.buscarPorOrigem(nomeOrigem);
			if(carroList != null && !carroList.isEmpty()) {
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
			} else {
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.existente", null, null));
			}
			
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody CarroDTO carroDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			Carro carro = service.adicionar(carroDTO);
			if(carro != null) {
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(carro);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.existente", null, null));
			}
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		ResponseEntity<?> responseEntity = null;
		try {
			service.deletar(id);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.deleta.sucesso", null, null));
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody CarroDTO carroDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			if(service.atualizar(carroDTO)) {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.atualiza.sucesso", null, null));
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
}
