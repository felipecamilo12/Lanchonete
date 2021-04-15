package com.felipecamilo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecamilo.dtos.IngredienteDTO;
import com.felipecamilo.dtos.LancheDTO;
import com.felipecamilo.entities.Ingrediente;
import com.felipecamilo.entities.Lanche;
import com.felipecamilo.repositories.LancheRepository;
import com.felipecamilo.util.Promocao;

@Service
public class LancheService {

	@Autowired
	private LancheRepository lancheRepository;

	@Autowired
	private IngredienteService ingredienteService;

	public List<LancheDTO> findAll() {
		List<LancheDTO> lanchesDTO = new ArrayList<>();
		List<Lanche> lanches = lancheRepository.findAll();
		for (Lanche lanche : lanches) {
			Function<Ingrediente, IngredienteDTO> ingredienteToIngredienteDTO = (ingrediente) -> new IngredienteDTO(
					ingrediente.getId(), ingrediente.getNome(), ingrediente.getPreco());
			List<IngredienteDTO> ingredientesDTO = lanche.getIngredientes().stream().map(ingredienteToIngredienteDTO)
					.collect(Collectors.toList());
			LancheDTO lancheDTO = new LancheDTO(lanche.getId(), lanche.getNome(), lanche.getPreco(), ingredientesDTO);
			lanchesDTO.add(lancheDTO);
		}
		return lanchesDTO;
	}

	public Optional<Lanche> findById(Long id) {
		return lancheRepository.findById(id);
	}

	public void save(LancheDTO lancheDTO) {
		Lanche lanche = new Lanche();
		lanche.setNome(lancheDTO.getNome());
		lanche.setPreco(new BigDecimal("0.0"));
		int alface = 0;
		int bacon = 0;
		BigDecimal promocao = new BigDecimal("0.0");
		for (IngredienteDTO ingredienteDTO : lancheDTO.getIngredientesDTO()) {
			BigDecimal quantidade = new BigDecimal("1");
			Optional<Ingrediente> ingredienteOptional = ingredienteService.findByid(ingredienteDTO.getId());
			if (ingredienteDTO.getQuantidade() != null && (ingredienteOptional.get().getNome().equals("Hamburger")
					|| ingredienteOptional.get().getNome().equals("Queijo"))) {
				quantidade = Promocao.calcularQuantidade(ingredienteDTO.getQuantidade());
			}
			if (ingredienteOptional.get().getNome().equals("Alface")) {
				alface++;
			} else if (ingredienteOptional.get().getNome().equals("Bacon")) {
				bacon++;
			}
			if (ingredienteOptional.isPresent()) {
				lanche.setPreco(lanche.getPreco().add(ingredienteOptional.get().getPreco().multiply(quantidade)));
				lanche.addIngrediente(ingredienteOptional.get());
			}
		}
		promocao = Promocao.calculaLight(alface, bacon);
		lanche.setPreco(lanche.getPreco().subtract(lanche.getPreco().multiply(promocao)));
		lancheRepository.save(lanche);
	}

	public void update(LancheDTO lancheDTO) {
		Optional<Lanche> lancheOptional = findById(lancheDTO.getId());
		if (lancheOptional.isPresent()) {
			Lanche lanche = lancheOptional.get();
			lanche.setNome(lancheDTO.getNome());
			lanche.setPreco(new BigDecimal("0.0"));
			lanche.setIngredientes(new ArrayList<>());
			for (IngredienteDTO ingredienteDTO : lancheDTO.getIngredientesDTO()) {
				Optional<Ingrediente> ingredienteOptional = ingredienteService.findByid(ingredienteDTO.getId());
				if (ingredienteOptional.isPresent()) {
					lanche.setPreco(lanche.getPreco().add(ingredienteOptional.get().getPreco()));
					lanche.addIngrediente(ingredienteOptional.get());
				}
			}
			lancheRepository.save(lanche);
		}
	}

	public void delete(Long id) {
		lancheRepository.deleteById(id);
	}

}