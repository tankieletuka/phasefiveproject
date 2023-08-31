package com.sportyshoes.web.mvc;

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
import com.sportyshoes.web.entity.LoginEntity;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.entity.SystemUser;
import com.sportyshoes.web.service.CategoryService;
import com.sportyshoes.web.service.ProductService;
import com.sportyshoes.web.service.SystemUserService;

@Controller
public class LogController {

	@Autowired
	private ProductService prodSrv;
	@Autowired
	private CategoryService catSrv;
	@Autowired
	private SystemUserService userSrv;

	public LogController() {
	}

	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@PostMapping("/")
	public String home() {
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login(Map<String, String> map, Model model, HttpSession session) {		
		session.invalidate();
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@RequestParam String email, @RequestParam String password,
			Map<String, List<ProductDAO>> map, Model model, HttpSession session) 
	{
		LoginEntity entity = new LoginEntity(email, password);

		if (userSrv.validateUser(entity)) 
		{
			session.setAttribute("email", entity.getEmail());
			
			List<ProductDAO> products = new ArrayList<>();

			for (Product p : prodSrv.getAllProducts()) {
				ProductDAO prod = new ProductDAO(p.getProdId(), p.getProdDescription(),
						catSrv.getCategoryByCatId(p.getProdCatId()).getCatDescription());
				products.add(prod);
			}
			map.put("products", products);
			model.addAllAttributes(map);

			return "redirect:productAdmin";

		} else {
			session.setAttribute("errorMessage", "Invalid credentials, please try again.");
			return "login";
		}

	}
	
	//Admin change password
	@GetMapping("/adminChangePassword")
	public String adminChangePassword(HttpSession session, Map<String, String> map) {
		
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}
		
		return "adminChangePassword";
	}
	
	//Admin change password
	@PostMapping("/adminChangePassword")
	public String adminChangePassword(@RequestParam String email, @RequestParam String password, HttpSession session, Map<String, String> map) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}
		
		try {
			if (password.isEmpty()) {
				map.put("errorMessage", "Unsuccessful - password cannot be empty.");
			} else {
				SystemUser usr = userSrv.getUserByEmail(email);
				usr.setPassword(password);
								
				userSrv.updateUser(usr);
				map.put("errorMessage", "Password changed successfully, please re-login to continue.");
				return "login";
				
			}
		} catch (Exception e) {
			map.put("errorMessage", e.getMessage());
		}
		
		return "adminChangePassword";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Map<String, String> map) {
		session.invalidate();
		map.put("errorMessage", "Successfully logged out.");
		return "login";
	}
}
