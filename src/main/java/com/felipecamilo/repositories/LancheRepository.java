package com.felipecamilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecamilo.entities.Lanche;

@Repository
public interface LancheRepository extends JpaRepository<Lanche, Long>{

}
