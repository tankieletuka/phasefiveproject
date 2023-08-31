package com.sportyshoes.web.mvc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.entity.SystemUser;
import com.sportyshoes.web.service.CategoryService;
import com.sportyshoes.web.service.ProductService;
import com.sportyshoes.web.service.SystemUserService;

@Controller
public class AddController {
	@Autowired
	private ProductService prodSrv;
	@Autowired
	private CategoryService catSrv;
	@Autowired
	private SystemUserService userSrv;

	// Registering a user
	@GetMapping("/registerUser")
	public String registerPage(HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}
		return "registerUser";
	}

	@PostMapping("/registerUser")
	public String registerUser(@RequestParam String userEmail, @RequestParam String userName,
			@RequestParam String userSurname, ModelMap model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		SystemUser user = new SystemUser(userEmail, userName, userSurname);

		try {
			if (user.getEmail().isEmpty() | user.getName().isEmpty() | user.getName().isEmpty()) {
				model.addAttribute("errorMessage", "Neither name, surname nor email can be null.");
			} else {
				userSrv.addUser(user);
				model.addAttribute("errorMessage", "User email: " + userEmail + " - registered successfuly.");
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
		}

		return "registerUser";
	}

	@GetMapping("/addProduct")
	public String addProductPage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		List<Category> categories = catSrv.getAllCategories();

		model.addAttribute("categories", categories);

		return "addProduct";
	}

	@PostMapping("/addProduct")
	public String addProduct(@RequestParam String prodDescription, @RequestParam Integer catId, ModelMap model,
			HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		List<Category> categories = catSrv.getAllCategories();
		model.addAttribute("categories", categories);

		try {
			if (prodDescription.isEmpty() | catId == 0) {
				model.addAttribute("errorMessage",
						"Unsuccessful - either product description is empty or category not selected.");
				model.addAttribute("prodDescription", prodDescription);

			} else {
				Product product = new Product(prodDescription, catId);
				prodSrv.addProduct(product);
				model.addAttribute("errorMessage", "Product: " + prodDescription + " - added successfuly.");

			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("prodDescription", prodDescription);
		}

		return "addProduct";
	}

	@GetMapping("/addCategory")
	public String addCategoryPage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		return "addCategory";
	}

	@PostMapping("/addCategory")
	public String addCategory(@RequestParam String catDescription, ModelMap model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		try {
			if (catDescription.isEmpty()) {
				model.addAttribute("errorMessage", "Unsuccessful - category description cannot be empty.");
				model.addAttribute("catDescription", catDescription);

			} else {
				Category category = new Category(catDescription);
				catSrv.addCategory(category);
				model.addAttribute("errorMessage", "Category: " + catDescription + " - added successfuly.");

			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("catDescription", catDescription);
		}

		return "addCategory";
	}

}
