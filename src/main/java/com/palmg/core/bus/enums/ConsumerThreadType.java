package com.palmg.core.bus.enums;

/**
 * 每一个consumer对应的线程类型
 * @author chkui
 */
public enum ConsumerThreadType {
	/**
	 * 核心调度线程
	 */
	core,
	/**
	 * 工作线程
	 */
	worker,
	
	/**
	 * 多线程worker
	 */
	multiWorker,
}
