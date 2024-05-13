package org.jsp.merchantbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MerchantRepository extends JpaRepository<Merchant,Integer>{

	List<Merchant> findByName(String name);
	
	Optional<Merchant> findByPhone(long phone);
	
	Optional<Merchant> findByPhoneAndPassword(long phone,String password);
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	Optional<Merchant> verifyByEmailAndPassword(String email, String password);
	
	@Query("select m from Merchant m where m.id=?1 and m.password=?2")
	Optional<Merchant> verifyIdAndPassword(int id,String password);
	
	@Query("select m from Merchant m where m.gst_number=?1 and m.password=?2")
	Optional<Merchant> verifyByGst_numberAndPassword(String Gst_number,String password);

	@Query("select m.name from Merchant m")
	List<String> findName();
}
