package com.felipecamilo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal preco;

	
	@ManyToMany
	@JoinTable(name = "pedido_lanche", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "lanche_id"))
	private List<Lanche> lanches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Lanche> getLanches() {
		return lanches;
	}

	public void setLanches(List<Lanche> lanches) {
		this.lanches = lanches;
	}
	
	public void addLanche(Lanche lanche) {
		if(lanches == null) {
			lanches = new ArrayList<>();
		}
		lanches.add(lanche);
	}

}
