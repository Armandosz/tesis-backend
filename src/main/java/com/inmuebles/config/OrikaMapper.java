package com.inmuebles.config;

import org.springframework.context.annotation.Configuration;

import com.inmuebles.domain.PedidoDomain;
import com.inmuebles.model.PedidoModel;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Configuration
public class OrikaMapper extends ConfigurableMapper {

	public MapperFactory mapper(MapperFactory factory) {
		factory.classMap(PedidoModel.class, PedidoDomain.class).byDefault().register();
		return factory;
	}

}