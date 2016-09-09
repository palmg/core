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
package com.palmg.core.main.vertx;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import com.palmg.core.bus.consumer.config.ConsumerOptions;
import com.palmg.core.bus.consumer.config.SendOptions;
import com.palmg.core.main.AaronKernel;
import com.palmg.core.main.annotation.Assembly;
import com.palmg.utility.async.Callback;
import com.palmg.utility.async.CallbackResult;
import com.palmg.utility.async.Msg;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

@Assembly
@Lazy(false)
/**
 * <h3>Vertx的包装实现类，对外提供所有的Vertx的基础功能</h3>
 * <p>
 * 
 * </p>
 * 
 * @author chkui
 */
public final class VertxCoreWrapperKernel implements AaronKernel {
	private static final Logger LOG = LoggerFactory.getLogger(VertxCoreWrapperKernel.class);

	private Vertx vertx;

	@PostConstruct
	public void init() {
		CompletableFuture<Void> future = new CompletableFuture<>();
		new Thread(() -> {
			vertx = Vertx.vertx();
			future.complete(null);
		}).start();
		try {
			future.get(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOG.error("init vertx error!", e);
		}

		vertx.deployVerticle(new AbstractVerticle() {
		});
	}

	@Override
	public <T> AaronKernel addConsumer(String address, Callback<Msg<T>> callback, ConsumerOptions options) {
		Objects.requireNonNull(address);
		Objects.requireNonNull(callback);
		Objects.requireNonNull(options);

		// 部署一个verticle
		vertx.deployVerticle(new AbstractVerticle() {
			@Override
			public void start() {
				// 根据类型创建一个consumer
				switch (options.getLocalType()) {
				case Local:
					vertx.eventBus().<T>localConsumer(address, handler -> {
						callback.call(new DefaultMsgImpl<T>().setvertxMsg(handler));
					});
					break;
				case Cluseter:
				default:
					vertx.eventBus().<T>consumer(address, handler -> {
						callback.call(new DefaultMsgImpl<T>().setvertxMsg(handler));
					});
					break;
				}
			}

			@Override
			public void stop() {

			}
		}, Optional.of(new DeploymentOptions()).map(opt -> {
			// 设置deploy参数
			switch (options.getThreadType()) {
			case worker:
				opt.setWorker(true);
				break;// 设置为worker线程
			case multiWorker:
				opt.setMultiThreaded(true);
				break;// 设置为同一段worker用多个线程执行
			case core:
			default:
				break;
			}
			return opt;
		}).get(), depResult -> {
			// 异步返回部署消息
			if (depResult.succeeded()) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("deploy consumer {} success.", address);
				}
			} else {
				LOG.error("deploy consumer error!", depResult.cause());
			}
		});
		return this;
	}

	@Override
	public <T> AaronKernel send(String address, Object obj, CallbackResult<T> callback, SendOptions options) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public <T> AaronKernel publish(String address, Object obj, SendOptions options) {
		// TODO Auto-generated method stub
		return this;
	}
}
