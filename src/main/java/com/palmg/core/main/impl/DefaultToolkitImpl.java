package com.palmg.core.main.impl;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.Palmg;
import com.palmg.core.bus.consumer.PalmgConsumer;
import com.palmg.core.bus.publisher.PalmgPulisher;
import com.palmg.core.cron.PalmgCron;
import com.palmg.core.ioc.PalmgIoc;

public enum DefaultToolkitImpl implements Palmg {
	Instance;
	private PalmgIoc ioc;

	private DefaultToolkitImpl() {
		ioc = PalmgIoc.build();
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
	public PalmgCron getCron() {
		return ioc.getBean(PalmgCron.class);
	}

}
