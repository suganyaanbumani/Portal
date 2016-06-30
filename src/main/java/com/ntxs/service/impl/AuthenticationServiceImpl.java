package com.ntxs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ntxs.dao.UserDao;
import com.ntxs.model.Authority;
import com.ntxs.model.User;
import com.ntxs.service.AuthenticationService;

@Component("authenticationService")
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User userInfo = userDao.findByLogin(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Authority authority: userInfo.getAuthorities()) {
			GrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority.getName());
			authorities.add(simpleAuthority);
		}
		UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(userInfo.getLogin(), 
				userInfo.getPassword(), authorities);
		return userDetails;
	}

}
