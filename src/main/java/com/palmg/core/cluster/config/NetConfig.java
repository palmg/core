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
 * 网络配置接口配置
 * 
 * @author chkui
 */
abstract public class NetConfig {

	/**
	 * 默认集群心跳频率：{@value}
	 */
	public static long PING_INTERVAL = 20000;

	/**
	 * 默认集群心跳回复等待时间：{@value}
	 */
	public static long PINT_REPLY_INTERVAL = 20000;

	/**
	 * 默认集群初始端口：{@value}
	 */
	public static int _PORT = 5700;

	/**
	 * 默认端口增量数：{@value}
	 */
	public static int PORT_COUNT = 100;

	/**
	 * 标记当前组建集群的网络协议
	 * 
	 * @author chenkui
	 *
	 */
	public enum NetType {
		/**
		 * tcp/ip组网协议{@value}
		 */
		TcpIp, // tcpip协议组网
		/**
		 * 组播协议{@value}
		 */
		Multicast// 组播协议组网
	}

	// 集群心跳间隔，单位ms
	private long pingInterval = PING_INTERVAL;

	// 心跳回复间隔，单位ms
	private long pingReplyInterval = PINT_REPLY_INTERVAL;

	// 本地组网端口
	private int port = _PORT;

	// 本地组网端口的可增长数
	private int portCount = PORT_COUNT;

	// 是否开启本地组网端口自动增长
	private boolean portIncrement = true;

	// 端口重用标记
	private boolean reusePort = false;

	// 公网地址
	private String publicAddress;

	// 对外接口
	private int[] interfaces;

	/**
	 * 获取网络连接类型
	 * 
	 * @return {@link NetWorkType}
	 */
	public abstract NetType getNetType();

	/**
	 * 获取集群节点之间的心跳间隔时间，单位ms。
	 * 
	 * @return 心跳频率
	 */
	public long getPingInterval() {
		return pingInterval;
	}

	@Fluently
	/**
	 * 设置集群节点之间的心跳间隔时间，单位ms
	 * 
	 * @param pingInterval
	 *            心跳间隔时间
	 * @return this
	 */
	public NetConfig setPingInterval(final long pingInterval) {
		if (1 > pingInterval) {
			throw new IllegalArgumentException("pingInterval must be greater than 0");
		}
		this.pingInterval = pingInterval;
		return this;
	}

	/**
	 * 等待集群节点回复时间,如果回复的时间超过这个设置，则认为节点已经失去功能。
	 * 
	 * @return 心跳等待时间 long
	 */
	public long getPingReplyInterval() {
		return pingReplyInterval;
	}

	@Fluently
	/**
	 * 设置集群心跳回复等待时间，单位ms
	 * 
	 * @param pingReplyInterval
	 *            等待心跳回复的等待时间，ms
	 * @return this
	 */
	public NetConfig setPingReplyInterval(final long pingReplyInterval) {
		if (1 > pingInterval) {
			throw new IllegalArgumentException("pingReplyInterval must be greater than 0");
		}
		this.pingReplyInterval = pingReplyInterval;
		return this;
	}

	/**
	 * 获取集群对外连接的端口
	 * 
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	@Fluently
	/**
	 * 设置集群的对外端口。这个端口是集群之间用于相互发现的端口（握手）。当握手成功以后，会使用websocket通信。
	 * 当portCount设置大于1并且portIncrement设置为true时，集群会在指定的端口范围内查找可用端口（范围port +
	 * portCount）。
	 * 
	 * @param port
	 *            要设置的端口值
	 */
	public NetConfig setPort(final int port) {
		if (1 > port) {
			throw new IllegalArgumentException("port must be greater than 0");
		}
		this.port = port;
		return this;
	}

	/**
	 * 获取端口的增量。
	 * 
	 * @return
	 */
	public int getPortCount() {
		return portCount;
	}

	@Fluently
	/**
	 * 设置端口增量
	 * 
	 * @param portCount
	 */
	public NetConfig setPortCount(final int portCount) {
		if (0 > portCount) {
			throw new IllegalArgumentException("portCount must be greater than -1");
		}
		this.portCount = portCount;
		return this;
	}

	/**
	 * 获取端口增量
	 * 
	 * @return
	 */
	public boolean isPortIncrement() {
		return portIncrement;
	}

	/**
	 * 设置是否开启网络端口增量
	 * 
	 * @param portIncrement
	 *            增量大小，系统会在 port + portCount的范围内寻找为被使用的端口
	 * @return
	 */
	public NetConfig setPortIncrement(final boolean portIncrement) {
		this.portIncrement = portIncrement;
		return this;
	}

	/**
	 * 获取端口是否可可复用
	 * 
	 * @return
	 */
	public boolean isReusePort() {
		return reusePort;
	}

	/**
	 * 设置端口可复用[true|false]。
	 * 端口复用是指当一个集群节点启动并停止之后会占用一个端口，此时将端口复用设置为true可以让集群从端口的开始位置重新扫描可用端口。
	 * 
	 * @param reusePort
	 */
	public NetConfig setReusePort(final boolean reusePort) {
		this.reusePort = reusePort;
		return this;
	}

	@Nullable
	/**
	 * 设置公网地址，默认值为null
	 * 
	 * @return
	 */
	public String getPublicAddress() {
		return publicAddress;
	}

	/**
	 * 设置公网地址
	 * 公网地址是指当当某个服务器使用NAT代理时，外网是无法直接使用真实IP地址访问到该服务器的，通过公网地址设置可以让外部访问这个服务器。
	 * 
	 * @param publicAddress
	 */
	public void setPublicAddress(final String publicAddress) {
		this.publicAddress = Objects.requireNonNull(publicAddress);
	}

	@Nullable
	/**
	 * 获取对外端口，默认值为null
	 * 
	 * @return
	 */
	public int[] getInterfaces() {
		return interfaces;
	}

	/**
	 * 设置对外端口。
	 * <p>
	 * 指定集群节点使用的网络接口地址。一些服务器可能有多个网络接口（多个网卡），因此可能需要限定可用的IP地址。 范围字符('*' and
	 * '-')可以用于多个地址，例如
	 * 10.3.10.*是指从10.3.10.0到10.3.10.255的端口均可使用，又例如：10.3.10.4-18是指从10.3.10.4到10.3.10.18的IP地址（包含4和18）。
	 * 在启用网络接口配置后如果系统找不到配置的IP地址，将会输出一个异常信息，并停止启动应用节点。
	 * </p>
	 * 
	 * @param interfaces
	 */
	public NetConfig setInterfaces(int... interfaces) {
		this.interfaces = Objects.requireNonNull(interfaces);
		return this;
	};
}
