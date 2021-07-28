package br.com.carro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carro.model.Marca;
import br.com.carro.model.dto.MarcaDTO;
import br.com.carro.service.MarcaService;

@RestController
@RequestMapping("marcas")	
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Marca> listar(){
		return service.listar();
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome){
		ResponseEntity<?> responseEntity = null;
		try {
			Marca marca = service.buscarPorNome(nome);
			if(marca != null) {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(marca);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body("Marca não encontrada");
			}
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/origem/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorOrigem(@PathVariable("nome") String nomeOrigem){
		
		try {
			List<Marca> marcaList = service.buscarPorOrigem(nomeOrigem);
			if(!marcaList.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(marcaList);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Marca não encontrada");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody MarcaDTO marcaDTO) {
		
		try {
			Marca marca = service.adicionar(marcaDTO);
			if(marca != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(marca);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Marca não encontrada");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deletar/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		
		try {
			service.deletar(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody MarcaDTO marcaDTO) {
		
		try {
			if(service.atualizar(marcaDTO)) {
				return ResponseEntity.status(HttpStatus.OK).body("Marca Atualizada");
			} else {
				return ResponseEntity.status(HttpStatus.OK).body("Marca não encontrada");
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
}
