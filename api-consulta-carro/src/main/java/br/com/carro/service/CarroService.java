package br.com.carro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carro.model.Carro;
import br.com.carro.model.Marca;
import br.com.carro.model.dto.CarroDTO;
import br.com.carro.repository.CarroRepository;

@Service
public class CarroService {

	
	@Autowired
	private CarroRepository respository;
	
	@Autowired
	private MarcaService marcaService;
	
	public List<Carro> listar(){
		return respository.findAll();
	}
	
	public Carro buscarPorNome(String nome){
		return respository.findCarroByNome(nome);
	}
	
	public List<Carro> buscarPorOrigem(String nome){
		return respository.findCarrosByOrigem(nome);
	}
	
	public Carro adicionar(CarroDTO carroDTO) {
		Marca marca = marcaService.buscarPorNome(carroDTO.getMarcaDTO().getNome());
		if(marca == null) {
			marca = marcaService.adicionar(carroDTO.getMarcaDTO());
		}
		if(!isExisteCarro(carroDTO)) {
			Carro carro = new Carro();
			carro.setMarca(marca);
			BeanUtils.copyProperties(carroDTO, carro);
			return respository.save(carro);
		} else {
			return null;
		}
	}
	
	public void deletar(Long id) {
		 respository.deleteById(id);
	}

	public Boolean isExisteCarro(CarroDTO carroDTO) {
		Carro carro = respository.findCarroByNome(carroDTO.getNome());
		
		return carro != null ? Boolean.TRUE : Boolean.FALSE ;
	}

	public boolean atualizar(CarroDTO carroDTO) {
		
		Optional<Carro> retorno = respository.findById(carroDTO.getIdCarro());
		if(retorno.isPresent()) {
			Carro carro = retorno.get();
			if(carroDTO.getMarcaDTO() != null) {
				Marca marca = marcaService.buscarPorNome(carroDTO.getMarcaDTO().getNome());
				carro.setMarca(marca);
			}
			carro.setNome(carroDTO.getNome());
			carro.setOrigem(carroDTO.getOrigem());
			carro.setAceleracao(carroDTO.getAceleracao());
			carro.setAno(carroDTO.getAno());
			carro.setCavalorDeForca(carroDTO.getCavalorDeForca());
			carro.setCilindros(carroDTO.getCilindros());
			carro.setKmPorGalao(carroDTO.getKmPorGalao());
			carro.setPeso(carroDTO.getPeso());
			respository.save(carro);
			return true;
		} else {
			return false;
		}
	}
}
