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
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.main.AaronConfigure;

/**
 * <h3>spring容器包裹类</h3>
 * 
 * @author chkui
 */
public enum DefaultLocalSpringIocWrapper implements PalmgIoc {
	Instance;
	private static final Logger LOG = LoggerFactory.getLogger(DefaultLocalSpringIocWrapper.class);

	private ApplicationContext springContext;

	// 用于标记是否已经开始初始化
	private boolean hasInit = false;

	public PalmgIoc build() {
		build(Optional.<String>empty());
		return this;
	}

	public PalmgIoc build(final String springXmlPath) {
		build(Optional.of(springXmlPath));
		return this;
	}

	private void build(final Optional<String> option) {
		if (!hasInit) {
			synchronized (this) {
				if (!hasInit) {
					hasInit = true;
					final String defloadPath = AaronConfigure.Instance.getSysConfig().getSpringXmlPath();
					if (option.isPresent()) {
						springContext = new ClassPathXmlApplicationContext(option.get(), defloadPath);
					} else {
						springContext = new ClassPathXmlApplicationContext(defloadPath);
					}
					LOG.debug("Init sping context success!");
				}
			}
		}
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
		return addBean(clazz.getName(), clazz);
	}

	@Override
	public <T> T addBean(String beanName, Class<T> clazz) {
		//使用BeanFactory构建一个新增bean的方法
		DefaultListableBeanFactory beanFactory = getBeanFactory();
		if (!beanFactory.containsBean(beanName)) {
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(clazz);
			builder.setScope("singleton");
			beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
		}
		return springContext.getBean(beanName, clazz);
	}

	@Override
	public boolean containBean(String beanName) {
		return springContext.containsBean(beanName);
	}

	@Override
	public int containBean(Class<?> clazz) {
		return Optional.ofNullable(springContext.getBeanNamesForType(clazz)).map(list -> list.length).orElse(0);
	}

	@Override
	public void destroyBean(Object instance) {
		DefaultListableBeanFactory beanFactory = getBeanFactory();
		beanFactory.destroyBean(instance);
	}

	//将当年前的springContext转换成BeanFactory的实现类
	private DefaultListableBeanFactory getBeanFactory() {
		return DefaultListableBeanFactory.class.cast(ConfigurableApplicationContext.class.cast(springContext));
	}
}
