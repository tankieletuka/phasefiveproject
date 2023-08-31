package com.sportyshoes.web.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDAO {
	// User Data Access Object
	
	private int     id;
	private String  email;
	private String  name;
	private String  surname;
	private boolean  isadmin;
	
}
