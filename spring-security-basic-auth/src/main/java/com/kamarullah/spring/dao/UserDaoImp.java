package com.kamarullah.spring.dao;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kamarullah.spring.model.User;

/**
 * 
 * Class : UserDaoImp
 * Create Date : 22/Sep/2019
 * 
 * @author Wahid Kamarullah
 *
 * Copyright(C) 2019, KamarullahLabs (Indonesia) Co., Ltd. All rights reserved.
 */
@Repository
public class UserDaoImp implements UserDao {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get User Authorities from Table Users
	 * 
	 * @param username String
	 * @return userRecord User
	 */
	@Override
	public User setAuthorities(String username) {

		User userRecord = null;
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User where username=:username ");
		query.setParameter("username", username);

		try {
			userRecord = query.getSingleResult();
		} catch (NoResultException nre) {
			logger.warn("NoResultException, message:" + nre);
		} catch (NonUniqueResultException nure) {
			logger.warn("NonUniqueResultException, message:" + nure);
		}

		return userRecord;
	}

}
