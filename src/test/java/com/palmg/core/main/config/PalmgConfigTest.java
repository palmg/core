package com.palmg.core.main.config;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.palmg.core.bus.config.BusConfig;
import com.palmg.core.bus.enums.BusRunType;
import com.palmg.core.cluster.config.ClusterConfig;
import com.palmg.core.cluster.config.MulticastConfig;
import com.palmg.core.cluster.config.NetConfig;
import com.palmg.core.cluster.config.TcpIpConfig;
import com.palmg.core.ioc.config.GuiceIocConfig;
import com.palmg.core.ioc.config.IocConfig;
import com.palmg.core.ioc.config.SpringIocConfig;

/**
 * 测试用于保证当初的的config功能设置被正确执行。
 * 
 * @author chenkui
 *
 */
public class PalmgConfigTest {

	private PalmgConfig palmgConfig;

	@Before
	public void init() {
		palmgConfig = new PalmgConfig();
	}

	@Test
	/**
	 * 用于断言所有的非空运行
	 */
	public void testCreateConfig() {
		BusConfig busConfig = palmgConfig.getBusConfig();

		ClusterConfig clusterConfig = palmgConfig.getClusterConfig();

		IocConfig iocConfig = palmgConfig.getIocConfig();

		Assert.assertNotNull(busConfig);// 确保BusConfig非空
		Assert.assertNotNull(clusterConfig);// 确保集群配置非空
		Assert.assertNotNull(iocConfig);// 确保IOC配置非空
		Assert.assertNotNull(clusterConfig.getNetConfig());// 确保网络配置非空
	}

	@Test
	/**
	 * 用于测试所有默认值是否被正确设置,并确保今后没有被错误修改。
	 * 
	 */
	public void testDefaultValue() {
		ClusterConfig clusterConfig = palmgConfig.getClusterConfig();

		Assert.assertFalse(clusterConfig.isEnabled());
		NetConfig netConfig = clusterConfig.getNetConfig();
		Assert.assertEquals(NetConfig.PING_INTERVAL, netConfig.getPingInterval());
		Assert.assertEquals(NetConfig.PINT_REPLY_INTERVAL, netConfig.getPingReplyInterval());
		Assert.assertEquals(NetConfig._PORT, netConfig.getPort());
		Assert.assertEquals(NetConfig.PORT_COUNT, netConfig.getPortCount());
		Assert.assertNull(netConfig.getInterfaces());
		Assert.assertNull(netConfig.getPublicAddress());

		TcpIpConfig tcpIpConfig = netConfig.getTcpIpConfig();
		Assert.assertEquals(TcpIpConfig.CONNECT_TIMEOUT, tcpIpConfig.getConnectTimeOut());
		Assert.assertEquals(Arrays.asList(TcpIpConfig.CONNECT_NODE), Arrays.asList(tcpIpConfig.getConnectNode()));
		
		MulticastConfig multicastConfig = netConfig.getMulticastConfig();
		Assert.assertNotNull(multicastConfig);

		BusConfig busConfig = palmgConfig.getBusConfig();
		Assert.assertEquals(BusRunType.Developer, busConfig.getBusRunType());
		Assert.assertEquals(BusConfig.DEV_MAX_WORKER_THREAD_EXECUTE_TIME, busConfig.getMaxWorkerThreadExecuteTime());
		Assert.assertEquals(BusConfig.CORE_THREAD_POOL_SIZE, busConfig.getCoreThreadPoolSize());
		Assert.assertEquals(BusConfig.INTERNAL_BLOCKING_POOL_SIZE, busConfig.getInternalBlockingPoolSize());
		Assert.assertEquals(BusConfig.DEV_MAX_CORE_THREAD_EXECUTE_TIME, busConfig.getMaxCoreThreadExecuteTime());
		Assert.assertEquals(BusConfig.WORKER_THREAD_POOL_SIZE, busConfig.getWorkerThreadPoolSize());

		IocConfig iocConfig = palmgConfig.getIocConfig();
		SpringIocConfig springIocConfig = iocConfig.getSpringIocConfig();
		Assert.assertTrue(springIocConfig.getSpringXmlPaths().contains("configs/ioc/default/ioc-default.xml"));
		
		GuiceIocConfig guiceIocConfig = iocConfig.getGuiceIocConfig();
		Assert.assertNotNull(guiceIocConfig);
	}
}
