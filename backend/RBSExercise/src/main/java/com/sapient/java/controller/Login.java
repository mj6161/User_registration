package com.sapient.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class Login implements Serializable {

	/**
	* 
	*/
	@Autowired
	HttpSession session;

	private static final long serialVersionUID = 1L;
	/*
	 * @Autowired SessionManager sessionManager;
	 */

	@Autowired(required = true)
	private UserManager UserManager;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 * login controller: accepts and validates username and password and sets
	 * the session.. ..returns user details
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String msg, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		System.out.println(msg);
		Gson gson = new Gson();
		User user = gson.fromJson(msg, User.class);
		session = request.getSession(true);

		if (user.getUsername() != null) {
			System.out.println(user.getUsername());
			if (UserManager.validateUser(user.getUsername())) {
				if (UserManager.validatePassword(user.getPassword())) {
					User user1 = UserManager.getUserDetails(user.getUsername());
					System.out.println("setting username now " + user1);
					System.out.println(session.getId());
					session.setAttribute("user", user1);
					System.out.println("Session att: " + session.getAttribute("user"));

					response.setHeader("Access-Control-Allow-Origin", "*");

					final String userJSON = gson.toJson(user1);
					System.out.println(userJSON);
					return userJSON;
				}
			}
		}
		return null;

	}

	/* checks and returns logged in user */

	@RequestMapping(value = "/checklogin", method = RequestMethod.GET)
	public @ResponseBody String isLoggedin(HttpServletResponse response, HttpServletRequest request) throws Exception {

		Gson gson = new Gson();
		//
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			System.out.println("logger= " + user);
			final String userJSON = gson.toJson(user);
			System.out.println("user json" + userJSON);
			return userJSON;
		} else {
			System.out.println("none logged");
			return null;

		}

	}

	/*
	 * logout controller: accepts username as msg from client and removes user
	 * sesion
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody String logout(@RequestBody String msg, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		System.out.println(msg);
		Gson gson = new Gson();

		boolean value = true;

		if ((session.getAttribute("user")) != null) {
			session.removeAttribute("user");
			System.out.println("session" + session.getAttribute("user"));

			response.setHeader("Access-Control-Allow-Origin", "*");
			final String userJSON = gson.toJson(msg);
			System.out.println(userJSON);
			return userJSON;
		} else {
			return null;

		}
	}

}
