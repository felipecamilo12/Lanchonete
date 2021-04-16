package com.felipecamilo.dtos;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.annotations.ApiModel;

@ApiModel
public class PedidoDTO {

	private Long id;
	private BigDecimal preco;
	private List<LancheDTO> lanches;
	
	public PedidoDTO() {
	}

	public PedidoDTO(BigDecimal preco, List<LancheDTO> lanches) {
		this.preco = preco;
		this.lanches = lanches;
	}
	
	public PedidoDTO(Long id, BigDecimal preco, List<LancheDTO> lanches) {
		this(preco, lanches);
		this.id = id;
	}

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

	public List<LancheDTO> getLanches() {
		return lanches;
	}

	public void setLanches(List<LancheDTO> lanches) {
		this.lanches = lanches;
	}

}
