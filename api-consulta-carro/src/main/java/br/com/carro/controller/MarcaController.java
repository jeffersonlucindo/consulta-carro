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

import br.com.carro.model.Marca;
import br.com.carro.model.dto.MarcaDTO;
import br.com.carro.service.MarcaService;

@RestController
@RequestMapping("marcas")	
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	@Autowired
	private MessageSource message;
	
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
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
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
			List<Marca> marcaList = service.buscarPorOrigem(nomeOrigem);
			if(!marcaList.isEmpty()) {
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(marcaList);
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
			}
			
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody MarcaDTO marcaDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			Marca marca = service.adicionar(marcaDTO);
			if(marca != null) {
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(marca);
			}else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.existente", null, null));
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
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.deleta.sucess", null, null));
		} catch (Exception e) {
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody MarcaDTO marcaDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			if(service.atualizar(marcaDTO)) {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.atualiza.sucesso", null, null));
			} else {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
}
