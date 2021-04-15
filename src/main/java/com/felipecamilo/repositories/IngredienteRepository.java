package com.felipecamilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecamilo.entities.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
	
}
