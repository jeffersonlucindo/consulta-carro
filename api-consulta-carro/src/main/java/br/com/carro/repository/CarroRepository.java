package br.com.carro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carro.model.Carro;


@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{
	
	public Carro findCarroByNome(String nome);
	
	public List<Carro> findCarrosByOrigem(String origem);
}
