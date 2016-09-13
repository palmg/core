package com.palmg.core.ioc.config;

import java.util.Arrays;
import java.util.List;

import com.palmg.utility.annotation.Fluently;

/**
 * 用于标记spring容器的配置类
 * 
 * @author chenkui
 */
public class SpringIocConfig {
	private List<String> springXmlPaths;// spring的加载路径

	public SpringIocConfig() {
		this.springXmlPaths = Arrays.asList("configs/ioc/default/ioc-default.xml");
	}

	/**
	 * 获取当前spring的启动路径
	 * 
	 * @return
	 */
	public List<String> getSpringXmlPaths() {
		return springXmlPaths;
	}

	@Fluently
	/**
	 * 增加spring的扫描路径
	 * 
	 * @param springXmlPaths
	 *            要添加的路径
	 * @return 
	 */
	public SpringIocConfig addSpringXmlPaths(String... springXmlPaths) {
		this.springXmlPaths.addAll(Arrays.asList(springXmlPaths));
		return this;
	}

	@Fluently
	/**
	 * 设置当前spring的启动路径
	 * 
	 * @param springXmlPaths
	 *            要设置的路径
	 * @return this
	 */
	public SpringIocConfig setSpringXmlPaths(List<String> springXmlPaths) {
		this.springXmlPaths = springXmlPaths;
		return this;
	}
}
