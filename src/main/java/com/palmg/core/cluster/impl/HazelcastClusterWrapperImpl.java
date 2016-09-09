package com.palmg.core.cluster.impl;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.palmg.cluster.PalmgCluster;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public enum HazelcastClusterWrapperImpl implements PalmgCluster {
	Instance;
	
	private static final Logger LOG = LoggerFactory.getLogger(HazelcastClusterWrapperImpl.class);

	private HazelcastInstance hazelcastins;

	public PalmgCluster buildCluster() {
		if (null == hazelcastins) {
			if(LOG.isDebugEnabled()){
				LOG.debug("hazelcast not init!");
			}
			synchronized (Instance) {
				LOG.debug("begin init hazelcast!");
				if (null == hazelcastins) {
					hazelcastins = Hazelcast.newHazelcastInstance();
				}
				LOG.debug("init hazelcast success!");
			}
		}
		return this;
	}
}
