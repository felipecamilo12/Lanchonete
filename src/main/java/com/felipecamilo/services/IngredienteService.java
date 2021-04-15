package com.felipecamilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecamilo.dtos.IngredienteDTO;
import com.felipecamilo.entities.Ingrediente;
import com.felipecamilo.repositories.IngredienteRepository;

@Service
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<IngredienteDTO> findAll() {
		List<IngredienteDTO> ingredientesDTO = new ArrayList<>();
		List<Ingrediente> ingredientes = ingredienteRepository.findAll();
		for (Ingrediente ingrediente : ingredientes) {
			IngredienteDTO ingredienteDTO = new IngredienteDTO(ingrediente.getId(), ingrediente.getNome(),
					ingrediente.getPreco());
			ingredientesDTO.add(ingredienteDTO);
		}
		return ingredientesDTO;
	}

	public Optional<Ingrediente> findByid(Long id) {
		return ingredienteRepository.findById(id);
	}
	
	public void save(IngredienteDTO ingredienteDTO) {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setNome(ingredienteDTO.getNome());
		ingrediente.setPreco(ingredienteDTO.getPreco());
		ingredienteRepository.save(ingrediente);
	}

	public void update(IngredienteDTO ingredienteDTO) {
		Optional<Ingrediente> ingredienteOptional = findByid(ingredienteDTO.getId());
		if (ingredienteOptional.isPresent()) {
			Ingrediente ingrediente = ingredienteOptional.get();
			ingrediente.setPreco(ingredienteDTO.getPreco());
			ingredienteRepository.save(ingrediente);
		}
	}

	public void delete(Long id) {
		ingredienteRepository.deleteById(id);
	}

}
