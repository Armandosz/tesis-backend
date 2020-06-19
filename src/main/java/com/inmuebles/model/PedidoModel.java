package com.inmuebles.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "inventario")
public class PedidoModel {
	@Id
	private String _id;
	/*Manejar numero como Id*/
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
