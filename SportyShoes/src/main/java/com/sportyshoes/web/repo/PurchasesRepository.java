package com.sportyshoes.web.repo;


import java.sql.Date;
import org.springframework.data.repository.CrudRepository;
import com.sportyshoes.web.entity.Purchases;

public interface PurchasesRepository extends CrudRepository<Purchases, String> {
	Iterable<Purchases> findByPurUserEmail(String purUserEmail);
	Iterable<Purchases> findByPurProdId(long purProdId);
	Iterable<Purchases> findByPurDate(Date purDate);
	Iterable<Purchases> findByPurDateBetween(Date startDate, Date endDate);
	Iterable<Purchases> findByPurDateBetweenAndPurProdId(Date startDate, Date endDate, long purProdId);
	Iterable<Purchases> findByPurDateBetweenAndPurUserEmail(Date startDate, Date endDate, String email);
}