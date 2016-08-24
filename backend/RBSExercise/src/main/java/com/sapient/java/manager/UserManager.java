package com.sapient.java.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.java.dao.UserDetailsDao;
import com.sapient.java.pojo.User;

/**
 * 
 * The class user maanager
 *
 */
@Component
public class UserManager {

	@Autowired(required = true)
	private UserDetailsDao userDetailsDao;

	private User user;

	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean validateUser(String username) {
		this.user = userDetailsDao.findUser(username);
		if (this.user != null)
			return true;
		return false;
	}

	/**
	 * 
	 * @param password
	 * @return
	 */

	public boolean validatePassword(String password) {
		if (this.user.getPassword().equals(password))
			return true;
		return false;
	}

	// register manager..
	// ..
	/**
	 * 
	 * @param userDetails
	 */

	public void registerUser(User userDetails) {
		userDetailsDao.persist(userDetails);

	}

	public boolean checkUserName(String username) {
		return userDetailsDao.checkUsername(username);

	}

	// edit manager..
	// ..
	/**
	 * 
	 * @param userDetails
	 */

	public void editUser(User userDetails) {
		userDetailsDao.merge(userDetails);

	}

	/**
	 * 
	 * @param username
	 * @return
	 */

	public User getUserDetails(String username) {
		return userDetailsDao.getUserDetails(username);
	}
}
