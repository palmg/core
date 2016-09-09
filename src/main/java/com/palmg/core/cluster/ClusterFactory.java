package com.palmg.core.cluster;

import com.palmg.cluster.PalmgCluster;
import com.palmg.core.cluster.impl.HazelcastClusterWrapperImpl;

/**
 * 独立于Ioc之外的套件工厂
 * @author chkui
 */
public class ClusterFactory {
	
	/**
	 * 获取集群管理对象实例
	 * @return
	 */
	public static PalmgCluster getPalmgClusterInstance(){
		return HazelcastClusterWrapperImpl.Instance.buildCluster();
	}
}
