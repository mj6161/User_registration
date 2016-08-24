package com.sapient.java.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.java.pojo.User;

@Component
public class UserDetailsDao {
	@PersistenceContext
	private EntityManager entityManager;

	/* query to find user by username */

	@Transactional
	public User findUser(String username) {
		String query;
		query = "from User where USERNAME= :username";

		Query q = entityManager.createQuery(query, User.class);
		q.setParameter("username", username);
		System.out.println(username);
		System.out.println(q.getResultList().size());
		List<User> results = q.getResultList();
		System.out.println(!(results.isEmpty()));
		if (!(results.isEmpty())) {
			// user= (User) q.getSingleResult();
			for (User user : results) {
				System.out.println(user != null);
				if (user != null) {
					if (user.getUsername().equals(username))
						return user;
				}
			}
		}
		return null;
	}

	/* query to find if username exits in db */
	
	@Transactional
	public boolean checkUsername(String username) {
		if (entityManager.find(User.class, username) != null)
			return true;
		else
			return false;
	}

	/* query to persist uset data */
	
	@Transactional
	public void persist(User userDetails) {
		entityManager.persist(userDetails);
		System.out.println(userDetails);

	}
	/* query to get userdetails by username */
	@Transactional
	public User getUserDetails(String username) {
		String query;
		User user = null;
		query = "from User where username= :username";
		Query q = entityManager.createQuery(query);
		q.setParameter("username", username);

		if (q.getResultList().size() > 0) {
			user = (User) q.getSingleResult();
			if (user.getUsername().equals(username))
				return user;
			else
				return null;
		}
		return user;
	}

	/* query to merge edited fields */
	
	@Transactional
	public void merge(User userDetails) {
		entityManager.merge(userDetails);

	}

}
