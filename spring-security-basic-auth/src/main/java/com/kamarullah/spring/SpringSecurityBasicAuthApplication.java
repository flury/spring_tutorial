package com.kamarullah.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.kamarullah.spring.security.config.LoginApplicationConfig;

/**
 * 
 * Class : SpringSecurityBasicAuthApplication
 * Create Date : 22/Sep/2019
 * 
 * @author Wahid Kamarullah
 *
 * Copyright(C) 2019, KamarullahLabs (Indonesia) Co., Ltd. All rights reserved.
 */
public class SpringSecurityBasicAuthApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { LoginApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { LoginApplicationConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
