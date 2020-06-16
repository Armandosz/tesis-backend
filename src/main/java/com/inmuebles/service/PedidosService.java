package com.inmuebles.service;

import java.util.List;

import com.inmuebles.domain.PedidoDomain;

public interface PedidosService {
	
	PedidoDomain getPedidoById(String id) throws Exception;

	List<PedidoDomain> getAllPedidos() throws Exception;

	public String createPedido(PedidoDomain request) throws Exception;

	void deletePedido(String id) throws Exception;
	
	PedidoDomain updatePedido(PedidoDomain request, String id) throws Exception;
}
