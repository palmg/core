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
package com.palmg.core;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.bus.consumer.PalmgConsumer;
import com.palmg.core.bus.publisher.PalmgPulisher;
import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.main.AaronConfigure;
import com.palmg.core.main.impl.DefaultToolkitImpl;
import com.palmg.core.util.PalmgUtil;

/**
 * <h3>Palmg组件工具</h3>
 * <p>
 * Palmg是一个轻量级、容器化、夸JVM、夸服务器的分布式集群交互工具。
 * Palmg不依托任何外部框架来运行，可以运行在tomcat、jetty、weblogic等常见容器中，甚至是一个简单的main程序都可以快速启动他复杂的集群功能。因此，你可以把他嵌入到任何系统中去使用。
 * Palmg的集群通信支持"发布/订阅"、"request/response"以及"消息广播模式"。只要简单的注册一个Consumer就可以接收集群中所有节点发送给他的所有消息。
 * </p>
 * <p>
 * Palmg默认使用spring容器来管理实力。如果你的项目也使用了spring或想要使用spring，可以平滑的都使用spring来管理各种实例。此外，Palmg还提供了Guice作为容器。
 * </p>
 * <p>
 * {@link Palmg}接口提供了获取{@link PalmgConsumer}、{@link PalmgPulisher}、{@link PalmgIoc}、{@link PalmgCron}的接口。
 * {@link PalmgConsumer}用于管理"消费者"，提供注册、移除、修改等功能
 * {@link PalmgPulisher}用于发布、广播、推送消息。
 * {@link PalmgIoc}提供了Ioc容器相关的功能。如果你是整合了Palmg的容器一起使用，这个接口能为你提供极大的帮助。
 * {@link PalmgCron}提供了一些常用基础功能，比如设定一个定时器、设定一个延迟器、用独立线程执行一个任务等。
 * </p>
 * 
 * @author chkui
 */
public interface Palmg {

	/**
	 * 构建一个默认Palmg
	 * @return {@link Palmg}
	 */
	public static Palmg build() {
		return buildConfig(false, null);
	}

	public static Palmg build(final PalmgConfig config) {
		return buildConfig(true, config);
	}

	static Palmg buildConfig(boolean isCluster, PalmgConfig palmgConfig) {
		AaronConfigure config = AaronConfigure.Instance;
		if (null != palmgConfig) {
			config.setPalmgConfig(palmgConfig);
		}
		return DefaultToolkitImpl.Instance;
	}

	/**
	 * <p>
	 * 获取消费者工具接口{@link PalmgConsumer}。
	 * </p>
	 * 
	 * @return{@link PalmgConsumer}
	 */
	PalmgConsumer getConsumer();

	/**
	 * 获取{@link PalmgPulisher}。
	 * 
	 * @return
	 */
	PalmgPulisher getPulisher();

	/**
	 * <p>
	 * 获取集群管理工具接口{@link PalmgCluster}
	 * </p>
	 * @return
	 */
	PalmgCluster getCluster();

	/**
	 * 获取IOC容器管理工具接口{@link PalmgIoc}
	 * 
	 * @return
	 */
	PalmgIoc getIoc();

	/**
	 * 获取Palmg常规工具组件管理接口{@link PalmgUtil}
	 * 
	 * @return
	 */
	PalmgUtil getUtil();
}
