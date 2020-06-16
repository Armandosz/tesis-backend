package com.inmuebles.domain;

import lombok.Data;

@Data
public class PedidoDomain {

	private String _id;
	
	private String numero;

	private String descripcion;
	
	private String marca;
	
	private String modelo;
	
	private String serie;
	
	private String ubicacion;
	
	private String no_trabajador;
	
	private String custodio;
	
	private String cve_depen;
	
	private String dependencia;
	
	private String custodio2;
	
	private String observaciones;
}
