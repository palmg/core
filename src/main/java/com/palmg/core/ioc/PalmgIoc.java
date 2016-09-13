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
package com.palmg.core.ioc;

import com.palmg.core.ioc.impl.DefaultLocalSpringIocWrapper;

/**
 * <h3>IOC容器接口</h3>
 * 
 * @author chkui
 */
public interface PalmgIoc {

	public static PalmgIoc build(){
		return new DefaultLocalSpringIocWrapper();
	}
	
	Object getBean(String beanName);

	<T> T getBean(Class<T> beanType);

	<T> T getBean(String beanName, Class<T> beanType);

	<T> T addBean(Class<T> clazz);

	<T> T addBean(String beanName, Class<T> clazz);

	boolean containBean(String beanName);

	int containBean(Class<?> clazz);

	void destroyBean(Object instance);
}
