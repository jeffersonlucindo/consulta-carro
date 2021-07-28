package br.com.carro.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carro.model.Marca;
import br.com.carro.model.dto.MarcaDTO;
import br.com.carro.repository.MarcaRepository;

@Service
public class MarcaService {
	
	private static Logger logger = LoggerFactory.getLogger(MarcaService.class);
	
	@Autowired
	private MarcaRepository respository;
	
	public List<Marca> listar(){
		logger.info(String.format("[MarcaService] Consultando lista de marca"));
		return respository.findAll();
	}
	
	public Marca buscarPorNome(String nome){
		logger.info(String.format("[MarcaService] Consultando marca de nome ", nome));
		return respository.findMarcaByNome(nome);
	}
	
	public List<Marca> buscarPorOrigem( String nome){
		logger.info(String.format("[MarcaService] Consultando marca de origem ",nome));
		return respository.findMarcaByOrigem(nome);
	}
	
	public Marca adicionar(MarcaDTO marcaDTO) {
		logger.info(String.format("[MarcaService] Adicionando Marca"));
		if(!isExisteMarca(marcaDTO)) {
			Marca marca= new Marca();
			BeanUtils.copyProperties(marcaDTO, marca);
			return respository.save(marca);
		} else {
			return null;
		}
	}
	
	public void deletar(Long id) {
		logger.info(String.format("[MarcaService] deletando Marca"));
		respository.deleteById(id);
	}
	
	public Boolean atualizar(MarcaDTO marcaDTO) {
		logger.info(String.format("[MarcaService] Atualizando a Marca ", marcaDTO.getNome()));
		
		Optional<Marca> retorno = respository.findById(marcaDTO.getIdMarca());
		
		if(retorno.isPresent()) {
			Marca marca = retorno.get();
			marca.setNome(marcaDTO.getNome());
			marca.setOrigem(marcaDTO.getOrigem());
			respository.save(marca);
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean isExisteMarca(MarcaDTO marcaDTO) {
		Marca marcar = respository.findMarcaByNome(marcaDTO.getNome());
		
		return marcar != null ? Boolean.TRUE : Boolean.FALSE ;
	}
}
