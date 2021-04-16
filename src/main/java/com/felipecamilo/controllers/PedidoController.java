package com.felipecamilo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipecamilo.dtos.PedidoDTO;
import com.felipecamilo.services.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(value = "API REST Pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de pedidos")
	public ResponseEntity<List<PedidoDTO>> findAll() {
		return new ResponseEntity<List<PedidoDTO>>(pedidoService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	@ApiOperation(value = "Salva um pedido")
	public ResponseEntity<PedidoDTO> save(@RequestBody PedidoDTO pedidoDTO) {
		pedidoService.save(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update")
	@ApiOperation(value = "Atualiza um pedido")
	public ResponseEntity<PedidoDTO> update(@RequestBody PedidoDTO pedidoDTO) {
		pedidoService.update(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um pedido")
	public ResponseEntity<PedidoDTO> delete(@PathVariable(value = "id") Long idPedido) {
		pedidoService.delete(idPedido);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
