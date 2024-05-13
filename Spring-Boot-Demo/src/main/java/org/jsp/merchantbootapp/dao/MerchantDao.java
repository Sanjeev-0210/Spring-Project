package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	public boolean delete(int id) {
		Optional<Merchant> recMerchant = findById(id);
		if(recMerchant.isPresent()) {
			merchantRepository.delete(recMerchant.get());
			return true;
		}
			
		return false;
	}

	public List<Merchant> findByName(String name) {
		return merchantRepository.findByName(name);
	}

	public Optional<Merchant> findByPhone(long phone) {
		return merchantRepository.findByPhone(phone);
	}

	public Optional<Merchant> findByPhoneAndPassword(long phone, String password) {
		return merchantRepository.findByPhoneAndPassword(phone,password);
	}

	public Optional<Merchant> verifyByEmailAndPassword(String email, String password) {
		return merchantRepository.verifyByEmailAndPassword(email,password);
	}

	public Optional<Merchant> verifyIdAndPassword(int id, String password) {
		return merchantRepository.verifyIdAndPassword(id,password);
	}

	public Optional<Merchant> verifyByGst_numberAndPassword(String gst_number, String password) {
		return merchantRepository.verifyByGst_numberAndPassword(gst_number,password);
	}

	public List<String> findName() {
		return merchantRepository.findName();
	}

	

}
