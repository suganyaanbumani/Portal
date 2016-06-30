package com.ntxs.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ntxs.service.AuthenticationService;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	 
	 @Autowired
	 private AuthenticationService authenticationService;
	 
	 @Override
	 public void onAuthenticationSuccess(HttpServletRequest request, 
			 	HttpServletResponse response, Authentication authentication)
			 				throws ServletException, IOException {
		 UserDetails user = authenticationService.loadUserByUsername(authentication.getName());
		 SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
	 }
}
