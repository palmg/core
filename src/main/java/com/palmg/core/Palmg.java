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
import com.palmg.core.main.config.PalmgConfig;
import com.palmg.core.main.impl.DefaultToolkitImpl;
import com.palmg.core.schdule.PalmgCron;

/**
 * Palmg全局toolkit
 * 
 * @author chkui
 */
public interface Palmg{

	public static Palmg build() {
		return DefaultToolkitImpl.Instance;
	}

	public static Palmg build(final String springXmlPath) {
		// TODO
		return null;
	}

	public static Palmg build(final PalmgConfig config) {
		// TODO
		return null;
	}

	PalmgConsumer getConsumer();

	PalmgPulisher getPulisher();

	PalmgCluster getCluster();

	PalmgIoc getIoc();

	PalmgCron getCron();
}
