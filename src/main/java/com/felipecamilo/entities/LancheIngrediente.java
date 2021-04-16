package com.felipecamilo.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class LancheIngrediente implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LancheIngredienteChaveComposta id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@MapsId("lancheID")
	@JoinColumn(name = "lanche_id")
	private Lanche lanche;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@MapsId("ingredienteID")
	@JoinColumn(name = "ingrediente_id")
	private Ingrediente ingrediente;
	
	private BigDecimal quantidade;

	public LancheIngrediente() {
	}

	public LancheIngrediente(Lanche lanche, Ingrediente ingrediente, BigDecimal quantidade) {
		this.lanche = lanche;
		this.ingrediente = ingrediente;
		this.quantidade = quantidade;
	}

	public LancheIngredienteChaveComposta getId() {
		return id;
	}

	public void setId(LancheIngredienteChaveComposta id) {
		this.id = id;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

}
