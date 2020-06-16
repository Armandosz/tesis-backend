package com.inmuebles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.inmuebles.domain.PedidoDomain;
import com.inmuebles.domain.PedidoDomainId;
import com.inmuebles.service.PedidosServiceImpl;

@RestController
@RequestMapping(value = "/inventario")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@CrossOrigin(origins = "*", maxAge = 3600)
public class PedidosController {

	@Autowired
	PedidosServiceImpl pedidoService;
	
	//Get All items
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/", produces = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	public List<PedidoDomain> getAllPedidos() throws Exception {
		return pedidoService.getAllPedidos();
	}
	
	//Get pedido by Id
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseBody
	public PedidoDomain getPedidoById(@Valid @PathVariable String id) throws Exception {
		return pedidoService.getPedidoById(id);
	}

	//POST pedido
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> createPedido(@Valid @RequestBody PedidoDomain request) throws Exception {
		PedidoDomainId storyDomainId = new PedidoDomainId();
		storyDomainId.setId(pedidoService.createPedido(request));
		return new ResponseEntity<>(storyDomainId, HttpStatus.CREATED);
	}
	
	//Update Pedido
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public PedidoDomain updatePedido(@Valid @RequestBody PedidoDomain request, @PathVariable String id) throws Exception {
		return pedidoService.updatePedido(request, id);
	}

	//Delete pedido
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void deletePedido(@Valid @PathVariable String id) throws Exception {
		pedidoService.deletePedido(id);
	}
}