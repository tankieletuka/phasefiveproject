package com.sportyshoes.web.entity;

import java.sql.Date;

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
@Table(name = "purchases")
public class Purchases {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pur_uid")
	private int purId;
	
	@Column(name = "pur_p_uid")
	private long purProdId;
	
	@Column(name = "pur_u_email")
	private String purUserEmail;
	
	@Column(name = "pur_date")
	private Date purDate;
	
	@Column(name = "pur_count")
	private int purCount;
	
	public Purchases(int purProdId, String purUserEmail, Date purDate, int purCount) {
		this.purProdId = purProdId;
		this.purUserEmail = purUserEmail;
		this.purDate = purDate;
		this.purCount = purCount;
	}

}
