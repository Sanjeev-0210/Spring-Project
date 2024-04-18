package org.jsp.usermvcapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDao {
	@Autowired
	private EntityManager entityManager;

	public User saveUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		entityManager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}

	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	public User updateUser(User user) {
		EntityTransaction transaction = entityManager.getTransaction();
		User dbuser = entityManager.find(User.class, user.getId());
		if (dbuser != null) {
			dbuser.setName(user.getName());
			dbuser.setPhone(user.getPhone());
			dbuser.setEmail(user.getEmail());
			dbuser.setPassword(user.getPassword());
			transaction.begin();
			transaction.commit();
			return dbuser;
		}
		return null;
	}

	public String deleteUser(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		User user = entityManager.find(User.class, id);
		if (user != null) {
			entityManager.remove(user);
			transaction.begin();
			transaction.commit();
			return "User Id:" + user.getId() + "has been Deleted!!!";
		}
		return "Invalid Id!!!";
	}

	public User verifyUserByIdandPassword(int id, String password) {
		Query q = entityManager.createQuery("select u from User u where u.id=?1 and u.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User verifyUserByPhoneandPassword(long phone, String password) {
		Query q = entityManager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User verifyUserByEmailandPassword(String email, String password) {
		Query q = entityManager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
