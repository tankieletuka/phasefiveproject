package com.sportyshoes.web.mvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.web.dao.ProductDAO;
import com.sportyshoes.web.dao.PurchasesDAO;
import com.sportyshoes.web.dao.UserDAO;
import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.entity.Purchases;
import com.sportyshoes.web.entity.SystemUser;
import com.sportyshoes.web.service.CategoryService;
import com.sportyshoes.web.service.ProductService;
import com.sportyshoes.web.service.PurchasesService;
import com.sportyshoes.web.service.SystemUserService;

@Controller
public class ViewController {
	@Autowired
	private CategoryService catSrv;
	@Autowired
	private ProductService prodSrv;
	@Autowired
	private SystemUserService userSrv;
	@Autowired
	private PurchasesService purSrv;

	@GetMapping("/productAdmin")
	public String adminPage(Map<String, List<ProductDAO>> map, Model model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		List<ProductDAO> products = new ArrayList<>();

		for (Product p : prodSrv.getAllProducts()) {
			ProductDAO prod = new ProductDAO(p.getProdId(), p.getProdDescription(),
					catSrv.getCategoryByCatId(p.getProdCatId()).getCatDescription());
			products.add(prod);
		}
		map.put("products", products);
		model.addAllAttributes(map);

		return "productAdmin";
	}

	@PostMapping("/productAdmin")
	public String adminPage(HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		return "redirect:productAdmin"; // this should be handled by the get for admin
	}

	@GetMapping("/userAdmin")
	public String viewUsers(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		// Get all users
		List<UserDAO> users = new ArrayList<>();

		for (SystemUser u : userSrv.getAllUsers()) {
			UserDAO user = new UserDAO(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.isIsadmin());
			users.add(user);
		}

		model.addAttribute("users", users);

		return "userAdmin";
	}

	@PostMapping("/userAdmin")
	public String viewUsers(HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		return "redirect:userAdmin";
	}

	//Views users/user with surname like a given surname
	@GetMapping("/searchUser")
	public String searchUserPage(Model model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}
		
