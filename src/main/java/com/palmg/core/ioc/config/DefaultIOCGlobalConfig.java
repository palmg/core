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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.palmg.core.main.AaronConfigure;

@Configuration
/**
 * spring的注入容器，用于支配全局配置。
 * @author chenkui
 */
public class DefaultIOCGlobalConfig {
	
	/**
	 * 获取容器相关配置
	 * @return
	 */
	public @Bean IocConfig getIocConfig(){
		return AaronConfigure.Instance.getPalmgConfig().getIocConfig();
	}
}
