package org.jsp.springempapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springempapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class EmployeeDao {

	@Autowired
	private EntityManager entityManager;
	
	public Employee saveEmployee(Employee employee) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityManager.persist(employee);
		entityTransaction.begin();
		entityTransaction.commit();
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Employee dbEmployee = entityManager.find(Employee.class, employee.getId());
		if (dbEmployee != null) {
			dbEmployee.setDesg(employee.getDesg());
			dbEmployee.setEmail(employee.getEmail());
			dbEmployee.setName(employee.getName());
			dbEmployee.setPassword(employee.getPassword());
			dbEmployee.setPhone(employee.getPhone());
			dbEmployee.setSalary(employee.getSalary());
			entityTransaction.begin();
			entityTransaction.commit();
			return dbEmployee;
		}
		return null;
	}

	public Employee findEmployeeById(int id) {
		return entityManager.find(Employee.class, id);
	}

	public Employee verifyEmployee(long phone, String password) {
		Query query = entityManager.createQuery("select e from Employee e where e.phone=?1 and e.password=?2");
		query.setParameter(1, phone);
		query.setParameter(2, password);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(String email, String password) {
		Query query = entityManager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(int id, String password) {
		Query query = entityManager.createQuery("select e from Employee e where e.id=?1 and e.password=?2");
		query.setParameter(1, id);
		query.setParameter(2, password);
		try {
			return (Employee) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Employee> findBySalary(double salary) {
		javax.persistence.Query query = entityManager.createQuery("select e from Employee e where e.salary=?1");
		query.setParameter(1, salary);
		return query.getResultList();
	}

	public List<Employee> findByDesg(String desg) {
		Query query = entityManager.createQuery("select e from Employee e where e.desg=?1");
		query.setParameter(1, desg);
		return query.getResultList();
	}

	public List<Employee> findByName(String name) {
		Query query = entityManager.createQuery("select e from Employee e where e.name=?1");
		query.setParameter(1, name);
		return query.getResultList();
	}

	public List<Employee> findBySalary(double min_salary, double max_salary) {
		Query query = entityManager.createQuery("select e from Employee e where e.salary between ?1 and ?2");
		query.setParameter(1, min_salary);
		query.setParameter(2, max_salary);
		return query.getResultList();
	}
	
}
