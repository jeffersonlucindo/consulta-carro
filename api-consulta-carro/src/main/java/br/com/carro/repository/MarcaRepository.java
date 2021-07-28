package br.com.carro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.carro.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

	public Marca findMarcaByNome(String nome);
	
	public List<Marca> findMarcaByOrigem(String origem);
}
