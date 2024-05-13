package org.jsp.merchantbootapp.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.jsp.merchantbootapp.service.MerchantService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
      @Autowired
      private MerchantService merchantService;
      
      @PostMapping
      @ResponseStatus(code = HttpStatus.CREATED)
      public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
    	  
    	  return merchantService.saveMerchant(merchant);
      }
      
      @GetMapping("/{id}")
      public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name="id")int id) {
    	 
    	  return merchantService.findById(id);
      }
      
      @PutMapping
      public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
    	  
    	  return merchantService.updateMerchant(merchant);
      }
      
      @GetMapping
      @ResponseStatus(code = HttpStatus.CREATED)
      public List<Merchant> findAllMerchants(){
    	  return merchantService.findAll();
      }
      
      @DeleteMapping("/{id}")
      public ResponseEntity<ResponseStructure<String>> deleteMerchant(@PathVariable(name="id") int id) {
    	 
    	  return merchantService.deleteMerchant(id);
      }
      
      @GetMapping("/find-by-name/{name}")
      public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable(name="name") String name){
    	  
    	  return merchantService.findByName(name);
      }
      
      @GetMapping("find-by-phone/{phone}")
      public ResponseEntity<ResponseStructure<Merchant>> findByPhone(@PathVariable(name="phone") long phone){
    	  return merchantService.findByPhone(phone);
      }
      
      @PostMapping("/verify-by-phone")
      public Merchant verifyMerchant(@RequestParam(name="phone") long phone, @RequestParam(name="password") String password) {
    	  return merchantService.verifyMerchant(phone,password);
      }
      
      @PostMapping("/verify-by-email")
      public Merchant verifyMerchant(@RequestParam(name="email") String email, @RequestParam(name="password") String password) {
    	  return merchantService.verifyMerchant(email,password);
      }
      
      @PostMapping("/verify-by-id")
      public Merchant verifyMerchant(@RequestParam(name="id")int id,@RequestParam(name="password")String password) {
    	  return merchantService.verifyMerchant(id,password);
      }
      
      @PostMapping("/verify-by-gst_number")
      public Merchant verifyMerchantByGst_number(@RequestParam(name="gst_number")String gst_number,@RequestParam(name="password")String password) {
    	  return merchantService.verifyMerchantByGst_number(gst_number,password);
    		  
      }
      
      @GetMapping("/find-all-names")
      public List<String> findAllNames(){
    	  return merchantService.findAllNames();
    	 
    	  
      }
}
