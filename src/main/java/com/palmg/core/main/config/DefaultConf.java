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
package com.palmg.core.main.config;

/**
 * 全局默认配置
 * @author chkui
 */
public class DefaultConf {
	
	/**
	 * 标识未定义字符串:{@value}
	 */
	final public static String STRING_NULL= "NaNEOF";
	/**
	 * 标识全局通用的逗号:{@value}
	 */
	final public static String STRING_COMMA = ",";
	
	/**
	 * 未定义数字:{@value}
	 */
	final public static int NUMBER_NULL = -1;
	
	/**
	 * ioc容器的配置名称{@value}
	 */
	final public static String CONFIG_NAME_IOC = "default-ioc-config";

	/**
	 * bus在容器中的配置名称{@value}
	 */
	final public static String CONFIG_NAME_BUS = "default-bus-config";
	
	/**
	 * cluster在容器中的配置名称{@value}
	 */
	final public static String CONFIG_NAME_CLUSTER = "default-cluster-config";
}
