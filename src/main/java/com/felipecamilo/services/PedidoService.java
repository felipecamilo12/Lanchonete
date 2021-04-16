package com.felipecamilo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipecamilo.dtos.LancheDTO;
import com.felipecamilo.dtos.PedidoDTO;
import com.felipecamilo.entities.Lanche;
import com.felipecamilo.entities.Pedido;
import com.felipecamilo.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private LancheService lancheService;

	public List<PedidoDTO> findAll() {
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepository.findAll();
		for (Pedido pedido : pedidos) {
			Function<Lanche, LancheDTO> lancheToLancheDTO = (lanche) -> new LancheDTO(lanche.getId(), lanche.getNome(),
					lanche.getPreco());
			List<LancheDTO> lanchesDTO = pedido.getLanches().stream().map(lancheToLancheDTO)
					.collect(Collectors.toList());
			PedidoDTO pedidoDTO = new PedidoDTO(pedido.getId(), pedido.getPreco(), lanchesDTO);
			pedidosDTO.add(pedidoDTO);
		}
		return pedidosDTO;
	}

	public Optional<Pedido> findById(Long id) {
		return pedidoRepository.findById(id);
	}

	public void save(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setPreco(new BigDecimal("0.0"));
		for (LancheDTO lancheDTO : pedidoDTO.getLanches()) {
			Optional<Lanche> lanche = lancheService.findById(lancheDTO.getId());
			if (lanche.isPresent()) {
				pedido.addLanche(lanche.get());
				pedido.setPreco(pedido.getPreco().add(lanche.get().getPreco()));
			}
		}
		pedidoRepository.save(pedido);
	}

	public void update(PedidoDTO pedidoDTO) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoDTO.getId());
		if (pedidoOptional.isPresent()) {
			Pedido pedido = pedidoOptional.get();
			pedido.setPreco(new BigDecimal("0.0"));
			pedido.getLanches().clear();
			for (LancheDTO lancheDTO : pedidoDTO.getLanches()) {
				Optional<Lanche> lanche = lancheService.findById(lancheDTO.getId());
				if (lanche.isPresent()) {
					pedido.addLanche(lanche.get());
					pedido.setPreco(pedido.getPreco().add(lanche.get().getPreco()));
				}
			}
			pedidoRepository.save(pedido);
		}
	}

	public void delete(Long id) {
		pedidoRepository.deleteById(id);
	}

}
