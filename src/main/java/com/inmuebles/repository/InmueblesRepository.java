package com.inmuebles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.inmuebles.model.InmuebleModel;

@Repository
public interface InmueblesRepository extends MongoRepository<InmuebleModel, String> {

}
