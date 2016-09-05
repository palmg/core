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

import com.palmg.core.cluster.config.NetWorkConfig.NetWorkType;

/**
 * TCP/IP组网配置
 * 
 * @author chkui
 */
public class TcpIpNetWorkConfig {
	// 组网类型
	private NetWorkType networkType = NetWorkType.TcpIp;

	// 当前节点加入集群之前优先启动的Ip
	private String[] requireIp;

	// 当前集群节点要连接的Ip地址
	private String[] connectIp;

	// 当前连接超时时间
	private int connectTimeOut;

	// 公网地址
	private String publicAddress;

	// 本地组网端口
	private int port;

	// 本地组网端口的可增长数
	private int portCount;

	// 是否开启本地组网端口自动增长
	private boolean portIncrement;

	// 套接字临时端口
	private int[] socketPorts;

	// 端口重用标记
	private boolean reusePort;

	// 自定义对外端口 -1表示不启用
	private int outPorts;

	public NetWorkType getNetworkType() {
		return networkType;
	}

	public String[] getRequireIp() {
		return requireIp;
	}

	public TcpIpNetWorkConfig setRequireIp(String... requireIp) {
		this.requireIp = requireIp;
		return this;
	}

	public String[] getConnectIp() {
		return connectIp;
	}

	public TcpIpNetWorkConfig setConnectIp(String... connectIp) {
		this.connectIp = connectIp;
		return this;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public TcpIpNetWorkConfig setConnectTimeOut(int connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
		return this;
	}

	public String getPublicAddress() {
		return publicAddress;
	}

	public TcpIpNetWorkConfig setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
		return this;
	}

	public int getPort() {
		return port;
	}

	public TcpIpNetWorkConfig setPort(int port) {
		this.port = port;
		return this;
	}

	public int getPortCount() {
		return portCount;
	}

	public TcpIpNetWorkConfig setPortCount(int portCount) {
		this.portCount = portCount;
		return this;
	}

	public boolean isPortIncrement() {
		return portIncrement;
	}

	public TcpIpNetWorkConfig setPortIncrement(boolean portIncrement) {
		this.portIncrement = portIncrement;
		return this;
	}

	public int[] getSocketPorts() {
		return socketPorts;
	}

	public TcpIpNetWorkConfig setSocketPorts(int... socketPorts) {
		this.socketPorts = socketPorts;
		return this;
	}

	public boolean isReusePort() {
		return reusePort;
	}

	public TcpIpNetWorkConfig setReusePort(boolean reusePort) {
		this.reusePort = reusePort;
		return this;
	}

	public int getOutPorts() {
		return outPorts;
	}

	public TcpIpNetWorkConfig setOutPorts(int outPorts) {
		this.outPorts = outPorts;
		return this;
	}
}
