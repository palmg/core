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

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.main.config.DefaultConf;
import com.palmg.utility.properties.PropertiesLoadUtil;

/**
 * <h3>spring容器包裹类</h3>
 * @author chkui
 */
public class DefaultLocalSpringIocWrapper implements PalmgIoc {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultLocalSpringIocWrapper.class);

	private ApplicationContext springContext;

	public DefaultLocalSpringIocWrapper() {
		build(Optional.<String> empty());
	}

	public DefaultLocalSpringIocWrapper(final String springXmlPath) {
		build(Optional.of(springXmlPath));
	}

	private void build(final Optional<String> option) {
		final String defloadPath = Optional.of(PropertiesLoadUtil.classPathLoad(DefaultConf.DEF_CONFIG_PROPERTIES_PATH))// 读取properties
				.map(p -> p.getProperty("default.spring.loadPath"))// 读取配置路径加载字符串
				.get();

		if (option.isPresent()) {
			springContext = new ClassPathXmlApplicationContext(option.get(), defloadPath);
		} else {
			springContext = new ClassPathXmlApplicationContext(defloadPath);
		}
		LOG.debug("Init sping context success!");
	}

	@Override
	public Object getBean(String beanName) {
		return springContext.getBean(beanName);
	}

	@Override
	public <T> T getBean(Class<T> beanType) {
		return springContext.getBean(beanType);
	}

	@Override
	public <T> T getBean(String beanName, Class<T> beanType) {
		return springContext.getBean(beanName, beanType);
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
