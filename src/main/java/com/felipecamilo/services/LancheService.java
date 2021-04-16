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
import com.felipecamilo.entities.LancheIngrediente;
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
			Function<LancheIngrediente, IngredienteDTO> ingredienteToIngredienteDTO = (
					lancheIngrediente) -> new IngredienteDTO(lancheIngrediente.getIngrediente().getId(),
							lancheIngrediente.getIngrediente().getNome(),
							lancheIngrediente.getIngrediente().getPreco());
			List<IngredienteDTO> ingredientesDTO = lanche.getLancheIngredientes().stream()
					.map(ingredienteToIngredienteDTO).collect(Collectors.toList());
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
		adicionarIngrediente(lancheDTO, lanche);
		calcularPreco(lancheDTO, lanche);
		aplicaPromocao(lancheDTO, lanche);
		lancheRepository.save(lanche);
	}

	public void adicionarIngrediente(LancheDTO lancheDTO, Lanche lanche) {
		for (IngredienteDTO ingredienteDTO : lancheDTO.getIngredientesDTO()) {
			Optional<Ingrediente> ingredienteOptional = ingredienteService.findByid(ingredienteDTO.getId());
			if (ingredienteOptional.isPresent()) {
				lanche.addIngrediente(ingredienteOptional.get(), ingredienteDTO.getQuantidade());
			}
		}
	}

	public void calcularPreco(LancheDTO lancheDTO, Lanche lanche) {
		for (IngredienteDTO ingredienteDTO : lancheDTO.getIngredientesDTO()) {
			if (!ingredienteDTO.getNome().equals("Hamburger") && !ingredienteDTO.getNome().equals("Queijo")) {
				Optional<Ingrediente> ingredienteOptional = ingredienteService.findByid(ingredienteDTO.getId());
				if (ingredienteOptional.isPresent()) {
					lanche.setPreco(lanche.getPreco()
							.add(ingredienteOptional.get().getPreco().multiply(ingredienteDTO.getQuantidade())));
				}
			}
		}
	}

	private void aplicaPromocao(LancheDTO lancheDTO, Lanche lanche) {
		lanche.setPreco(lanche.getPreco().add(calcularMuitaCarne(lancheDTO.getIngredientesDTO())));
		lanche.setPreco(lanche.getPreco().add(calcularMuitoQueijo(lancheDTO.getIngredientesDTO())));
		
		IngredienteDTO alfaceIngrediente = lancheDTO.getIngredientesDTO().stream()
				.filter(ingrediente -> "Alface".equals(ingrediente.getNome()))
				.findFirst()
				.orElse(new IngredienteDTO());
		IngredienteDTO baconIngrediente = lancheDTO.getIngredientesDTO().stream()
				.filter(ingrediente -> "Bacon".equals(ingrediente.getNome()))
				.findFirst()
				.orElse(new IngredienteDTO());
		BigDecimal promocao = Promocao.calculaLight(alfaceIngrediente.getQuantidade(), baconIngrediente.getQuantidade());
		lanche.setPreco(lanche.getPreco().subtract(lanche.getPreco().multiply(promocao)));
	}

	private BigDecimal calcularMuitaCarne(List<IngredienteDTO> ingredientesDTO) {
		Optional<IngredienteDTO> hamburgerIngrediente = ingredientesDTO.stream()
				.filter(ingrediente -> "Hamburger".equals(ingrediente.getNome())).findFirst();
		if (hamburgerIngrediente.isPresent()) {
			BigDecimal quantidadePagar = Promocao.calcularQuantidadePagar(hamburgerIngrediente.get().getQuantidade());
			Optional<Ingrediente> hamburger = ingredienteService.findByid(hamburgerIngrediente.get().getId());
			return hamburger.get().getPreco().multiply(quantidadePagar);
		}
		return BigDecimal.ZERO;
	}

	private BigDecimal calcularMuitoQueijo(List<IngredienteDTO> ingredientesDTO) {
		Optional<IngredienteDTO> queijoIngrediente = ingredientesDTO.stream()
				.filter(ingrediente -> "Queijo".equals(ingrediente.getNome())).findFirst();
		if (queijoIngrediente.isPresent()) {
			BigDecimal quantidadePagar = Promocao.calcularQuantidadePagar(queijoIngrediente.get().getQuantidade());
			Optional<Ingrediente> queijo = ingredienteService.findByid(queijoIngrediente.get().getId());
			return queijo.get().getPreco().multiply(quantidadePagar);
		}
		return BigDecimal.ZERO;
	}

	public void update(LancheDTO lancheDTO) {
		Optional<Lanche> lancheOptional = findById(lancheDTO.getId());
		if (lancheOptional.isPresent()) {
			Lanche lanche = lancheOptional.get();
			lanche.setNome(lancheDTO.getNome());
			lanche.setPreco(new BigDecimal("0.0"));
			adicionarIngrediente(lancheDTO, lanche);
			calcularPreco(lancheDTO, lanche);
			aplicaPromocao(lancheDTO, lanche);
			lancheRepository.save(lanche);
		}
	}

	public void delete(Long id) {
		lancheRepository.deleteById(id);
	}

}