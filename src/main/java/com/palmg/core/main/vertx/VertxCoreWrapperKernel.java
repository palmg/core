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
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;

import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.bus.consumer.config.ConsumerOptions;
import com.palmg.core.bus.consumer.config.SendOptions;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.cluster.impl.HazelcastClusterWrapperImpl;
import com.palmg.core.main.AaronConfigure;
import com.palmg.core.main.AaronKernel;
import com.palmg.utility.async.Callback;
import com.palmg.utility.async.CallbackResult;
import com.palmg.utility.async.Msg;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

@Named
@Lazy(false)
/**
 * <h3>AaronKernel implements</h3>
 * <p>
 * Use Vertx as a Wrapper.This class user Vertx as a kernel.
 * </p>
 * 
 * @author chkui
 */
public final class VertxCoreWrapperKernel implements AaronKernel {
	private static final Logger LOG = LoggerFactory.getLogger(VertxCoreWrapperKernel.class);

	private Vertx vertx;

	@Inject
	private ClusterConfig clusterConfig;

	@PostConstruct
	public void init() {
		// build vertx
		vertxBuild();
		// we must deploy one verticle befor，otherwise palmg will be terminal.
		vertx.deployVerticle(new AbstractVerticle() {
		});
	}

	private void vertxBuild() {
		try {
			// get the configure.
			ClusterConfig cluster = clusterConfig;
			final BusConfig busConfig = AaronConfigure.Instance.getPalmgConfig().getBusConfig();
			VertxOptions options = new VertxOptions();

			// eventbus configure
			options.setEventLoopPoolSize(busConfig.getCoreThreadPoolSize())
					.setWorkerPoolSize(busConfig.getWorkerThreadPoolSize())
					.setInternalBlockingPoolSize(busConfig.getInternalBlockingPoolSize())
					.setBlockedThreadCheckInterval(busConfig.getCoreThreadBlockedCheckInterval())
					.setMaxEventLoopExecuteTime(busConfig.getMaxCoreThreadExecuteTime())
					.setMaxWorkerExecuteTime(busConfig.getMaxWorkerThreadExecuteTime())
					.setWarningExceptionTime(busConfig.getWarningExceptionTime());

			if (cluster.isEnabled()) {// use cluster
				options.setClustered(true);
				CompletableFuture<Void> future = new CompletableFuture<>();
				// user hazelcast to create cluster
				HazelcastClusterWrapperImpl.Instance.getHazelcastCluster();

				Vertx.clusteredVertx(options, result -> {
					if (result.failed()) {
						LOG.error("Init cluster error! Terminate palmg", result.cause());
						future.completeExceptionally(result.cause());
					} else {
						vertx = result.result();
						future.complete(null);
					}
				});
				future.get(10, TimeUnit.SECONDS);
			} else {// default
				vertx = Vertx.vertx(options.setClustered(false));
			}
		} catch (Throwable t) {
			LOG.error("create palmg kernel error! Terminate", t);
			// if create vertx instance error then terminate palmg.
			// because we can not provide any toolkit without vertx.
			System.exit(0);
		}
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
