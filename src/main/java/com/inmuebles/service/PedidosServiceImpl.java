package com.inmuebles.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmuebles.domain.PedidoDomain;
import com.inmuebles.domain.UsuarioDomain;
import com.inmuebles.exception.EntityNotFoundException;
import com.inmuebles.model.PedidoModel;
import com.inmuebles.model.UsuarioModel;
import com.inmuebles.repository.PedidosRepository;
import com.inmuebles.repository.UsuariosRepository;

import io.micrometer.core.instrument.util.StringUtils;
import ma.glasnost.orika.MapperFacade;

@Service
public class PedidosServiceImpl implements PedidosService {

	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Autowired
	private MapperFacade mapperFacade;

	PedidoModel pedidoModel = new PedidoModel();
	List<PedidoModel> pedidosModel = new ArrayList<PedidoModel>();
	PedidoDomain pedidoDomain = new PedidoDomain();
	List<PedidoDomain> pedidosDomain = new ArrayList<>();
	
	UsuarioModel usuarioModel = new UsuarioModel();


	@Override
	public String createPedido(PedidoDomain pedidoDomain) throws Exception {
		//if (jsonValidation(pedidoDomain.getNombre(), pedidoDomain.getCelular(), pedidoDomain.getDireccion(),
		//		pedidoDomain.getPedido())) {
			pedidoModel = mapperFacade.map(pedidoDomain, PedidoModel.class);
			System.out.println(pedidoModel);
			pedidosRepository.save(pedidoModel);
			String id = pedidoModel.get_id();
			System.out.println(id);
			return id;
		//} else {
		//	throw new EntityNotFoundException("Los campos no pueden ir nulos o vacios.", "", "/pedidos/");
		//}

	}

	@Override
	public PedidoDomain getPedidoById(String id) throws Exception {
		if (!pedidosRepository.existsById(id))
			throw new EntityNotFoundException("No se encontro", "/pedidos/" + id);
		pedidoModel = pedidosRepository.findById(id).get();
		pedidoDomain = mapperFacade.map(pedidoModel, PedidoDomain.class);
		System.out.println(pedidoDomain);
		return pedidoDomain;
	}

	@Override
	public List<PedidoDomain> getAllPedidos() throws Exception {
		List<PedidoModel> allPedidosModel = new ArrayList<>();
		List<PedidoDomain> allPedidosDomain = new ArrayList<>();
		allPedidosModel = pedidosRepository.findAll();

		for (int i = 0; i < allPedidosModel.size(); i++) {
			allPedidosDomain.add(mapperFacade.map(allPedidosModel.get(i), PedidoDomain.class));
		}
		return allPedidosDomain;
	}
	
	@Override
	public List<UsuarioDomain> getAllUsuarios() throws Exception {
		List<UsuarioModel> allUsuariosModel = new ArrayList<>();
		List<UsuarioDomain> allUsuariosDomain = new ArrayList<>();
		allUsuariosModel = usuariosRepository.findAll();

		for (int i = 0; i < allUsuariosModel.size(); i++) {
			allUsuariosDomain.add(mapperFacade.map(allUsuariosModel.get(i), UsuarioDomain.class));
		}
		return allUsuariosDomain;
	}

	@Override
	public void deletePedido(String id) throws Exception {
		if (!pedidosRepository.existsById(id)) {
			throw new EntityNotFoundException("El pedido no ha sido encontrado", "/pedidos/" + id);
		} else
			System.out.println("Deleting pedido with the id: " + id);
		pedidosRepository.deleteById(id);
	}

	@Override
	public PedidoDomain updatePedido(PedidoDomain pedidoDomain, String id) throws Exception {
		//if (jsonValidation(pedidoDomain.getNombre(), pedidoDomain.getCelular(), pedidoDomain.getDireccion(), pedidoDomain.getPedido())) {
			//if (pedidosRepository.existsById(id)) {
				pedidoModel = mapperFacade.map(pedidoDomain, PedidoModel.class);
				pedidoModel.set_id(id);
				pedidoDomain = mapperFacade.map(pedidoModel, PedidoDomain.class);
				System.out.println("Updating pedido with the id: " + id);
				pedidosRepository.save(pedidoModel);
				// return storyModel;
				return pedidoDomain;
			//} else {
			//	throw new EntityNotFoundException("No se encontro el pedido con el Id: "+id,
			//			"/pedido/" + id);
			//}
		//} else {
		//	throw new EntityNotFoundException("Los campos no pueden ir nulos o vacios.", "", "/pedidos/");
		//}
	}
	
	@Override
	public UsuarioDomain updateUsuario(UsuarioDomain usuarioDomain, String id) throws Exception {
				usuarioModel = mapperFacade.map(usuarioDomain, UsuarioModel.class);
				usuarioModel.set_id(id);
				usuarioDomain = mapperFacade.map(usuarioModel, UsuarioDomain.class);
				System.out.println("Updating usuario with the id: " + id);
				usuariosRepository.save(usuarioModel);
				// return storyModel;
				return usuarioDomain;
	}

	private boolean jsonValidation(String name, String cellphone, String ubication, String order) {
		if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(cellphone) && !StringUtils.isEmpty(ubication) && !StringUtils.isEmpty(order)) {
			return true;
		} else {
			return false;
		}
	}

}