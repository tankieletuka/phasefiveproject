package com.sportyshoes.web.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.repo.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository prodRepo;

	// Add a product
	public Product addProduct(Product product) throws Exception {
		if (!this.prodRepo.findByProdDescription(product.getProdDescription()).isEmpty())
			throw new Exception(product.getProdDescription() + " already exists.");
		return this.prodRepo.save(product);
	}
	
	// Update product - provide the ID and Hibernate will update instead of inserting
	public Product updateProduct(Product product) throws Exception {
		if (this.prodRepo.findByProdDescription(product.getProdDescription()).isEmpty())
			throw new Exception(product.getProdDescription() + "does not exist.");
		return this.prodRepo.save(product);
		
	}

	// Delete a product
	public void deleteProduct(String prodDescription) throws Exception {
		Optional<Product> Product = this.prodRepo.findByProdDescription(prodDescription);

		if (Product.isEmpty())
			throw new Exception(prodDescription + " does not exist and cannot delete");
		this.prodRepo.deleteByProdDescription(prodDescription);
	}

	// Get all products
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();

		this.prodRepo.findAll().forEach(product -> {
			Product prod = new Product(product.getProdId(), product.getProdDescription(), product.getProdCatId());
			products.add(prod);
		});

		return products;
	}

	// Get all products by category
	public List<Product> getAllProductsByCategory(Category categoty) {
		List<Product> products = new ArrayList<>();

		this.prodRepo.findByProdCatId(categoty.getCatId()).forEach(product -> {
			Product prod = new Product(product.getProdId(), product.getProdDescription(), product.getProdCatId());
			products.add(prod);
		});

		return products;
	}

	// Get product by description
	public Product getProductByDescription(String ProdDescription) {
		return this.prodRepo.findByProdDescription(ProdDescription)
				.orElseThrow(() -> new EntityNotFoundException(ProdDescription + " not found."));
	}

	// Get product by id
	public Product getProductById(long prodId) {
		return this.prodRepo.findByProdId(prodId)
				.orElseThrow(() -> new EntityNotFoundException(prodId + " not found."));
	}

}
