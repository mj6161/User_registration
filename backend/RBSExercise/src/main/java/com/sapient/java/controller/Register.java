package com.sapient.java.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sapient.java.manager.UserManager;
import com.sapient.java.pojo.User;

@Controller
public class Register {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public UserManager userManager;

	/*
	 * Register controller: accepts user details from client and calls manager
	 * to persists in database
	 */

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String msg, HttpSession session, HttpServletResponse response)
			throws Exception {
		System.out.println(msg);
		Gson gson = new Gson();
		User user = gson.fromJson(msg, User.class);
		System.out.println(user);
		System.out.println(userManager.checkUserName(user.getUsername()));
		if (userManager.checkUserName(user.getUsername()) != true) {

			System.out.println("in if");
			userManager.registerUser(user);

			response.setHeader("Access-Control-Allow-Origin", "*");

			final String userJSON = gson.toJson(user);
			System.out.println(userJSON);
			return userJSON;
		} else {
			System.out.println("in else");
			return null;
		}
	}

}
