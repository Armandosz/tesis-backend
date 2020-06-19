package com.inmuebles.domain;

import lombok.Data;

@Data
public class UsuarioDomain {
	
	private String _id;
	private String username;
	private String email;
	private String name;
	private String password;
	
	//private List<RolesDomain> roles;
	
	//private List<TasksDomain> tasks;

//	public StoryDomain() {
//		this.history = new ArrayList<>();
//		this.tasks = new ArrayList<>();
//	}
	
}
