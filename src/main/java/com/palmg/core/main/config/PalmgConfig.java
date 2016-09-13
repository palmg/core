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

import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.ioc.config.IocConfig;
import com.palmg.utility.annotation.Fluently;

/**
 * <h3>palmg配置对象</h3>
 * <p>
 * PalmgConfig中包含了所有的运行相关配置。其中包含集群配置、交互事件配置、容器配置。
 * </p>
 * 
 * @author chkui
 */
public class PalmgConfig {
	// 集群配置
	private ClusterConfig clusterConfig;

	// 交互总线配置
	private BusConfig busConfig;

	// 容器配置
	private IocConfig iocConfig;

	public PalmgConfig() {
		iocConfig = new IocConfig();// 创建容器配置

		busConfig = new BusConfig();// 创建总线配置

		clusterConfig = new ClusterConfig();// 创建集群配置
	}

	/**
	 * 获取集群配置
	 * 
	 * @return {@link ClusterConfig}
	 */
	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}

	@Fluently
	/**
	 * 替换集群配置
	 * 
	 * @param {@link ClusterConfig}
	 * @return {@link PalmgConfig}
	 */
	public PalmgConfig setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = Objects.requireNonNull(clusterConfig);
		return this;
	}

	/**
	 * 获取总线配置
	 * 
	 * @return {@link BusConfig}
	 */
	public BusConfig getBusConfig() {
		return busConfig;
	}

	@Fluently
	/**
	 * 替换传输总线配置
	 * 
	 * @param {@link BusConfig}
	 * @return {@link PalmgConfig}
	 */
	public PalmgConfig setBusConfig(BusConfig busConfig) {
		this.busConfig = Objects.requireNonNull(busConfig);
		return this;
	}

	/**
	 * 获取容器相关配置
	 * 
	 * @return {@link IocConfig}
	 */
	public IocConfig getIocConfig() {
		return iocConfig;
	}

	@Fluently
	/**
	 * 设置容器相关配置
	 * 
	 * @param {@link IocConfig}
	 *            
	 */
	public PalmgConfig setIocConfig(IocConfig iocConfig) {
		this.iocConfig = Objects.requireNonNull(iocConfig);
		return this;
	}
}
