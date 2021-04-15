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

import com.felipecamilo.dtos.LancheDTO;
import com.felipecamilo.services.LancheService;

@RestController
@RequestMapping("/lanches")
public class LancheController {

	@Autowired
	private LancheService lancheService;

	@GetMapping
	public ResponseEntity<List<LancheDTO>> findAll() {
		return new ResponseEntity<List<LancheDTO>>(lancheService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<LancheDTO> save(@RequestBody LancheDTO lancheDTO) {
		lancheService.save(lancheDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<LancheDTO> update(@RequestBody LancheDTO lancheDTO){
		lancheService.update(lancheDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<LancheDTO> delete(@RequestBody LancheDTO lancheDTO){
		lancheService.delete(lancheDTO.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
