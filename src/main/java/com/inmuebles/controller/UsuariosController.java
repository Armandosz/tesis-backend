package com.inmuebles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inmuebles.domain.PedidoDomain;
import com.inmuebles.domain.UsuarioDomain;
import com.inmuebles.service.PedidosServiceImpl;

@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuariosController {
	
	@Autowired
	PedidosServiceImpl pedidoService;
	
	//Get All users
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseBody
	public List<UsuarioDomain> getAllUsuarios() throws Exception {
		return pedidoService.getAllUsuarios();
	}
	
	//Update User
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public UsuarioDomain updateUsuario(@Valid @RequestBody UsuarioDomain request, @PathVariable String id) throws Exception {
		return pedidoService.updateUsuario(request, id);
	}
	
}