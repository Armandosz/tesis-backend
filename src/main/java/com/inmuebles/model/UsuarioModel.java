package com.inmuebles.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class UsuarioModel {
	@Id
	private String _id;
	private String username;
	private String email;
	private String name;
	private String password;

}
