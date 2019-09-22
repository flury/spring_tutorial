package com.kamarullah.spring.dao;

import com.kamarullah.spring.model.User;

/**
 * 
 * Class : UserDao
 * Create Date : 22/Sep/2019
 * 
 * @author Wahid Kamarullah
 *
 * Copyright(C) 2019, KamarullahLabs (Indonesia) Co., Ltd. All rights reserved.
 */
public interface UserDao {

	public User setAuthorities(String username);
	
}
