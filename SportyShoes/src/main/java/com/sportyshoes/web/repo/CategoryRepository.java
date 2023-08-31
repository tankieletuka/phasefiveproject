package com.sportyshoes.web.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.sportyshoes.web.entity.Category;

public interface CategoryRepository extends CrudRepository<Category	, String>{
	Optional<Category> findByCatDescription(String catDescription);
	void deleteByCatDescription(String catDescription);
	Optional<Category> findByCatId(long catId);
}