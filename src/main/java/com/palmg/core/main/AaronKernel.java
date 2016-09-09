package com.palmg.core.main;

import com.palmg.core.bus.consumer.config.ConsumerOptions;
import com.palmg.core.bus.consumer.config.SendOptions;
import com.palmg.utility.annotation.Fluently;
import com.palmg.utility.async.Callback;
import com.palmg.utility.async.CallbackResult;
import com.palmg.utility.async.Msg;

/**
 * 事件总线内核
 * <p>
 * 内核为plamg-core提供基于publish\consumer模型的事件驱动功能。
 * </p>
 * <p>
 * 按照publish\consumer事件模型，该接口需要实现部署一个consumer、向指定地址发送消息(send)、向指定地址广播消息(publish)
 * </p>
 * 
 * @author chkui
 */
public interface AaronKernel {

	@Fluently
	/**
	 * 增加一个consumer
	 * 
	 * @param address
	 *            监听地址
	 * @param callback
	 *            consumer被调用时的回调
	 * @param options
	 *            consumer相关参数
	 * @return this
	 */
	<T> AaronKernel addConsumer(String address, Callback<Msg<T>> callback, ConsumerOptions options);

	@Fluently
	/**
	 * 
	 * /** 向集群内部某个consumer发送消息
	 * 
	 * @param address
	 *            发送地址
	 * @param obj
	 *            发送的内容
	 * @param callback
	 *            consumer返回消息的回调方法
	 * @param options
	 *            相关操作参数
	 * @return this
	 */
	<T> AaronKernel send(String address, Object obj, CallbackResult<T> callback, SendOptions options);

	@Fluently
	/**
	 * 向集群内部某个节点广播消息
	 * 
	 * @param address
	 *            发送地址
	 * @param obj
	 *            发送的内容
	 * @param options
	 *            相关操作参数
	 * @return this
	 */
	<T> AaronKernel publish(String address, Object obj, SendOptions options);
}
