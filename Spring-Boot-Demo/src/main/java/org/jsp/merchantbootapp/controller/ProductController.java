package org.jsp.merchantbootapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/{merchant_id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,@PathVariable int merchant_id) {
		return productService.saveProduct(product,merchant_id);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable(name="id")int id) {
  	 
  	  return productService.findById(id);
    }
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@GetMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<Product> findAllProducts(){
  	  return productService.findAll();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable(name="id") int id) {
  	 
  	  return productService.deleteProduct(id);
    }
    
    @GetMapping("/find-by-brand/{brand}")
    public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name="brand") String brand){
  	  
  	  return productService.findByBrand(brand);
    }
    
    @GetMapping("/find-by-category/{category}")
    public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable(name="category") String category){
    	return productService.findByCategory(category);
    }
    
    @GetMapping("/find-by-merchant_id/{merchant_id}")
    public ResponseEntity<ResponseStructure<List<Product>>> findByMerchant_Id(@PathVariable(name="merchant_id") int merchant_id){
    	return productService.findByMerchant_Id(merchant_id);
    }
    
    
	
}
