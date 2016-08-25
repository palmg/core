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
package com.palmg.core.main;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.bus.consumer.PalmgConsumer;
import com.palmg.core.bus.publisher.PalmgPulisher;
import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.schdule.PalmgCron;

/**
 * palmg工具组件
 * @author chkui
 */
public interface Toolkit {

	PalmgConsumer getConsumer();

	PalmgPulisher getPulisher();

	PalmgCluster getCluster();

	PalmgIoc getIoc();

	PalmgCron getCron();
}
