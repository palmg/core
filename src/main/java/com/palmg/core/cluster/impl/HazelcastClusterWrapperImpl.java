package com.palmg.core.cluster.impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.palmg.cluster.PalmgCluster;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.cluster.config.NetConfig;
import com.palmg.core.cluster.config.TcpConfig;
import com.palmg.core.main.AaronConfigure;
import com.palmg.core.main.config.DefaultConf;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * user hazelcast to implement cluster。
 * @author chenkui
 *
 */
public enum HazelcastClusterWrapperImpl implements PalmgCluster {
	Instance;

	private static final String XML_PATH = "configs/cluster/palmg-cluster.xml";

	private static final Logger LOG = LoggerFactory.getLogger(HazelcastClusterWrapperImpl.class);

	private Optional<HazelcastInstance> hazelcastIns = Optional.empty();

	/**
	 * Get the hazelcastinstance
	 * 
	 * @return
	 */
	public HazelcastInstance getHazelcastCluster() {
		synchronized (Instance) {
			return hazelcastIns.orElseGet(new Supplier<HazelcastInstance>() {
				@Override
				public HazelcastInstance get() {
					try (InputStream in = getConfigStream(); InputStream bInputStream = new BufferedInputStream(in)) {
						if (null != in) {
							hazelcastIns = Optional.of(Hazelcast.newHazelcastInstance(getConfig(bInputStream)));
						}
					} catch (Exception e) {
						LOG.error("load hazelcast xml file error!Terminate palmg!");
						System.exit(0);
					}
					return hazelcastIns.get();
				}
			});
		}
	}

	// load hazelcast xml config from classpath.
	private InputStream getConfigStream() {
		return Optional.of(Thread.currentThread()).map(th -> th.getContextClassLoader())
				.map(cl -> cl.getResourceAsStream(XML_PATH)).orElseGet(new Supplier<InputStream>() {// default get
					@Override
					public InputStream get() {
						return getClass().getClassLoader().getResourceAsStream(XML_PATH);
					}
				});
	}

	// set hazelcast config user by palmg cluster config
	private Config getConfig(InputStream stream) {
		Config config = new XmlConfigBuilder(stream).build();
		
		// palmg's config aways included in AaronConfigure instance
		final ClusterConfig clusterConfig = AaronConfigure.Instance.getPalmgConfig().getClusterConfig();
		// set groupName
		config.getGroupConfig().setName(clusterConfig.getGroupName()).setPassword("default");
		// set network
		final NetConfig netConfig = clusterConfig.getNetConfig();
		final NetworkConfig networkConfig = config.getNetworkConfig();
		networkConfig.setPort(netConfig.getPort()).setReuseAddress(netConfig.isReusePort())
				.setPortAutoIncrement(netConfig.isPortIncrement()).setPortCount(netConfig.getPortCount());

		// set out interfaces
		if (null != netConfig.getInterfaces()) {
			networkConfig.getInterfaces().setEnabled(true).setInterfaces(Arrays.asList(netConfig.getInterfaces()));
		}

		// set public address
		if (null != netConfig.getPublicAddress()) {
			networkConfig.setPublicAddress(netConfig.getPublicAddress());
		}
		
		// protocol set
		switch (netConfig.getNetType()) {
		case Multicast:// user multicast
			// TODO
			break;
		case TcpIp://use tcpip
		default:
			TcpIpConfig tcpIpConfig = networkConfig.getJoin().getTcpIpConfig();
			TcpConfig tcpConfig = netConfig.getTcpConfig();
			tcpIpConfig.setEnabled(true).setMembers(Arrays.asList(tcpConfig.getConnectNode()))
					.setConnectionTimeoutSeconds(tcpConfig.getConnectTimeOut());
			if (null != tcpConfig.getRequireNode()) {
				StringBuilder builder = new StringBuilder();
				for(String requireNode : tcpConfig.getRequireNode()){
					builder.append(requireNode).append(DefaultConf.STRING_COMMA);
				}
				tcpIpConfig.setRequiredMember(builder.toString());
			}
			break;
		}

		return config;
	}
}
