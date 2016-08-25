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
package com.palmg.core.schdule;

/**
 * <p>设置Palmg定时任务的接口</p>
 * 
 * @author chkui
 */
public interface PalmgCron {

	/**
	 * 执行定时执行
	 * 
	 * @return
	 */
	PalmgCron periodic();

	/**
	 * 延迟执行
	 * 
	 * @return
	 */
	PalmgCron timer();
}
