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

import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.cluster.config.ClusterConfig;

/**
 * palmg启动配置
 * 
 * @author chkui
 */
public class PalmgConfig {
	// 集群配置
	private ClusterConfig clusterConfig;

	// 交互总线配置
	private BusConfig busConfig;

	/**
	 * 获取集群配置
	 * @return
	 */
	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}

	/**
	 * 替换集群配置
	 * @param clusterConfig
	 * @return
	 */
	public PalmgConfig setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = clusterConfig;
		return this;
	}

	/**
	 * 获取总线配置
	 * @return
	 */
	public BusConfig getBusConfig() {
		return busConfig;
	}

	/**
	 * 替换传输总线配置
	 * @param busConfig
	 * @return
	 */
	public PalmgConfig setBusConfig(BusConfig busConfig) {
		this.busConfig = busConfig;
		return this;
	}
}
