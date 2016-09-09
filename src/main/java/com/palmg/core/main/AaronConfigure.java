package com.palmg.core.main;

import java.util.Optional;

import com.palmg.core.cluster.config.ClusterConfig;

public enum AaronConfigure {
	Instance;

	private boolean cluster;// 标记是否集群

	private ClusterConfig clusterConfig;// 集群配置

	private Optional<String[]> springXmlPaths;// xml配置路径

	private AaronConfigure() {
		springXmlPaths = Optional.empty();
	}

	public boolean isCluster() {
		return cluster;
	}

	public AaronConfigure setCluster(boolean cluster) {
		this.cluster = cluster;
		return this;
	}

	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}

	public AaronConfigure setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = clusterConfig;
		return this;
	}

	public Optional<String[]> getSpringXmlPath() {
		return springXmlPaths;
	}

	public AaronConfigure setSpringXmlPath(String... springXmlPaths) {
		this.springXmlPaths = Optional.ofNullable(springXmlPaths);
		return this;
	}
}
