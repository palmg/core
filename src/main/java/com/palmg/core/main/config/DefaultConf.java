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
	 * 全局默认propertis文件classpath路径:
	 * {@value}
	 */
	final public static String DEF_CONFIG_PROPERTIES_PATH = "configs/default_conf.properties";
	
	/**
	 * 标识未定义字符串:{@value}
	 */
	final public static String DEF_TAG_STRING_NULL= "NaNEOF";
	
	/**
	 * 未定义数字
	 */
	final public static int DEF_TAG_NUMBER_NULL = -1;
}
