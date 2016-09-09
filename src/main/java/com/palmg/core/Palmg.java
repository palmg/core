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

import java.util.Objects;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.bus.consumer.PalmgConsumer;
import com.palmg.core.bus.publisher.PalmgPulisher;
import com.palmg.core.cron.PalmgCron;
import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.main.AaronConfigure;
import com.palmg.core.main.config.PalmgConfig;
import com.palmg.core.main.impl.DefaultToolkitImpl;

/**
 * Palmg全局toolkit
 * 
 * @author chkui
 */
public interface Palmg{

	public static Palmg build() {
		AaronConfigure config = AaronConfigure.Instance;
		config.setCluster(false);// no cluster
		return buildConfig(false, null, null);
	}

	public static Palmg build(final String... springXmlPath) {
		Objects.requireNonNull(springXmlPath);
		return buildConfig(false, springXmlPath, null);
	}

	public static Palmg buildCluster(final String... springXmlPath) {
		// TODO
		return null;
	}

	public static Palmg build(final PalmgConfig config) {
		// TODO
		return null;
	}
	
	static Palmg buildConfig(boolean isCluster, String[] springXmlPaths, PalmgConfig palmgConfig){
		AaronConfigure config = AaronConfigure.Instance;
		config.setCluster(isCluster);// no cluster
		config.setSpringXmlPath(springXmlPaths);// 设置spring配置
		
		return DefaultToolkitImpl.Instance;
	}

	PalmgConsumer getConsumer();

	PalmgPulisher getPulisher();

	PalmgCluster getCluster();

	PalmgIoc getIoc();

	PalmgCron getCron();
}
