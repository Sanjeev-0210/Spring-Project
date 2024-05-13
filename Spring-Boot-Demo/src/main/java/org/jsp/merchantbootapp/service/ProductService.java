package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, int merchant_id) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			product.setMerchant(merchant); // assigning merchant to product
			merchant.getProducts().add(product);// Assigning product to Merchant
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setMessage("Product Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData(productDao.saveProduct(product));
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new ProductNotFoundException("Can't Saved, Merchant Id is not found!!!");

	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(id);
		if (recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recProduct.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Invalid Product Id!!!");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> recProduct = productDao.findById(product.getId());
		if (recProduct.isPresent()) {
			Product dbProducts = recProduct.get();
			dbProducts.setName(product.getName());
			dbProducts.setBrand(product.getBrand());
			dbProducts.setCategory(product.getCategory());
			dbProducts.setDescription(product.getDescription());
			dbProducts.setImg_url(product.getImg_url());
			dbProducts.setCost(product.getCost());
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setData(productDao.saveProduct(dbProducts));
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new ProductNotFoundException("Invalid Product Id!!!");
	}

	public List<Product> findAll() {
		return productDao.findAll();
	}

	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable(name = "id") int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData("Product deleted!!!");
			productDao.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Can't Delete Product as ID is Invalid!!!");

	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> recProduct = productDao.findByBrand(brand);
		structure.setData(recProduct); 
		if (recProduct.isEmpty()) {
			throw new ProductNotFoundException("Invalid Product Brand!!!");
			
		}
		structure.setMessage("List of Product with entered Brand");
		  structure.setStatusCode(HttpStatus.OK.value());
	  return ResponseEntity.status(HttpStatus.OK).body(structure);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> recProduct = productDao.findByCategory(category);
		structure.setData(recProduct);
		if(recProduct.isEmpty()) {
			throw new ProductNotFoundException("Invalid Product Category!!!");
		}
		structure.setMessage("List of Product with entered Category");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchant_Id(int merchant_id){
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		List<Product> recProduct = productDao.findByMerchant_Id(merchant_id);
		structure.setData(recProduct);
		if(recProduct.isEmpty()) {
			throw new ProductNotFoundException("Invalid Merchant ID!!!");
		}
		structure.setMessage("List of Product with entered Merchant ID");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
	

}
