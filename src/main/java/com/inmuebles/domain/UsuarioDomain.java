package com.inmuebles.domain;

import java.util.HashSet;
import java.util.Set;

import com.inmuebles.model.Role;

import lombok.Data;

@Data
public class UsuarioDomain {
	
	private String _id;
	private String username;
	private String email;
	private String name;
	private String password;
	
    private Set<String> roles;

	//private Set<Role> roles = new HashSet<>();
	
	
	//private List<RolesDomain> roles;
	
	//private List<TasksDomain> tasks;

	//public StoryDomain() {
	//	this.history = new ArrayList<>();
	//	this.tasks = new ArrayList<>();
	//}
	
	public Set<String> getRoles() {
	  return this.roles;
	}

	public void setRoles(Set<String> roles) {
	  this.roles = roles;
	}
	
}
