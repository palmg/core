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

import java.util.Objects;

import com.palmg.core.PalmgConfig;
import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.ioc.config.IocConfig;
import com.palmg.utility.annotation.Fluently;

/**
 * PalmgConfig接口的实现类。用于在运行时创建所有配置对象
 * @author chkui
 */
public class DefRuntimeConfig implements PalmgConfig {
	// 集群配置
	private ClusterConfig clusterConfig;

	// 交互总线配置
	private BusConfig busConfig;

	// 容器配置
	private IocConfig iocConfig;

	public DefRuntimeConfig() {
		iocConfig = new IocConfig();// 创建容器配置

		busConfig = new BusConfig();// 创建总线配置

		clusterConfig = new ClusterConfig();// 创建集群配置
	}

	// 获取集群配置
	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}

	@Fluently
	// 设置集群配置
	public PalmgConfig setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = Objects.requireNonNull(clusterConfig);
		return this;
	}

	public BusConfig getBusConfig() {
		return busConfig;
	}

	@Fluently
	public PalmgConfig setBusConfig(BusConfig busConfig) {
		this.busConfig = Objects.requireNonNull(busConfig);
		return this;
	}

	public IocConfig getIocConfig() {
		return iocConfig;
	}

	public PalmgConfig setIocConfig(IocConfig iocConfig) {
		this.iocConfig = Objects.requireNonNull(iocConfig);
		return this;
	}
}
