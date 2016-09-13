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

import java.util.Objects;

import com.palmg.core.bus.enums.BusRunType;
import com.palmg.utility.annotation.Fluently;

/**
 * <h3>数据传输总线相关配置</h3>
 * <p>
 * 该配置用于设置数据传递总线的相关参数。
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
	 * 核心线程个数，默认为当前CPU核心的×2
	 */
	public static final int CORE_THREAD_POOL_SIZE = 2 * Runtime.getRuntime().availableProcessors();

	/**
	 * 工作线程的个数：{@value}
	 */
	public static final int WORKER_THREAD_POOL_SIZE = 20;

	/**
	 * 默认内部核心工作线程个数
	 */
	public static final int INTERNAL_BLOCKING_POOL_SIZE = 20;

	/**
	 * 设置开发环境默认的线程检查阻塞时间{@value}
	 */
	public static final int DEV_CORE_THREAD_BLOCK_CHECK_INTERVAL = 999999999;

	/**
	 * 设置在开发环境默认核心线程轮训检查时间{@value}
	 */
	public static final int DEV_MAX_CORE_THREAD_EXECUTE_TIME = 999999999;

	/**
	 * 设置在开发环境警告时间{@value}
	 */
	public static final long DEV_WARNUBG_EXCEPTION_TIME = 999999999999999999L;

	/**
	 * 设置在开发环境下worker线程的执行检查间隔
	 */
	public static final long DEV_MAX_WORKER_THREAD_EXECUTE_TIME = 60L * 1000 * 1000000;

	// 环境运行类型
	private BusRunType busRunType = BusRunType.Developer;

	// 核心线程个数 eventLoopPoolSize。默认为CPU核心数×2
	private int coreThreadPoolSize = CORE_THREAD_POOL_SIZE;

	// 工作线程个数 WorkerPoolSize
	private int workerThreadPoolSize = WORKER_THREAD_POOL_SIZE;

	// 代码异步执行的阻塞线程，用于调度内部总线执行情况
	private int internalBlockingPoolSize = INTERNAL_BLOCKING_POOL_SIZE;

	// 核心线程阻塞检查周期。系统会根据设定的时间周期性检测某个coreThread线程是否执行了太久的时间，单位ms。
	// blockedThreadCheckInterval
	private int coreThreadBlockedCheckInterval = DEV_CORE_THREAD_BLOCK_CHECK_INTERVAL;

	// 检查核心线程的等待返回时间，如果在这个指定的时间核心线程没有任何响应，则会输出一个警告日志，单位ms。用于检查一个核心线程是否被阻塞。 work
	// maxEventLoopExecuteTime
	private int maxCoreThreadExecuteTime = DEV_MAX_CORE_THREAD_EXECUTE_TIME;

	// 检查工作线程的等待返回时间，效果类似maxCoreThreadExecuteTime 。maxWorkerExecuteTime
	private long maxWorkerThreadExecuteTime = DEV_MAX_WORKER_THREAD_EXECUTE_TIME;

	// 异常日志的输出阀值时间，如果阻塞超过这个时间，则输出异常日志
	private long warningExceptionTime = DEV_WARNUBG_EXCEPTION_TIME;

	/**
	 * 获取事件总线的运行类型。
	 * 
	 * @return {@link BusRunType}
	 */
	public BusRunType getBusRunType() {
		return busRunType;
	}

	@Fluently
	/**
	 * 设置事件总线的运行类型
	 * 
	 * @param busRunType
	 *            {@link BusRunType}
	 * @return {@link BusConfig}
	 */
	public BusConfig setBusRunType(BusRunType busRunType) {
		this.busRunType = Objects.requireNonNull(busRunType);
		return this;
	}

	/**
	 * 获取核心线程的个数
	 * 
	 * @return
	 */
	public int getCoreThreadPoolSize() {
		return coreThreadPoolSize;
	}

	@Fluently
	/**
	 * 设置核心线程的个数。
	 * 核心线程用于处理数据核心业务。如果不特别指明，在Palmg中所有的线程都是使用核心线程来执行的。需要特别强调的是，不要让核心线程进行阻塞。
	 * 
	 * @param coreThreadPoolSize
	 * @return {@link BusConfig}
	 */
	public BusConfig setCoreThreadPoolSize(int coreThreadPoolSize) {
		if (1 > coreThreadPoolSize) {
			throw new IllegalArgumentException("coreThreadPoolSize must be greater than 0");
		}
		this.coreThreadPoolSize = coreThreadPoolSize;
		return this;
	}

	/**
	 * 获取工作线程的个数（规模）
	 * 
	 * @return
	 */
	public int getWorkerThreadPoolSize() {
		return workerThreadPoolSize;
	}

	@Fluently
	/**
	 * 设置核心线程的个数（规模）
	 * 
	 * @param workerThreadPoolSize
	 * @return {@link BusConfig}
	 */
	public BusConfig setWorkerThreadPoolSize(int workerThreadPoolSize) {
		if (1 > workerThreadPoolSize) {
			throw new IllegalArgumentException("workerThreadPoolSize must be greater than 0");
		}
		this.workerThreadPoolSize = workerThreadPoolSize;
		return this;
	}

	/**
	 * 获取内部处理线程个数（规模）
	 * 
	 * @return
	 */
	public int getInternalBlockingPoolSize() {
		return internalBlockingPoolSize;
	}

	@Fluently
	/**
	 * 设置内部处理线程的个数（规模）
	 * 
	 * @param internalBlockingPoolSize
	 * @return {@link BusConfig}
	 */
	public BusConfig setInternalBlockingPoolSize(int internalBlockingPoolSize) {
		if (1 > internalBlockingPoolSize) {
			throw new IllegalArgumentException("internalBlockingPoolSize must be greater than 0");
		}
		this.internalBlockingPoolSize = internalBlockingPoolSize;
		return this;
	}

	/**
	 * 获取核心线程阻塞检查周期。
	 * 
	 * @return
	 */
	public int getCoreThreadBlockedCheckInterval() {
		return coreThreadBlockedCheckInterval;
	}

	@Fluently
	/**
	 * 设置核心线程阻塞检查周期。系统会根据设定的时间周期性检测某个coreThread线程是否执行了太久的时间，单位ms。
	 * 
	 * @param coreThreadBlockedCheckInterval
	 * @return {@link BusConfig}
	 */
	public BusConfig setCoreThreadBlockedCheckInterval(int coreThreadBlockedCheckInterval) {
		if (1 > coreThreadBlockedCheckInterval) {
			throw new IllegalArgumentException("coreThreadBlockedCheckInterval must be greater than 0");
		}
		this.coreThreadBlockedCheckInterval = coreThreadBlockedCheckInterval;
		return this;
	}

	/**
	 * 获取核心线程的最大执行时间。
	 * 
	 * @return
	 */
	public int getMaxCoreThreadExecuteTime() {
		return maxCoreThreadExecuteTime;
	}

	/**
	 * 设置核心线程的最大执行时间。检查核心线程的等待返回时间，如果在这个指定的时间核心线程没有任何响应，则会输出一个警告，单位ms。用于检查一个核心线程是否被阻塞。
	 * 
	 * @param maxCoreThreadExecuteTime
	 * @return {@link BusConfig}
	 */
	public BusConfig setMaxCoreThreadExecuteTime(int maxCoreThreadExecuteTime) {
		if (1 > maxCoreThreadExecuteTime) {
			throw new IllegalArgumentException("maxCoreThreadExecuteTime must be greater than 0");
		}
		this.maxCoreThreadExecuteTime = maxCoreThreadExecuteTime;
		return this;
	}

	/**
	 * 获取工作线程检查时间。
	 * 
	 * @return
	 */
	public long getMaxWorkerThreadExecuteTime() {
		return maxWorkerThreadExecuteTime;
	}

	/**
	 * 设置工作线程执行时间。效果类似maxCoreThreadExecuteTime
	 * 
	 * @param maxWorkerThreadExecuteTime
	 * @return {@link BusConfig}
	 */
	public BusConfig setMaxWorkerThreadExecuteTime(long maxWorkerThreadExecuteTime) {
		if (1 > maxWorkerThreadExecuteTime) {
			throw new IllegalArgumentException("maxWorkerThreadExecuteTime must be greater than 0");
		}
		this.maxWorkerThreadExecuteTime = maxWorkerThreadExecuteTime;
		return this;
	}

	/**
	 * 获取bus执行警告的输出阀值
	 * 
	 * @return
	 */
	public long getWarningExceptionTime() {
		return warningExceptionTime;
	}

	@Fluently
	/**
	 * 设置bus的执行警告阀值
	 * 
	 * @param warningExceptionTime
	 * @return {@link BusConfig}
	 */
	public BusConfig setWarningExceptionTime(long warningExceptionTime) {
		if (1 > warningExceptionTime) {
			throw new IllegalArgumentException("warningExceptionTime must be greater than 0");
		}
		this.warningExceptionTime = warningExceptionTime;
		return this;
	}
}