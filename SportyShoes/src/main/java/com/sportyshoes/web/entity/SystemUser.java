package com.sportyshoes.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class SystemUser {
	@Id
	private int id;
	private String  email;
	private String  name;
	private String  surname;
	private boolean isadmin;
	private String  password;
	
	
	public SystemUser(int id, String email,String name,String surname,boolean isadmin) {
		this.id      = id;
		this.email   = email;
		this.name    = name;
		this.surname = surname;
		this.isadmin = isadmin;
	}
	
	public SystemUser(String email,String name,String surname,boolean isadmin) {
		this.email   = email;
		this.name    = name;
		this.surname = surname;
		this.isadmin = isadmin;
	}
	
	public SystemUser(String email,String name,String surname) {
		this.email   = email;
		this.name    = name;
		this.surname = surname;
	}
}
