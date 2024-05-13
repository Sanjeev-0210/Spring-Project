package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MerchantService {

	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
  	  ResponseStructure<Merchant> structure = new ResponseStructure<>();
  	  structure.setMessage("Merchant Saved");
  	  structure.setStatusCode(HttpStatus.CREATED.value());
  	  structure.setData(merchantDao.saveMerchant(merchant));
  	  return ResponseEntity.status(HttpStatus.CREATED).body(structure);
    }
	
	public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant=merchantDao.findById(id);
  	  if(recMerchant.isPresent()) {
  		  structure.setMessage("Merchant Found");
      	  structure.setStatusCode(HttpStatus.OK.value());
      	  structure.setData(recMerchant.get());
      	  return ResponseEntity.status(HttpStatus.OK).body(structure);
  	  }
  	  throw new MerchantNotFoundException("Invalid Merchant Id!!!");
	}
	
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
  	  ResponseStructure<Merchant> structure = new ResponseStructure<>();
  	  Optional<Merchant> recMerchant=merchantDao.findById(merchant.getId());
  	  if(recMerchant.isPresent()) {
  		  Merchant dbMerchant= recMerchant.get();
  		  dbMerchant.setEmail(merchant.getEmail());
  		  dbMerchant.setGst_number(merchant.getGst_number());
  		  dbMerchant.setName(merchant.getName());
  		  dbMerchant.setPassword(merchant.getPassword());
  		  dbMerchant.setPhone(merchant.getPhone());
  		  structure.setMessage("Merchant Saved");
      	  structure.setStatusCode(HttpStatus.CREATED.value());
      	  structure.setData(merchantDao.saveMerchant(dbMerchant));
      	  return ResponseEntity.status(HttpStatus.CREATED).body(structure);
  	  }
  	throw new MerchantNotFoundException("Invalid Merchant Id!!!");
    }
	
	public List<Merchant> findAll(){
  	  return merchantDao.findAll();
    }
	
	public ResponseEntity<ResponseStructure<String>> deleteMerchant(@PathVariable(name="id") int id) {
  	  Optional<Merchant> recMerchant=merchantDao.findById(id);
  	ResponseStructure<String> structure = new ResponseStructure<>();
  	  if(recMerchant.isPresent()) {
  		structure.setMessage("Merchant Found");
    	  structure.setStatusCode(HttpStatus.OK.value());
    	  structure.setData("Merchant deleted!!!");
  		merchantDao.delete(id);
  		  return ResponseEntity.status(HttpStatus.OK).body(structure);
  	  }
  	throw new MerchantNotFoundException("Can't Delete Merchant as ID is Invalid!!!");
  	 
    }
	
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable(name="name") String name){
  	  ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
  	  List<Merchant> merchants= merchantDao.findByName(name);
  	  structure.setData(merchants); 
  	  if(merchants.isEmpty()) {
  		throw new MerchantNotFoundException("No Merchant Found with entered name");
  	  }
  	  structure.setMessage("List of Merchant with entered name");
		  structure.setStatusCode(HttpStatus.OK.value());
  	  return ResponseEntity.status(HttpStatus.OK).body(structure);
    }
	
     public ResponseEntity<ResponseStructure<Merchant>> findByPhone(@PathVariable(name="phone") long phone){
   	  ResponseStructure<Merchant> structure = new ResponseStructure<>();
   	  Optional<Merchant> recMerchant= merchantDao.findByPhone(phone);
   	  structure.setData(recMerchant.get()); 
   	  if(recMerchant.isEmpty()) {
   		throw new MerchantNotFoundException("No Merchant Found with entered Phone");
   	  }
   	  structure.setMessage("Merchant with entered Phone");
		  structure.setStatusCode(HttpStatus.OK.value());
   	  return ResponseEntity.status(HttpStatus.OK).body(structure);
     }
     
     public Merchant verifyMerchant(@RequestParam(name="phone") long phone, @RequestParam(name="password") String password) {
   	  Optional<Merchant> recMerchant = merchantDao.findByPhoneAndPassword(phone,password);
   	  if(recMerchant.isPresent())
   		  return recMerchant.get();
   	  return null;
     }
     
     public Merchant verifyMerchant(@RequestParam(name="email") String email, @RequestParam(name="password") String password) {
   	  Optional<Merchant> recMerchant = merchantDao.verifyByEmailAndPassword(email,password);
   	  if(recMerchant.isPresent())
   		  return recMerchant.get();
   	throw new MerchantNotFoundException("Invalid Email or Password!!!");
 	 
     }
     
     public Merchant verifyMerchant(@RequestParam(name="id")int id,@RequestParam(name="password")String password) {
   	  Optional<Merchant> recMerchant=merchantDao.verifyIdAndPassword(id,password);
   	  if(recMerchant.isPresent()) 
   		  return recMerchant.get();
   	throw new MerchantNotFoundException("Invalid ID or Password!!!"); 
     }
     
     public Merchant verifyMerchantByGst_number(@RequestParam(name="gst_number")String gst_number,@RequestParam(name="password")String password) {
   	  Optional<Merchant> recMerchant=merchantDao.verifyByGst_numberAndPassword(gst_number,password);
   	  if(recMerchant.isPresent()) 
   		  return recMerchant.get();
   	throw new MerchantNotFoundException("Invalid GST_Number or Password!!!");
   		  
     }
     
     public List<String> findAllNames(){
   	  return merchantDao.findName();
   	 
   	  
     }
	
	
}
