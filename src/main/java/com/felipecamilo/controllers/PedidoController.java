package com.felipecamilo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipecamilo.dtos.PedidoDTO;
import com.felipecamilo.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll() {
		return new ResponseEntity<List<PedidoDTO>>(pedidoService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<PedidoDTO> save(@RequestBody PedidoDTO pedidoDTO) {
		pedidoService.save(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<PedidoDTO> update(@RequestBody PedidoDTO pedidoDTO){
		pedidoService.update(pedidoDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<PedidoDTO> delete(@RequestBody PedidoDTO pedidoDTO){
		pedidoService.delete(pedidoDTO.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
