package com.felipecamilo.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class IngredienteDTO {
	
	@ApiModelProperty(name = "id", example = "")
	private Long id;
	private String nome;
	private BigDecimal preco;
	
	@JsonIgnore
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
