package com.prekes.web.prekesweb.service;

import org.springframework.stereotype.Service;

@Service 
public class LoginService {
	public boolean validateUser(String uid, String passw) {
		return uid.equals("aaa") && passw.equals("aaa");
	}
}
