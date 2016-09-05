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

import com.palmg.utility.annotation.Fluently;

/**
 * 网络配置接口配置
 * 
 * @author chkui
 */
abstract public class NetWorkConfig {
	public enum NetWorkType {
		TcpIp, // tcpip协议组网
		Multicast// 组播协议组网
	}

	// 集群心跳间隔，单位ms
	private long pingInterval;

	// 心跳回复间隔，单位ms
	private long pingReplyInterval;

	/**
	 * 获取集群节点之间的心跳间隔时间，单位ms
	 * @return 心跳频率
	 */
	public long getPingInterval() {
		return pingInterval;
	}

	/**
	 * 设置集群节点之间的心跳间隔时间，单位ms
	 * @param pingInterval 心跳间隔时间
	 * @return this
	 */
	public @Fluently NetWorkConfig setPingInterval(final long pingInterval) {
		if(1 > pingInterval){
			throw new IllegalArgumentException("pingInterval must be greater than 0");
		}
		this.pingInterval = pingInterval;
		return this;
	}

	/**
	 * 等待集群节点回复时间,如果回复的时间超过这个设置，则认为节点已经失去功能。
	 * @return 心跳等待时间 long
	 */
	public long getPingReplyInterval() {
		return pingReplyInterval;
	}

	/**
	 * 设置集群心跳回复等待时间，单位ms
	 * @param pingReplyInterval 等待心跳回复的等待时间，ms
	 * @return this
	 */
	public @Fluently NetWorkConfig setPingReplyInterval(long pingReplyInterval) {
		if(1 > pingInterval){
			throw new IllegalArgumentException("pingReplyInterval must be greater than 0");
		}
		this.pingReplyInterval = pingReplyInterval;
		return this;
	};
}
