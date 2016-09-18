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
package com.palmg.core.cluster.config;

import java.util.Objects;

import com.palmg.utility.annotation.Fluently;

/**
 * 集群相关配置
 * 
 * @author chkui
 */
public class ClusterConfig {
	
	public static final String GROUP_NAME = "palmg";
	
	private boolean enabled = false;// 集群启停标识

	private NetConfig netConfig; // 组网相关配置
	
	private String groupName = GROUP_NAME; // 分组名称

	public ClusterConfig() {
		netConfig = new NetConfig();// 默认使用TCP/IP连接
	}

	/**
	 * 判断是否启用集群，默认值为false
	 * 
	 * @return [true|false] 启用|停用
	 */
	public boolean isEnabled() {
		return enabled;
	}

	@Fluently
	/**
	 * 设置是否启用集群
	 * 
	 * @param enabled
	 *            标识是否启用集群 [true|fasle]
	 * @return {@link ClusterConfig}
	 */
	public ClusterConfig setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	/**
	 * 获取集群网络相关配置
	 * 
	 * @return {@link NetWorkConfig}
	 */
	public NetConfig getNetConfig() {
		return netConfig;
	}

	@Fluently
	/**
	 * 设置集群网络相关配置
	 * 
	 * @param {@link
	 * 			NetWorkConfig}
	 * @return {@link ClusterConfig}
	 */
	public ClusterConfig setNetConfig(NetConfig netConfig) {
		this.netConfig = Objects.requireNonNull(netConfig);
		return this;
	}

	/**
	 * 获取集群分组名称
	 * @return
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * 设置集群分组名称
	 * <p>
	 * 集群分组用于隔离集群。不同组的集群节点不能相互发现
	 * </p>
	 * @param groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
