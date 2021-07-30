package br.com.carro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carro.model.Carro;
import br.com.carro.model.Marca;
import br.com.carro.model.dto.MarcaDTO;
import br.com.carro.model.dto.RespostaDTO;
import br.com.carro.service.MarcaService;

@RestController
@RequestMapping("marcas")	
public class MarcaController {
	
	@Autowired
	private MarcaService service;
	
	@Autowired
	private MessageSource message;
	
	@Autowired
	RespostaDTO respostaDTO;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		ResponseEntity<?> responseEntity = null;
		List<Marca> marca = service.listar();
		
		if(!marca.isEmpty()) {
			respostaDTO.setSucesso(true);
			respostaDTO.setObject(marca);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		} else {
			respostaDTO.setMessagem(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
			respostaDTO.setSucesso(true);
			respostaDTO.setObject(marca);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);			
		}
		
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/marcas/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorId(@PathVariable("nome") String nome){
		ResponseEntity<?> responseEntity = null;
		try {
			Marca marca = service.buscarPorNome(nome);
			if(marca != null) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(marca);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
				respostaDTO.setSucesso(false);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			}
		} catch (Exception e) {
			respostaDTO.setMessagem(e.getMessage());
			respostaDTO.setSucesso(false);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		}
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome){
		ResponseEntity<?> responseEntity = null;
		try {
			Marca marca = service.buscarPorNome(nome);
			if(marca != null) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(marca);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
				respostaDTO.setSucesso(false);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			}
		} catch (Exception e) {
			respostaDTO.setMessagem(e.getMessage());
			respostaDTO.setSucesso(false);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		}
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{nomeOrigem}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorOrigem(@PathVariable("nomeOrigem") String nomeOrigem){
		ResponseEntity<?> responseEntity = null;
		try {
			List<Marca> marcaList = service.buscarPorOrigem(nomeOrigem);
			if(!marcaList.isEmpty()) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(marcaList);
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
				respostaDTO.setSucesso(false);
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			}
			
		} catch (Exception e) {
			respostaDTO.setMessagem(e.getMessage());
			respostaDTO.setSucesso(false);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		}
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ResponseEntity<?> adicionar(@RequestBody MarcaDTO marcaDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			Marca marca = service.adicionar(marcaDTO);
			if(marca != null) {
				respostaDTO.setMessagem(this.message.getMessage("marca.criado.sucesso", null, null));
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(marca);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
			}else {
				respostaDTO.setMessagem(this.message.getMessage("marca.existente", null, null));
				respostaDTO.setSucesso(false);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			}
		} catch (Exception e) {
			respostaDTO.setMessagem(e.getMessage());
			respostaDTO.setSucesso(false);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		}
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
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
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody MarcaDTO marcaDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			if(service.atualizar(marcaDTO)) {
				respostaDTO.setMessagem(this.message.getMessage("marca.atualiza.sucesso", null, null));
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(marcaDTO);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("marca.consulta.nao.encontrado", null, null));
				respostaDTO.setSucesso(false);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			}
			
		} catch (Exception e) {
			respostaDTO.setMessagem(e.getLocalizedMessage());
			respostaDTO.setSucesso(false);
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
		return responseEntity;
	}
}
