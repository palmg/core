package com.palmg.core.main.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

/**
 * Verticle实例
 * 
 * @author chkui
 */
public class Verticle<T> extends AbstractVerticle {

	private EventBus eventBus;

	private Handler<Message<T>> handler;

	private DeploymentOptions deployOptions;

	private boolean localType;

	public EventBus getEventBus() {
		return eventBus;
	}

	public Verticle<T> setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		return this;
	}

	public Handler<Message<T>> getHandler() {
		return handler;
	}

	public Verticle<T> setHandler(Handler<Message<T>> handler) {
		this.handler = handler;
		return this;
	}

	public DeploymentOptions getDeployOptions() {
		return deployOptions;
	}

	public Verticle<T> setDeployOptions(DeploymentOptions deployOptions) {
		this.deployOptions = deployOptions;
		return this;
	}

	public boolean isLocalType() {
		return localType;
	}

	public Verticle<T> setLocalType(boolean localType) {
		this.localType = localType;
		return this;
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}
}
