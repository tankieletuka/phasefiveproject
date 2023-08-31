package com.sportyshoes.web.dao;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDAO {
	// Product Data Access Object
	
	private long prodId;
	private String prodDescription;
	private String catDescription;

}
