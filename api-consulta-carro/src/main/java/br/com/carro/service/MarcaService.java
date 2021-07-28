package br.com.carro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carro.model.Marca;
import br.com.carro.model.dto.MarcaDTO;
import br.com.carro.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository respository;
	
	public List<Marca> listar(){
		return respository.findAll();
	}
	
	public Marca buscarPorNome(String nome){
		return respository.findMarcaByNome(nome);
	}
	
	public List<Marca> buscarPorOrigem( String nome){
		return respository.findMarcaByOrigem(nome);
	}
	
	public Marca adicionar(MarcaDTO marcaDTO) {
		if(!isExisteMarca(marcaDTO)) {
			Marca marca= new Marca();
			BeanUtils.copyProperties(marcaDTO, marca);
			return respository.save(marca);
		} else {
			return null;
		}
	}
	
	public void deletar(Long id) {
		respository.deleteById(id);
	}
	
	public Boolean atualizar(MarcaDTO marcaDTO) {
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
