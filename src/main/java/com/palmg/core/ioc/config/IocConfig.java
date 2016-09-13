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

import java.util.Arrays;
import java.util.List;

import com.palmg.utility.annotation.Fluently;

/**
 * IOC容器相关的配置，为容器的初始化提供支持
 * @author chenkui
 *
 */
public class IocConfig {
	
	private List<String> springXmlPaths;//spring的加载路径
	
	public IocConfig(){
		this.springXmlPaths = Arrays.asList("configs/ioc/default/ioc-default.xml");
	}

	/**
	 * 获取当前spring的启动路径
	 * @return
	 */
	public List<String> getSpringXmlPaths() {
		return springXmlPaths;
	}

	@Fluently
	/**
	 * 增加spring的扫描路径
	 * @param springXmlPaths 要添加的路径
	 * @return this
	 */
	public IocConfig addSpringXmlPaths(String... springXmlPaths){
		this.springXmlPaths.addAll(Arrays.asList(springXmlPaths));
		return this;
	}
	
	@Fluently
	/**
	 * 设置当前spring的启动路径
	 * @param springXmlPaths 要设置的路径
	 * @return this
	 */
	public IocConfig setSpringXmlPaths(List<String> springXmlPaths) {
		this.springXmlPaths = springXmlPaths;
		return this;
	}
}
