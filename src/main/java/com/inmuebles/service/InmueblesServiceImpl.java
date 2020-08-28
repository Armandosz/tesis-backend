package com.inmuebles.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.inmuebles.domain.InmuebleDomain;
import com.inmuebles.domain.UsuarioDomain;
import com.inmuebles.exception.EntityNotFoundException;
import com.inmuebles.model.ERole;
import com.inmuebles.model.InmuebleModel;
import com.inmuebles.model.Role;
import com.inmuebles.model.UsuarioModel;
import com.inmuebles.repository.InmueblesRepository;
import com.inmuebles.repository.RoleRepository;
import com.inmuebles.repository.UsuariosRepository;

import io.micrometer.core.instrument.util.StringUtils;
import ma.glasnost.orika.MapperFacade;

@Service
public class InmueblesServiceImpl implements InmueblesService {

	@Autowired
	InmueblesRepository inmueblesRepository;
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private MapperFacade mapperFacade;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	InmuebleModel inmuebleModel = new InmuebleModel();
	List<InmuebleModel> inmueblesModel = new ArrayList<InmuebleModel>();
	InmuebleDomain inmuebleDomain = new InmuebleDomain();
	List<InmuebleDomain> inmueblesDomain = new ArrayList<>();
	
	UsuarioModel usuarioModel = new UsuarioModel();
	UsuarioDomain usuarioDomain = new UsuarioDomain();


	@Override
	public String createInmueble(InmuebleDomain inmuebleDomain) throws Exception {
		//if (jsonValidation(pedidoDomain.getNombre(), pedidoDomain.getCelular(), pedidoDomain.getDireccion(),
		//		pedidoDomain.getPedido())) {
			inmuebleModel = mapperFacade.map(inmuebleDomain, InmuebleModel.class);
			System.out.println(inmuebleModel);
			inmueblesRepository.save(inmuebleModel);
			String id = inmuebleModel.get_id();
			System.out.println(id);
			return id;
		//} else {
		//	throw new EntityNotFoundException("Los campos no pueden ir nulos o vacios.", "", "/pedidos/");
		//}

	}

	@Override
	public InmuebleDomain getInmuebleById(String id) throws Exception {
		if (!inmueblesRepository.existsById(id))
			throw new EntityNotFoundException("No se encontro", "/inmueble/" + id);
		inmuebleModel = inmueblesRepository.findById(id).get();
		inmuebleDomain = mapperFacade.map(inmuebleModel, InmuebleDomain.class);
		System.out.println(inmuebleDomain);
		return inmuebleDomain;
	}

	@Override
	public List<InmuebleDomain> getAllInmuebles() throws Exception {
		List<InmuebleModel> allInmueblesModel = new ArrayList<>();
		List<InmuebleDomain> allInmueblesDomain = new ArrayList<>();
		allInmueblesModel = inmueblesRepository.findAll();

		for (int i = 0; i < allInmueblesModel.size(); i++) {
			allInmueblesDomain.add(mapperFacade.map(allInmueblesModel.get(i), InmuebleDomain.class));
		}
		return allInmueblesDomain;
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
	public UsuarioDomain getUsuarioById(String id) throws Exception {
		if (!usuariosRepository.existsById(id))
			throw new EntityNotFoundException("No se encontro", "/usuario/" + id);
		usuarioModel = usuariosRepository.findById(id).get();
		usuarioDomain = mapperFacade.map(usuarioModel, UsuarioDomain.class);
		System.out.println(usuarioDomain);
		return usuarioDomain;
	}

	@Override
	public void deleteInmueble(String id) throws Exception {
		if (!inmueblesRepository.existsById(id)) {
			throw new EntityNotFoundException("El inmueble no ha sido encontrado", "/inmueble/" + id);
		} else
			System.out.println("Deleting inmueble with the id: " + id);
		inmueblesRepository.deleteById(id);
	}

	@Override
	public InmuebleDomain updateInmueble(InmuebleDomain inmuebleDomain, String id) throws Exception {
		//if (jsonValidation(pedidoDomain.getNombre(), pedidoDomain.getCelular(), pedidoDomain.getDireccion(), pedidoDomain.getPedido())) {
			//if (pedidosRepository.existsById(id)) {
				inmuebleModel = mapperFacade.map(inmuebleDomain, InmuebleModel.class);
				inmuebleModel.set_id(id);
				inmuebleDomain = mapperFacade.map(inmuebleModel, InmuebleDomain.class);
				System.out.println("Updating inmueble with the id: " + id);
				inmueblesRepository.save(inmuebleModel);
				// return storyModel;
				return inmuebleDomain;
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
				if (!usuariosRepository.existsById(id))
					throw new EntityNotFoundException("No se encontro", "/usuario/" + id);
				usuarioModel = usuariosRepository.findById(id).get();
				System.out.println("Rolesss"+usuarioModel);
				usuarioModel = mapperFacade.map(usuarioDomain, UsuarioModel.class);
				usuarioModel.set_id(id);
				
				//Primero obtener el role del Id
				
				//Encriptamos la contraseña que viene del Domain
				String encodedPassword = passwordEncoder.encode(usuarioDomain.getPassword());

				
				//System.out.println("Contraseña en texto plano"+usuarioDomain.getPassword());
				//System.out.println("Contraseña encryptada" + encodedPassword);
				
				//Establecemos la contraseña que viene del Domain ya encriptada
				usuarioModel.setPassword(encodedPassword);
				
				System.out.println("domain" + usuarioModel);
				
				boolean isPasswordMatch = passwordEncoder.matches(usuarioDomain.getPassword(), encodedPassword);
				System.out.println("Password : " + usuarioDomain.getPassword() + "   isPasswordMatch    : " + isPasswordMatch);
				
				
				Set<String> strRoles = usuarioDomain.getRoles();
				System.out.println("domain" + strRoles);
				Set<Role> roles = new HashSet<>();
								
				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						case "mod":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
						}
					});
				}
				
				usuarioModel.setRoles(usuariosRepository.findById(id).get().getRoles());

				System.out.println("Roll despues de convertirlo" + usuarioModel.getRoles());

				usuarioDomain = mapperFacade.map(usuarioModel, UsuarioDomain.class);
				//System.out.println("Updating usuario with the id: " + id);
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