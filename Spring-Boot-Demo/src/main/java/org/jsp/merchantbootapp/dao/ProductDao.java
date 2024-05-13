package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public boolean delete(int id) {
		Optional<Product> recProduct = findById(id);
		if(recProduct.isPresent()) {
			productRepository.delete(recProduct.get());
			return true;
		}
			
		return false;
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}
	
//	public void delete(int id) {
//		productRepository.deleteById(id);
//	}
	
	public List<Product> findByCategory(String category){
		return productRepository.findByCategory(category);
	}

	public List<Product> findByMerchant_Id(int merchant_id) {
		return productRepository.findByMerchant_Id(merchant_id);
	}

	

}
