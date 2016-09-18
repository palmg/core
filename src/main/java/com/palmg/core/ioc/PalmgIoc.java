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

/**
 * <h3>IOC容器接口</h3>
 * <p>
 * IOC容器接口提供了容器操作的通用功能。
 * 
 * </p>
 * @author chkui
 */
public interface PalmgIoc {

	Object getBean(String beanName);

	<T> T getBean(Class<T> beanType);

	<T> T getBean(String beanName, Class<T> beanType);

	<T> T addBean(Class<T> clazz);

	<T> T addBean(String beanName, Class<T> clazz);

	boolean containBean(String beanName);

	int containBean(Class<?> clazz);

	void destroyBean(Object instance);
}
