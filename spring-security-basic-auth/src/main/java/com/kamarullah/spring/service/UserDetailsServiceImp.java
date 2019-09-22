package com.kamarullah.spring.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kamarullah.spring.dao.UserDao;
import com.kamarullah.spring.model.User;

/**
 * 
 * Class : UserDetailsServiceImp
 * Create Date : 22/Sep/2019
 * 
 * @author Wahid Kamarullah
 *
 * Copyright(C) 2019, KamarullahLabs (Indonesia) Co., Ltd. All rights reserved.
 */
@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	public UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		// Checking username is empty
		if (username.length() == 0) {
			logger.warn("Cannot pass null or empty values to constructor");
			throw new UsernameNotFoundException("Username not found: " + username);
		}
		
		try {
			
			// initiate userdetails parameters
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			String password 				= "";
			Boolean enabled 				= false;
			Boolean accountNonExpired 		= false;
			Boolean credentialsNonExpired 	= false;
			Boolean accountNonLocked 		= false;
			
			User user = userDao.setAuthorities(username);
			if (user != null) {
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				
				password 				= user.getPassword();
				enabled 				= true;
				accountNonExpired 		= true;
				credentialsNonExpired 	= true;
				accountNonLocked 		= true;
			}
			
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password, enabled, 
					accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		    return userDetails;
		    
		} catch (UsernameNotFoundException e) {
			logger.warn("Username not found: " + username);
			throw new UsernameNotFoundException("Username not found: " + username);
		}
	    
	}

}
