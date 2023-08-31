package com.sportyshoes.web.dao;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchasesDAO {
	// Purchases Data Access Object
	private int    purId;
	private String userEmail;
	private String prodDescription;
	private String prodCategory;
	private Date   purDate;
	private int    purCount;
}
