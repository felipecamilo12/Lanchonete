package com.felipecamilo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipecamilo.dtos.IngredienteDTO;
import com.felipecamilo.services.IngredienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ingredientes")
@Api(value = "API REST Ingrediente")
@CrossOrigin(origins="*")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista de ingredientes")
	public ResponseEntity<List<IngredienteDTO>> findAll(){
		return new ResponseEntity<> (ingredienteService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	@ApiOperation(value = "Salva um ingrediente")
	public ResponseEntity<IngredienteDTO> save(@RequestBody IngredienteDTO ingredienteDTO){
		ingredienteService.save(ingredienteDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update")
	@ApiOperation(value = "Atualiza um ingrediente")
	public ResponseEntity<IngredienteDTO> update(@RequestBody IngredienteDTO ingredienteDTO){
		ingredienteService.update(ingredienteDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um ingrediente")
	public ResponseEntity<IngredienteDTO> delete(@PathVariable(value = "id") Long idIngrediente){
		ingredienteService.delete(idIngrediente);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
