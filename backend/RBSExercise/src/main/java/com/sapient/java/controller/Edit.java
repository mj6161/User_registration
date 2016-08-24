package com.sapient.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.sapient.java.manager.SessionManager;
import com.sapient.java.manager.UserManager;
import com.sapient.java.pojo.User;

@Controller
public class Edit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Autowired(required = true)
	private UserManager userManager;

	/* accepts edited user details and updates the database */

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody String EditProfile(@RequestBody String msg, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		System.out.println(msg);

		Gson gson = new Gson();
		User user = gson.fromJson(msg, User.class);

		user.setUsername(user.getUsername());
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setPassword(user.getPassword());
		user.setDesignation(user.getDesignation());
		user.setPhone_no(user.getPhone_no());

		userManager.editUser(user);
		response.setHeader("Access-Control-Allow-Origin", "*");

		final String userJSON = gson.toJson(user);
		System.out.println("returning" + userJSON);
		return userJSON;

	}
}
