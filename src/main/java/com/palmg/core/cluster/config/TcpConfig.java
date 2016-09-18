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
import com.palmg.utility.annotation.Nullable;

/**
 * TCP/IP组网配置
 * 
 * @author chkui
 */
public class TcpConfig {
	/**
	 * 默认TCP/IP探查连接超时时间：{@value}
	 */
	public static final int CONNECT_TIMEOUT = 5;

	/**
	 * 默认连接节点：{@value}
	 */
	public static final String[] CONNECT_NODE = { "localhost" };

	// 当前节点加入集群之前优先启动的节点
	private String[] requireNode;

	// 当前集群节点要连接的节点
	private String[] connectNode = CONNECT_NODE;

	// 当前连接超时时间
	private int connectTimout = CONNECT_TIMEOUT;

	/**
	 * 获取组建集群的前置IP
	 * 
	 * @return
	 */
	public @Nullable String[] getRequireNode() {
		return requireNode;
	}

	/**	 * 设置组建集群的前置IP
	 * 
	 * @param requireIp
	 *            IP列表
	 * @return
	 */
	public @Fluently TcpConfig setRequireNode(String... requireNode) {
		this.requireNode = Objects.requireNonNull(requireNode);
		return this;
	}

	/**
	 * 获取当前连接节点
	 * 
	 * @return
	 */
	public String[] getConnectNode() {
		return connectNode;
	}

	/**
	 * 设置要连接的节点服务器IP地址。
	 * 
	 * @param connectIp
	 *            连接的IP清单
	 * @return
	 */
	public @Fluently TcpConfig setConnectNode(String... connectNode) {
		this.connectNode = Objects.requireNonNull(connectNode);
		return this;
	}

	/**
	 * 获取连接超时时间
	 * 
	 * @return
	 */
	public int getConnectTimeOut() {
		return connectTimout;
	}

	/**
	 * 设置连接超时时间
	 * 
	 * @param connectTimout
	 *            连接超时时间，单位MS。
	 * @return
	 */
	public @Fluently TcpConfig setConnectTimout(int connectTimout) {
		if (1 > connectTimout) {
			throw new IllegalArgumentException("portCount must be greater than 1");
		}
		this.connectTimout = connectTimout;
		return this;
	}
}
