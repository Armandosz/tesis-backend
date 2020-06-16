package com.inmuebles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inmuebles.model.PedidoModel;

@Repository
public interface PedidosRepository extends MongoRepository<PedidoModel, String> {

}
