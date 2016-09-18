package com.palmg.core.main.impl;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.Palmg;
import com.palmg.core.bus.consumer.PalmgConsumer;
import com.palmg.core.bus.publisher.PalmgPulisher;
import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.ioc.factory.PalmIocFactory;
import com.palmg.core.util.PalmgUtil;

/**
 * 基础toolkit服务
 * @author chenkui
 */
public enum DefaultToolkitImpl implements Palmg {
	Instance;
	private PalmgIoc ioc;

	private DefaultToolkitImpl() {
		// 创建一个全局或者palmg内部容器
		// 容器需要阻塞所有的并发请求，以保证ioc容器是一个单利
		synchronized (DefaultToolkitImpl.class) {
			if (null == ioc) {
				ioc = PalmIocFactory.build();
			}
		}
	}

	@Override
	public PalmgConsumer getConsumer() {
		return ioc.getBean(PalmgConsumer.class);
	}

	@Override
	public PalmgPulisher getPulisher() {
		return ioc.getBean(PalmgPulisher.class);
	}

	@Override
	public PalmgCluster getCluster() {
		return ioc.getBean(PalmgCluster.class);
	}

	@Override
	public PalmgIoc getIoc() {
		return ioc.getBean(PalmgIoc.class);
	}

	@Override
	public PalmgUtil getUtil() {
		return ioc.getBean(PalmgUtil.class);
	}

}
