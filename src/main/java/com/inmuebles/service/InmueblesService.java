package com.inmuebles.service;

import java.util.List;

import com.inmuebles.domain.InmuebleDomain;
import com.inmuebles.domain.UsuarioDomain;

public interface InmueblesService {
	
	InmuebleDomain getInmuebleById(String id) throws Exception;

	List<InmuebleDomain> getAllInmuebles() throws Exception;

	public String createInmueble(InmuebleDomain request) throws Exception;

	void deleteInmueble(String id) throws Exception;
	
	InmuebleDomain updateInmueble(InmuebleDomain request, String id) throws Exception;

	List<UsuarioDomain> getAllUsuarios() throws Exception;
	
	UsuarioDomain getUsuarioById(String id) throws Exception;

	UsuarioDomain updateUsuario(UsuarioDomain request, String id) throws Exception;
}
