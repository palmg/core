package com.palmg.core.bus.consumer.config;

import com.palmg.core.bus.enums.ConsumerLocalType;
import com.palmg.core.bus.enums.ConsumerThreadType;

public class ConsumerOptions {
	private ConsumerThreadType threadType;// 线程类型

	private ConsumerLocalType localType;// 运行位置的类型

	public ConsumerOptions() {
		this.threadType = ConsumerThreadType.core;
		this.localType = ConsumerLocalType.Cluseter;
	}

	public ConsumerThreadType getThreadType() {
		return threadType;
	}

	public ConsumerOptions setThreadType(ConsumerThreadType threadType) {
		this.threadType = threadType;
		return this;
	}

	public ConsumerLocalType getLocalType() {
		return localType;
	}

	public ConsumerOptions setLocalType(ConsumerLocalType localType) {
		this.localType = localType;
		return this;
	}
}
