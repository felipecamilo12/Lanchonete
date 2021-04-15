package com.felipecamilo.dtos;

import java.math.BigDecimal;
import java.util.List;

public class LancheDTO {

	private Long id;
	private String nome;
	private BigDecimal preco;
	
	private List<IngredienteDTO> ingredientes;

	public LancheDTO() {
	}
	
	public LancheDTO(Long id, String nome, BigDecimal preco, List<IngredienteDTO> ingredientes) {
		this(nome, preco, ingredientes);
		this.id = id;
	}

	public LancheDTO(Long id, String nome, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public LancheDTO(String nome, BigDecimal preco, List<IngredienteDTO> ingredientes) {
		this.nome = nome;
		this.preco = preco;
		this.ingredientes = ingredientes;
	}

	public LancheDTO(Long id, String nome, List<IngredienteDTO> ingredientes) {
		this.id = id;
		this.nome = nome;
		this.ingredientes = ingredientes;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<IngredienteDTO> getIngredientesDTO() {
		return ingredientes;
	}

	public void setIngredientesDTO(List<IngredienteDTO> ingredientesDTO) {
		this.ingredientes = ingredientesDTO;
	}

}
