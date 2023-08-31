package com.sportyshoes.web.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.web.dao.ProductDAO;
import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.service.CategoryService;
import com.sportyshoes.web.service.ProductService;

@Controller
public class ModifyController {

	@Autowired
	private ProductService prodSrv;
	@Autowired
	private CategoryService catSrv;

	@GetMapping("/editProduct")
	public String editProduct(@RequestParam Integer prodId, @RequestParam String prodDescription,
			@RequestParam String catDescription, Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		Product product = prodSrv.getProductById(prodId);
		Category category = catSrv.getCategoryByCatId(product.getProdCatId());

		ProductDAO prod = new ProductDAO(product.getProdId(), product.getProdDescription(),
				category.getCatDescription());
		List<Category> categories = catSrv.getAllCategories();

		model.addAttribute("product", prod);
		model.addAttribute("categories", categories);

		return "editProduct";
	}

	@PostMapping("/editProduct")
	public String editProduct(@RequestParam Integer prodId, @RequestParam String prodDescription,
			@RequestParam Integer catId, ModelMap model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		try {
			prodSrv.updateProduct(new Product(prodId, prodDescription, catId));

			// Get all products - including the updated one
			List<ProductDAO> products = new ArrayList<>();

			for (Product p : prodSrv.getAllProducts()) {
				ProductDAO prod = new ProductDAO(p.getProdId(), p.getProdDescription(),
						catSrv.getCategoryByCatId(p.getProdCatId()).getCatDescription());
				products.add(prod);
			}

			model.addAttribute("products", products);
			model.addAttribute("updatedProduct", prodId);
			model.addAttribute("updateMessage", "Product ID: " + prodId + " - updated successfuly.");
		} catch (Exception e) {
			// Get all products - including the updated one
			List<ProductDAO> products = new ArrayList<>();

			for (Product p : prodSrv.getAllProducts()) {
				ProductDAO prod = new ProductDAO(p.getProdId(), p.getProdDescription(),
						catSrv.getCategoryByCatId(p.getProdCatId()).getCatDescription());
				products.add(prod);
			}

			model.addAttribute("products", products);
			model.addAttribute("updatedProduct", prodId);
			model.addAttribute("updateMessage", e.getMessage());
		}

		return "productAdmin";
	}

	@GetMapping("/deleteProduct")
	public String deletePage(@RequestParam Integer prodId, @RequestParam String prodDescription,
			@RequestParam String catDescription, Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		ProductDAO prod = new ProductDAO(prodId, prodDescription, catDescription);

		model.addAttribute("product", prod);

		return "deleteProduct";
	}

	@PostMapping("/deleteProduct")
	public String deleteProduct(@RequestParam String prodDescription, ModelMap model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		try {
			prodSrv.deleteProduct(prodDescription);

			// Get all products
			List<ProductDAO> products = new ArrayList<>();

			for (Product p : prodSrv.getAllProducts()) {
				ProductDAO prod = new ProductDAO(p.getProdId(), p.getProdDescription(),
						catSrv.getCategoryByCatId(p.getProdCatId()).getCatDescription());
				products.add(prod);
			}

			model.addAttribute("products", products);
			model.addAttribute("updateMessage", "Product: " + prodDescription + " - deleted successfuly.");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("updateMessage", "Error: " + e.getMessage());
		}

		return "productAdmin";
	}

}
