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
package com.palmg.core.bus.config;

import com.palmg.core.bus.enums.BusRunType;

/**
 * <h3>数据传输总线相关配置</h3>
 * <p>
 * 该配置用于设置数据传递总线的相关参数
 * </p>
 * <h4>全局环境设置</h4>
 * <p>
 * 我们已经根据开发环境和生产环境进行了预设，当然你也可以根据需要修改所有参数。通过setBusRunType方法来设置运行环境：[Developer|Product]
 * </p>
 * <p>
 * 当设置为开发环境时，所有的监控设置和等待设置都会设置为非常大的值。而设置为生成环境时，所有的监控和等待时间都会大幅缩短。
 * </p>
 * 
 * @author chkui
 */
public class BusConfig {

	/**
	 * 设置开发环境默认的线程检查阻塞时间{@value}
	 */
	final public static int DEV_CORE_THREAD_BLOCK_CHECK_INTERVAL = 999999999;

	/**
	 * 设置在开发环境默认核心线程轮训检查时间{@value}
	 */
	final public static int DEV_MAX_EVENT_LOOP_EXECUTETIME = 999999999;

	/**
	 * 设置在开发环境警告时间{@value}
	 */
	final public static long DEV_WARNUBG_EXCEPTION_TIME = 999999999999999999L;

	// 环境运行类型
	private BusRunType busRunType = BusRunType.Developer;

	// 核心线程个数 eventLoopPoolSize。默认为CPU核心数×2
	private int coreThreadPoolSize = 2 * Runtime.getRuntime().availableProcessors();

	// 工作线程个数 WorkerPoolSize
	private int workerThreadPoolSize = 20;

	// 代码异步执行的阻塞线程，用于调度内部总线执行情况
	private int internalBlockingPoolSize = 20;

	// 核心线程阻塞检查周期。系统会根据设定的时间周期性检测某个coreThread线程是否执行了太久的时间，单位ms。
	// blockedThreadCheckInterval
	private int coreThreadBlockedCheckInterval = DEV_CORE_THREAD_BLOCK_CHECK_INTERVAL;

	// 检查核心线程的等待返回时间，如果在这个指定的时间核心线程没有任何响应，则会输出一个警告日志，单位ms。用于检查一个核心线程是否被阻塞。 work
	// maxEventLoopExecuteTime
	private int maxCoreThreadExecuteTime = DEV_MAX_EVENT_LOOP_EXECUTETIME;

	// 检查工作线程的等待返回时间，效果类似maxCoreThreadExecuteTime 。maxWorkerExecuteTime
	private long maxWorkerThreadExecuteTime = 60L * 1000 * 1000000;

	// 异常日志的输出阀值时间，如果阻塞超过这个时间，则输出异常日志
	private long warningExceptionTime = DEV_WARNUBG_EXCEPTION_TIME;

	public BusRunType getBusRunType() {
		return busRunType;
	}

	public BusConfig setBusRunType(BusRunType busRunType) {
		this.busRunType = busRunType;
		return this;
	}

	public int getCoreThreadPoolSize() {
		return coreThreadPoolSize;
	}

	public BusConfig setCoreThreadPoolSize(int coreThreadPoolSize) {
		this.coreThreadPoolSize = coreThreadPoolSize;
		return this;
	}

	public int getWorkerThreadPoolSize() {
		return workerThreadPoolSize;
	}

	public BusConfig setWorkerThreadPoolSize(int workerThreadPoolSize) {
		this.workerThreadPoolSize = workerThreadPoolSize;
		return this;
	}

	public int getInternalBlockingPoolSize() {
		return internalBlockingPoolSize;
	}

	public BusConfig setInternalBlockingPoolSize(int internalBlockingPoolSize) {
		this.internalBlockingPoolSize = internalBlockingPoolSize;
		return this;
	}

	public int getCoreThreadBlockedCheckInterval() {
		return coreThreadBlockedCheckInterval;
	}

	public BusConfig setCoreThreadBlockedCheckInterval(int coreThreadBlockedCheckInterval) {
		this.coreThreadBlockedCheckInterval = coreThreadBlockedCheckInterval;
		return this;
	}

	public int getMaxCoreThreadExecuteTime() {
		return maxCoreThreadExecuteTime;
	}

	public void setMaxCoreThreadExecuteTime(int maxCoreThreadExecuteTime) {
		this.maxCoreThreadExecuteTime = maxCoreThreadExecuteTime;
	}

	public long getMaxWorkerThreadExecuteTime() {
		return maxWorkerThreadExecuteTime;
	}

	public void setMaxWorkerThreadExecuteTime(long maxWorkerThreadExecuteTime) {
		this.maxWorkerThreadExecuteTime = maxWorkerThreadExecuteTime;
	}

	public long getWarningExceptionTime() {
		return warningExceptionTime;
	}

	public void setWarningExceptionTime(long warningExceptionTime) {
		this.warningExceptionTime = warningExceptionTime;
	}
}