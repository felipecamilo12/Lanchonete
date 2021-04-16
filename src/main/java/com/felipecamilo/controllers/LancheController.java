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

import com.felipecamilo.dtos.LancheDTO;
import com.felipecamilo.services.LancheService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lanches")
@Api(value = "API REST Lanche")
public class LancheController {

	@Autowired
	private LancheService lancheService;

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de lanches")
	public ResponseEntity<List<LancheDTO>> findAll() {
		return new ResponseEntity<List<LancheDTO>>(lancheService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	@ApiOperation(value = "Salva um lanche")
	public ResponseEntity<LancheDTO> save(@RequestBody LancheDTO lancheDTO) {
		lancheService.save(lancheDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "Atualiza um lanche")
	public ResponseEntity<LancheDTO> update(@RequestBody LancheDTO lancheDTO){
		lancheService.update(lancheDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um lanche")
	public ResponseEntity<LancheDTO> delete(@PathVariable(value = "id") Long idLanche){
		lancheService.delete(idLanche);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
