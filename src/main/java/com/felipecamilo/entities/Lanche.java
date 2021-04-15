package com.felipecamilo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lanche")
public class Lanche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Nome")
	private String nome;

	@Column(name = "Preco")
	private BigDecimal preco;

	@ManyToMany
	@JoinTable(name = "lanche_ingrediente", joinColumns = @JoinColumn(name = "lanche_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
	private List<Ingrediente> ingredientes;

	@ManyToMany(mappedBy = "lanches")
	private List<Pedido> pedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public void addIngrediente(Ingrediente ingrediente) {
		if (ingredientes == null) {
			ingredientes = new ArrayList<>();
		}
		ingredientes.add(ingrediente);
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

}
