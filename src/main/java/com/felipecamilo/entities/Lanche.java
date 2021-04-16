package com.felipecamilo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Lanche")
public class Lanche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private BigDecimal preco;

	@OneToMany(mappedBy = "lanche", cascade = CascadeType.ALL)
	private List<LancheIngrediente> lancheIngredientes;
	
	@ManyToMany(mappedBy = "lanches")
	private List<Pedido> pedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LancheIngrediente> getLancheIngredientes() {
		return lancheIngredientes;
	}

	public void setLancheIngredientes(List<LancheIngrediente> ingredientes) {
		this.lancheIngredientes = ingredientes;
	}

	public void addIngrediente(Ingrediente ingrediente, BigDecimal quantidade) {
		if (lancheIngredientes == null) {
			lancheIngredientes = new ArrayList<>();
		}
		LancheIngrediente lancheIngrediente = new LancheIngrediente(this, ingrediente, quantidade);
		lancheIngrediente.setId(new LancheIngredienteChaveComposta(this.getId(),ingrediente.getId()));
		lancheIngredientes.add(lancheIngrediente);
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
