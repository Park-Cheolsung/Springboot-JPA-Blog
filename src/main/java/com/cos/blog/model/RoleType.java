package com.cos.blog.model;

import javax.persistence.EnumType;

public enum RoleType {

	USER("user"), ADMIN("admin");
	
	private String roleName;
	
	RoleType(String string) {
		// TODO Auto-generated constructor stub
		this.roleName = string;
	}
	
	public String getStatus() {
		
		return roleName;
	}
}
