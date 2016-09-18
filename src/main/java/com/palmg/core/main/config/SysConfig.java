package com.palmg.core.main.config;

/**
 * Palmg运行的全局配置参数入口，如果需要增加新的配置，请再这里添加
 * @author chenkui
 *
 */
public interface SysConfig {
	/**
	 * spring加载路径配置
	 * @return
	 */
	String getSpringXmlPath();
}
