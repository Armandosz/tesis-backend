package com.inmuebles.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
	
	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
	  this.roles = roles;
	}
		

}
