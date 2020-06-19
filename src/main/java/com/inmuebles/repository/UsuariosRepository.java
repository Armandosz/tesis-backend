package com.inmuebles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.inmuebles.model.UsuarioModel;

public interface UsuariosRepository extends MongoRepository<UsuarioModel, String>{

}
