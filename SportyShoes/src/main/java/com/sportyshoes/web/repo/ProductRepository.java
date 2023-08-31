package com.sportyshoes.web.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.sportyshoes.web.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	Optional<Product> findByProdDescription(String prodDescription);
	void deleteByProdDescription(String prodDescription);
	Optional<Product> findByProdId(long prodId);
	Iterable<Product> findByProdCatId(long catId);
}