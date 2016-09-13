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
package com.palmg.core.util;

/**
 * <p>Palmg的工具组件</p>
 * 
 * @author chkui
 */
public interface PalmgUtil {

	/**
	 * 根据指定的时间周期定期执行一项任务
	 * 
	 * @return
	 */
	PalmgUtil periodic();

	/**
	 * 根据制定的时间延迟执行一项任务
	 * 
	 * @return
	 */
	PalmgUtil timer();
	
	/**
	 * 执行一个独立线程，该线程会使用workPool中的线程来执行
	 * @return
	 */
	PalmgUtil workThread();
}
