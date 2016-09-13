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
package com.palmg.core.ioc.config;

import java.util.Objects;

/**
 * IOC容器相关的配置，为容器的初始化提供支持。 在palmg中，只要是服么JSR330规范的反向依赖注入机制都可以实现
 * 
 * @author chenkui
 *
 */
final public class IocConfig {
	public enum IocType {
		/**
		 * spring容器
		 */
		Spring,
		/**
		 * google guice容器
		 */
		Guice
	}

	// 容器类型
	private IocType iocType = IocType.Spring;

	// spring容器配置
	private SpringIocConfig springIocConfig;
	
	// guice容器配置
	private GuiceIocConfig guiceIocConfig;

	public IocConfig() {
		springIocConfig = new SpringIocConfig();
		
		guiceIocConfig = new GuiceIocConfig();
	}

	/**
	 * 获取Ioc容器类型,标记当前使用的容器
	 * 
	 * @return
	 */
	public IocType getIocType() {
		return iocType;
	}

	/**
	 * 设置当前IOC容器的使用类型
	 * 
	 * @param iocType
	 * @return {@link IocConfig}
	 */
	public IocConfig setIocType(IocType iocType) {
		//TODO 暂时未完成Guice功能
		//this.iocType = Objects.requireNonNull(iocType);
		return this;
	}

	/**
	 * 获取spring的IOC容器
	 * 
	 * @return {@link SpringIocConfig}
	 */
	public SpringIocConfig getSpringIocConfig() {
		return springIocConfig;
	}

	/**
	 * 设置spring的IOC容器配置
	 * 
	 * @param springIocConfig
	 */
	public void setSpringIocConfig(SpringIocConfig springIocConfig) {
		this.springIocConfig = Objects.requireNonNull(springIocConfig);
	}

	/**
	 * 获取
	 * @return
	 */
	public GuiceIocConfig getGuiceIocConfig() {
		return guiceIocConfig;
	}

	/**
	 * 
	 * @param guiceIocConfig
	 */
	public void setGuiceIocConfig(GuiceIocConfig guiceIocConfig) {
		this.guiceIocConfig = guiceIocConfig;
	}
}
