package com.sapient.java.manager;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

	public HttpSession session;

	public HttpSession returnSession() {
		return session;
	}

}
