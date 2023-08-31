package com.sportyshoes.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_uid")
	private long prodId;
	
	@Column(name = "p_description")
	private String prodDescription;
	
	@Column(name = "p_c_uid")
	private long prodCatId;
	
	public Product(String prodDescription){
		this.prodDescription = prodDescription;
	}
	
	//if the product description and category ID are provided
	public Product(String prodDescription, long prodCatId){
		this.prodDescription = prodDescription;
		this.prodCatId = prodCatId;
	}
	
	//if the product ID and description are provided
	public Product(long prodId, String prodDescription){
		this.prodId = prodId;
		this.prodDescription = prodDescription;
	}
}
