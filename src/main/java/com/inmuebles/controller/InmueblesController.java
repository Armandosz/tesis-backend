package com.inmuebles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.inmuebles.domain.InmuebleDomain;
import com.inmuebles.domain.InmuebleDomainId;
import com.inmuebles.service.InmueblesServiceImpl;

@RestController
@RequestMapping(value = "/inventario")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@CrossOrigin(origins = "*", maxAge = 3600)
public class InmueblesController {

	@Autowired
	InmueblesServiceImpl inmuebleService;
	
	//Get All inmuebles
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/", produces = "application/json")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseBody
	public List<InmuebleDomain> getAllInmuebles() throws Exception {
		return inmuebleService.getAllInmuebles();
	}
	
	//Get inmueble by Id
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseBody
	public InmuebleDomain getInmuebleById(@Valid @PathVariable String id) throws Exception {
		return inmuebleService.getInmuebleById(id);
	}

	//POST inmueble
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Object> createInmueble(@Valid @RequestBody InmuebleDomain request) throws Exception {
		InmuebleDomainId inmuebleDomainId = new InmuebleDomainId();
		inmuebleDomainId.setId(inmuebleService.createInmueble(request));
		return new ResponseEntity<>(inmuebleDomainId, HttpStatus.CREATED);
	}
	
	//Update Inmueble
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public InmuebleDomain updateInmueble(@Valid @RequestBody InmuebleDomain request, @PathVariable String id) throws Exception {
		return inmuebleService.updateInmueble(request, id);
	}

	//Delete Inmueble
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = "/{id}")
	public void deleteInmueble(@Valid @PathVariable String id) throws Exception {
		inmuebleService.deleteInmueble(id);
	}
}