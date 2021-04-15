package com.felipecamilo.dtos;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;

@ApiModel
public class IngredienteDTO {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	
	private BigDecimal quantidade;
	
	public IngredienteDTO() {
	}

	public IngredienteDTO(String nome, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public IngredienteDTO(Long id, String nome, BigDecimal preco) {
		this(nome, preco);
		this.id = id;
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

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
