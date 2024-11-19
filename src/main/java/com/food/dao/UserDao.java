package com.food.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.food.dto.User;



public class UserDao {

	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("teju").createEntityManager();
	}

	public User saveUser(User user) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.merge(user);
		entityTransaction.commit();

		return user;
	}

	public User getUserByEmail(String email) {
	    EntityManager entityManager = getEntityManager();
	    Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
	    query.setParameter("email", email);

	    List<User> users = query.getResultList();

	    // Return the first user if found, or null otherwise
	    return users.isEmpty() ? null : users.get(0);
	}

	public User updateUser(User user) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.merge(user);
		entityTransaction.commit();

		return user;
	}
	public User getUserById(int id) {
		EntityManager entityManager = getEntityManager();
		User user = entityManager.find(User.class, id);
		return user;
	}

}
