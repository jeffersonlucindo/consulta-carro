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
import br.com.carro.model.dto.CarroDTO;
import br.com.carro.model.dto.RespostaDTO;
import br.com.carro.service.CarroService;

@RestController
@RequestMapping("carros")
public class CarroController {
	
	@Autowired
	private CarroService service;
	
	@Autowired
	MessageSource message;
	
	@Autowired
	RespostaDTO respostaDTO;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		ResponseEntity<?> responseEntity = null;
		List<Carro> carros = service.listar();
		
		if(!carros.isEmpty()) {
			respostaDTO.setSucesso(true);
			respostaDTO.setObject(carros);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		} else {
			respostaDTO.setMessagem(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
			respostaDTO.setSucesso(true);
			respostaDTO.setObject(carros);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);			
		}
		
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorId(@PathVariable("id") String id){
		ResponseEntity<?> responseEntity = null;
		try {
			Carro carro = service.buscarPorId(id);
			if(carro != null) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(carro);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
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
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorNome(@PathVariable("nome") String nome){
		ResponseEntity<?> responseEntity = null;
		try {
			Carro carro = service.buscarPorNome(nome);
			if(carro != null) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(carro);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
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
	@RequestMapping(value = "/origem/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorOrigem(@PathVariable("nome") String nomeOrigem){
		ResponseEntity<?> responseEntity = null;
		try {
			List<Carro> carroList = service.buscarPorOrigem(nomeOrigem);
			if(carroList != null && !carroList.isEmpty()) {
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(carroList);
				responseEntity =  ResponseEntity.status(HttpStatus.OK).body(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
			} else {
				respostaDTO.setMessagem(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
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
	public ResponseEntity<?> adicionar(@RequestBody CarroDTO carroDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			Carro carro = service.adicionar(carroDTO);
			if(carro != null) {
				respostaDTO.setMessagem(this.message.getMessage("carro.criado.sucesso", null, null));
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(carro);
				responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("carro.existente", null, null));
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
			
			respostaDTO.setMessagem(this.message.getMessage("carro.deleta.sucesso", null, null));
			respostaDTO.setSucesso(true);
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		} catch (Exception e) {
			respostaDTO.setMessagem(this.message.getMessage("carro.deleta.erro", null, null));
			respostaDTO.setSucesso(false);
			respostaDTO.setMessagem(e.getMessage());
			responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
		}
		return responseEntity;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST)
	public ResponseEntity<?> atualizar(@RequestBody CarroDTO carroDTO) {
		ResponseEntity<?> responseEntity = null;
		try {
			if(service.atualizar(carroDTO)) {
				respostaDTO.setMessagem(this.message.getMessage("carro.atualiza.sucesso", null, null));
				respostaDTO.setSucesso(true);
				respostaDTO.setObject(carroDTO);
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(respostaDTO);
			} else {
				respostaDTO.setMessagem(this.message.getMessage("carro.consulta.nao.encontrado", null, null));
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
