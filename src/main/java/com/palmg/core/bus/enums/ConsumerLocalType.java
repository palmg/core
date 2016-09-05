package com.palmg.core.bus.enums;

/**
 * consumer的部署位置
 * @author chkui
 */
public enum ConsumerLocalType {
	/**
	 * 只接收本地（被JVM）消息
	 */
	Local,
	/**
	 * 接收集群范围内的所有消息
	 */
	Cluseter
}
