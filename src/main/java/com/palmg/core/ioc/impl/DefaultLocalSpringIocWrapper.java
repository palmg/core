/*
 * Copyright palmg(www.palmg.com)
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Apache License v2.0 which accompanies this distribution.
 * 
 *     The Apache License v2.0 is available at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * You may elect to redistribute this code under this licenses and copyright.
 * ------------------------------------------------------
 */
package com.palmg.core.ioc.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmg.core.config.def.DefaultConf;
import com.palmg.core.ioc.PalmgIoc;
import com.palmg.utility.properties.PropertiesLoadUtil;

public class DefaultLocalSpringIocWrapper implements PalmgIoc {

	private ApplicationContext springContext;
	
	public DefaultLocalSpringIocWrapper(final String springXmlPath){
		PropertiesLoadUtil.classPathLoad(DefaultConf.DEF_CONFIG_PROPERTIES_PATH);
		springContext = new ClassPathXmlApplicationContext(springXmlPath);
	}
	
	@Override
	public Object getBean(String beanName) {
		return springContext.getBean(beanName);
	}

	@Override
	public <T> T getBean(Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getBean(String beanName, Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T addBean(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T addBean(String beanName, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containBean(String beanName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int containBean(Class<?> clazz) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroyBean(Object instance) {
		// TODO Auto-generated method stub

	}

}
