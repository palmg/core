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
package com.palmg.core.main;

import java.util.Objects;

import com.palmg.core.PalmgConfig;
import com.palmg.core.main.config.PropertiesSysConfig;
import com.palmg.core.main.config.SysConfig;
import com.palmg.utility.annotation.Fluently;

/**
 * 容器全局配置
 * 
 * @author chenkui
 */
public enum AaronConfigure {
	Instance;

	private PalmgConfig palmgConfig;// 设置全局配置
	
	private SysConfig sysConfig;

	private AaronConfigure() {
		synchronized (AaronConfigure.class) {
			if (null == palmgConfig) {
				palmgConfig = PalmgConfig.of();
				sysConfig = new PropertiesSysConfig();
			}
		}
	}

	/**
	 * 设置全局配置
	 * 
	 * @return
	 */
	public PalmgConfig getPalmgConfig() {
		return palmgConfig;
	}
	
	/**
	 * 获取系统配置参数
	 * @return
	 */
	public SysConfig getSysConfig(){
		return sysConfig;
	}

	@Fluently
	/**
	 * 设置全局配置
	 * 
	 * @param palmgConfig
	 *            全局配置参数
	 */
	public AaronConfigure setPalmgConfig(PalmgConfig palmgConfig) {
		Objects.requireNonNull(palmgConfig);
		this.palmgConfig = palmgConfig;
		return this;
	}
}