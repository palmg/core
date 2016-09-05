
package com.palmg.core.main.vertx;

import java.util.Objects;
import java.util.Optional;

import com.palmg.core.bus.consumer.config.ConsumerOptions;
import com.palmg.core.main.annotation.Assembly;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

/**
 * 构建Verticle的工厂
 * 
 * @author chkui
 */
@Assembly
public class VerticleFactory {
	public <T> Verticle<T> getVerticle(EventBus eventbus, Handler<Message<T>> handler, ConsumerOptions options) {
		Objects.requireNonNull(eventbus);
		Objects.requireNonNull(handler);

		DeploymentOptions depOpts = new DeploymentOptions();

		Optional<ConsumerOptions> optionsPer = Optional.of(options);

		Verticle<T> verticle = new Verticle<T>();

		optionsPer.map(x -> x.getLocalType()).ifPresent(enumType -> {
			switch (enumType) {
			case Local:
				verticle.setLocalType(true);
				break;
			case Cluseter:
			default:
				break;
			}
		});

		optionsPer.map(x -> x.getThreadType()).ifPresent(threadType -> {
			switch (threadType) {
			case worker:
				depOpts.setWorker(true);
				break;
			case core:
			default:
				break;
			}
		});

		return new Verticle<T>().setEventBus(eventbus).setHandler(handler).setDeployOptions(depOpts);
	};
}
