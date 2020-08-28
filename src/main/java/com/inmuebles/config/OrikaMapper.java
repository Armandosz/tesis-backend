package com.inmuebles.config;

import org.springframework.context.annotation.Configuration;

import com.inmuebles.domain.InmuebleDomain;
import com.inmuebles.model.InmuebleModel;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Configuration
public class OrikaMapper extends ConfigurableMapper {

	public MapperFactory mapper(MapperFactory factory) {
		factory.classMap(InmuebleModel.class, InmuebleDomain.class).byDefault().register();
		return factory;
	}

}