		return "searchUser";
	}
	
	//Views users/user with surname like a given surname
		@PostMapping("/searchUser")
		public String searchUser(@RequestParam String searchValue, Model model, HttpSession session) {
			if (session.getAttribute("email") == null) {
				return "redirect:login";
			}
			
			if (searchValue.isEmpty()) {
				model.addAttribute("searchValue", searchValue);
				model.addAttribute("errorMessage", "Search value cannot be empty - please try again.");
				return "searchUser";
			}

			List<UserDAO> users = new ArrayList<>();

			for (SystemUser u : userSrv.getAllUsersByUserDetails(searchValue)) {
				UserDAO user = new UserDAO(u.getId(), u.getEmail(), u.getName(), u.getSurname(), u.isIsadmin());
				users.add(user);
			}

			model.addAttribute("searchValue", searchValue);
			
			if (users.isEmpty()) {
				model.addAttribute("errorMessage", "No user(s) found for the given search.");
				return "searchUser";
			} else {
				model.addAttribute("users", users);
			}
			
			return "searchUser";
		}
	
	@GetMapping("/viewCategories")
	public String viewCategories(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		// Get all categories
		List<Category> categories = new ArrayList<>();

		for (Category cat : catSrv.getAllCategories()) {
			categories.add(cat);
		}

		model.addAttribute("categories", categories);

		return "viewCategories";
	}

	@GetMapping("/purchasesAdmin")
	public String purchasesAdminPage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		return "purchasesAdmin";
	}

	@GetMapping("/viewPurchasesByDate")
	public String viewPurchasesByDatePage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		return "viewPurchasesByDate";
	}

	@PostMapping("/viewPurchasesByDate")
	public String viewPurchasesByDate(String startDate, String endDate, Model model, HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		if (startDate.isEmpty() | endDate.isEmpty()) {
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			model.addAttribute("errorMessage", "Neither start date nor end date can be empty - please try again.");
			return "viewPurchasesByDate";
		}

		Date stDate = null;
		Date edDate = null;

		try {
			stDate = Date.valueOf(startDate);
			edDate = Date.valueOf(endDate);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Please confirm the date format and try again. (YYYY-MM-DD)");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			return "viewPurchasesByDate";
		}

		List<Purchases> pur = purSrv.getPurchasesByDateRange(stDate, edDate);
		List<PurchasesDAO> purchases = new ArrayList<>();

		for (Purchases p : pur) {
			Product product = prodSrv.getProductById(p.getPurProdId());
			Category category = catSrv.getCategoryByCatId(product.getProdCatId());

			PurchasesDAO pd = new PurchasesDAO(p.getPurId(), p.getPurUserEmail(), product.getProdDescription(),
					category.getCatDescription(), p.getPurDate(), p.getPurCount());
			System.out.println(pd);

			purchases.add(pd);
		}

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		if (purchases.isEmpty()) {
			model.addAttribute("errorMessage", "No purchases found for the selected dates.");
			return "viewPurchasesByDate";
		} else {
			model.addAttribute("purchases", purchases);
		}

		return "viewPurchasesByDate";
	}

	@GetMapping("/viewPurchasesByDateAndCategory")
	public String viewPurchasesByDateAndCategoryPage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		// Get all categories
		List<Category> categories = new ArrayList<>();

		for (Category cat : catSrv.getAllCategories()) {
			categories.add(cat);
		}

		model.addAttribute("categories", categories);

		return "viewPurchasesByDateAndCategory";
	}

	@PostMapping("/viewPurchasesByDateAndCategory")
	public String viewPurchasesByDateAndCategory(String startDate, String endDate, String catDescription, Model model,
			HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		List<Category> categories = new ArrayList<>();

		for (Category cat : catSrv.getAllCategories()) {
			categories.add(cat);
		}

		model.addAttribute("categories", categories);
		model.addAttribute("catDescription", catDescription);

		if (startDate.isEmpty() | endDate.isEmpty() | catDescription == null) {
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			model.addAttribute("errorMessage",
					"Neither start date, end date nor category can be empty - please try again.");

			return "viewPurchasesByDateAndCategory";
		}
		
		Date stDate = null;
		Date edDate = null;

		try {
			stDate = Date.valueOf(startDate);
			edDate = Date.valueOf(endDate);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Please confirm the date format and try again. (YYYY-MM-DD)");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			return "viewPurchasesByDateAndCategory";
		}

		List<Purchases> pur = purSrv.getPurchasesByDateAndCategory(stDate, edDate, catDescription);
		List<PurchasesDAO> purchases = new ArrayList<>();

		for (Purchases p : pur) {
			Product product = prodSrv.getProductById(p.getPurProdId());
			Category category = catSrv.getCategoryByCatId(product.getProdCatId());

			PurchasesDAO pd = new PurchasesDAO(p.getPurId(), p.getPurUserEmail(), product.getProdDescription(),
					category.getCatDescription(), p.getPurDate(), p.getPurCount());
			System.out.println(pd);

			purchases.add(pd);
		}

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		if (purchases.isEmpty()) {
			model.addAttribute("errorMessage", "No purchases found for the selected dates and category.");
			return "viewPurchasesByDateAndCategory";
		} else {
			model.addAttribute("purchases", purchases);
		}

		return "viewPurchasesByDateAndCategory";
	}

	@GetMapping("/viewPurchasesByDateAndUser")
	public String vviewPurchasesByDateAndUserPage(Model model, HttpSession session) {

		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		// Get all users
		List<SystemUser> users = new ArrayList<>();

		for (SystemUser usr : userSrv.getAllUsers()) {
			users.add(usr);
		}

		model.addAttribute("users", users);

		return "viewPurchasesByDateAndUser";
	}

	@PostMapping("/viewPurchasesByDateAndUser")
	public String viewPurchasesByDateAndUser(String startDate, String endDate, String userEmail, Model model,
			HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}

		System.out.println(userEmail);
		// Get all users
		List<SystemUser> users = new ArrayList<>();

		for (SystemUser usr : userSrv.getAllUsers()) {
			users.add(usr);
		}

		model.addAttribute("users", users);
		model.addAttribute("userEmail", userEmail);

		if (startDate.isEmpty() | endDate.isEmpty() | userEmail == null) {
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			model.addAttribute("errorMessage",
					"Neither start date, end date nor user can be empty - please try again.");

			return "viewPurchasesByDateAndUser";
		}

		Date stDate = null;
		Date edDate = null;

		try {
			stDate = Date.valueOf(startDate);
			edDate = Date.valueOf(endDate);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Please confirm the date format and try again. (YYYY-MM-DD)");
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
			return "viewPurchasesByDateAndUser";
		}

		List<Purchases> pur = purSrv.getPurchasesByDateAndUserEmail(stDate, edDate, userEmail);
		List<PurchasesDAO> purchases = new ArrayList<>();

		for (Purchases p : pur) {
			Product product = prodSrv.getProductById(p.getPurProdId());
			Category category = catSrv.getCategoryByCatId(product.getProdCatId());

			PurchasesDAO pd = new PurchasesDAO(p.getPurId(), p.getPurUserEmail(), product.getProdDescription(),
					category.getCatDescription(), p.getPurDate(), p.getPurCount());
			System.out.println(pd);

			purchases.add(pd);
		}

		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);

		if (purchases.isEmpty()) {
			model.addAttribute("errorMessage", "No purchases found for the selected dates and user.");
			return "viewPurchasesByDateAndUser";
		} else {
			model.addAttribute("purchases", purchases);
		}

		return "viewPurchasesByDateAndUser";
	}

}
