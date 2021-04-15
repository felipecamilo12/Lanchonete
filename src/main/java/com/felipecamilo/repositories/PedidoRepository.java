package com.felipecamilo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felipecamilo.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
