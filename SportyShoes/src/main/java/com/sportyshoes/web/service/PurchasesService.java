package com.sportyshoes.web.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.web.entity.Category;
import com.sportyshoes.web.entity.Product;
import com.sportyshoes.web.entity.Purchases;
import com.sportyshoes.web.entity.SystemUser;
import com.sportyshoes.web.repo.PurchasesRepository;

@Service
@Transactional
public class PurchasesService {
	@Autowired
	private PurchasesRepository purRepo;
	@Autowired
	private ProductService prodSrv;
	@Autowired
	private CategoryService catSrv;

	// Add / Process a purchase
	public Purchases processAPurchase(Purchases purchase) throws Exception {
		return this.purRepo.save(purchase);
	}

	// Get all purchases by user
	public List<Purchases> getPurchasesByUser(SystemUser user) {
		List<Purchases> purchases = new ArrayList<>();

		this.purRepo.findByPurUserEmail(user.getEmail()).forEach(purchase -> {
			Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
					purchase.getPurDate(), purchase.getPurCount());
			purchases.add(pur);
		});

		return purchases;
	}

	// Get all purchases by date
	public List<Purchases> getPurchasesByDate(Date date) {
		List<Purchases> purchases = new ArrayList<>();

		this.purRepo.findByPurDate(date).forEach(purchase -> {
			Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
					purchase.getPurDate(), purchase.getPurCount());
			purchases.add(pur);
		});

		return purchases;
	}

	// Find purchases by given date range
	public List<Purchases> getPurchasesByDateRange(Date startDate, Date endDate) {
		List<Purchases> purchases = new ArrayList<>();

		this.purRepo.findByPurDateBetween(startDate, endDate).forEach(purchase -> {
			Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
					purchase.getPurDate(), purchase.getPurCount());
			purchases.add(pur);
		});

		return purchases;
	}

	// Find purchases by given date range and category
	public List<Purchases> getPurchasesByDateAndCategory(Date startDate, Date endDate, String catDesription) {
		List<Purchases> purchases = new ArrayList<>();

		Category category = catSrv.getCategoryByDescription(catDesription);

		for (Product product : prodSrv.getAllProductsByCategory(category)) {

			this.purRepo.findByPurDateBetweenAndPurProdId(startDate, endDate, product.getProdId()).forEach(purchase -> {
				Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
						purchase.getPurDate(), purchase.getPurCount());
				purchases.add(pur);
			});
		}

		return purchases;
	}

	// Find purchases by given date range and user email
	public List<Purchases> getPurchasesByDateAndUserEmail(Date startDate, Date endDate, String email) {
		List<Purchases> purchases = new ArrayList<>();

		this.purRepo.findByPurDateBetweenAndPurUserEmail(startDate, endDate, email).forEach(purchase -> {
			Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
					purchase.getPurDate(), purchase.getPurCount());
			purchases.add(pur);
		});

		return purchases;
	}

	// Get all purchases by Product
	public List<Purchases> getPurchasesByProduct(Product product) {
		List<Purchases> purchases = new ArrayList<>();

		this.purRepo.findByPurProdId(product.getProdId()).forEach(purchase -> {
			Purchases pur = new Purchases(purchase.getPurId(), purchase.getPurProdId(), purchase.getPurUserEmail(),
					purchase.getPurDate(), purchase.getPurCount());
			purchases.add(pur);
		});

		return purchases;
	}

}
