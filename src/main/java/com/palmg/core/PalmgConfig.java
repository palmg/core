package com.palmg.core;

import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.ioc.config.IocConfig;
import com.palmg.core.main.config.DefRuntimeConfig;
import com.palmg.utility.annotation.Fluently;

/**
 * 
 * <h3>palmg配置对象</h3>
 * <p>
 * PalmgConfig中包含了所有的运行相关配置。其中包含集群配置、交互事件配置、容器配置。
 * </p>
 * @author chenkui
 *
 */
public interface PalmgConfig {

	/**
	 * 获取集群配置
	 * 
	 * @return {@link ClusterConfig}
	 */
	ClusterConfig getClusterConfig();

	@Fluently
	/**
	 * 替换集群配置
	 * 
	 * @param {@link ClusterConfig}
	 * @return {@link PalmgConfig}
	 */
	PalmgConfig setClusterConfig(ClusterConfig clusterConfig);
	
	/**
	 * 获取总线配置
	 * @return {@link BusConfig}
	 */
	BusConfig getBusConfig();

	@Fluently
	/**
	 * 替换传输总线配置
	 * 
	 * @param {@link BusConfig}
	 * @return {@link PalmgConfig}
	 */
	PalmgConfig setBusConfig(BusConfig busConfig);

	/**
	 * 获取容器相关配置
	 * 
	 * @return {@link IocConfig}
	 */
	IocConfig getIocConfig();

	@Fluently
	/**
	 * 设置容器相关配置
	 * 
	 * @param {@link IocConfig}
	 *            
	 */
	PalmgConfig setIocConfig(IocConfig iocConfig);
	
	/**
	 * 配置的默认构造器，创建一个默认的{@link PalmgConfig}实例。
	 * @return
	 */
	public static PalmgConfig of(){
		// 构造一个默认的基础配置类
		return new DefRuntimeConfig();
	}
	
	public static class Builder{
		
	}
}
