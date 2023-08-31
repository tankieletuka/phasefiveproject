package com.sportyshoes.web.service;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.repo.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;
	
	//Add a category
	public Category addCategory(Category category) throws Exception
	{
		if(!this.catRepo.findByCatDescription(category.getCatDescription()).isEmpty())
			throw new Exception(category.getCatDescription()+ " already exists.");
		return this.catRepo.save(category);	
	}
	
	//Delete a category
	public void deleteCategory(String catDescription) throws Exception
	{
		Optional<Category> category = this.catRepo.findByCatDescription(catDescription);
		
		if(category.isEmpty())
			throw new Exception(catDescription+ " does not exist and cannot delete");
		this.catRepo.deleteByCatDescription(catDescription);
	}
	
	//Get all categories
	public List<Category> getAllCategories()
	{
		List<Category> categories = new ArrayList<>();		
		
		this.catRepo.findAll().forEach(category -> {
			Category cat = new Category((long) category.getCatId(), category.getCatDescription());
			categories.add(cat);
		});
		
		return categories;
	}
	
	//Get category by description
	public Category getCategoryByDescription(String catDescription)
	{
		return this.catRepo.findByCatDescription(catDescription)
				.orElseThrow(()-> new EntityNotFoundException(catDescription + " not found."));
	}
	
	//Get category by id
	public Category getCategoryByCatId(long catId)
	{	
		return this.catRepo.findByCatId(catId)
				.orElseThrow(()-> new EntityNotFoundException(catId + " not found."));
	}
}
