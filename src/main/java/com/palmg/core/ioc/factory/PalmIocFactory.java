package com.palmg.core.ioc.factory;

import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.ioc.impl.DefaultLocalSpringIocWrapper;

/**
 * Ioc容器的构建工厂,用于确保全局实例的一致性和单例性
 * 
 * @author chenkui
 *
 */
public class PalmIocFactory {
	/**
	 * 构建Ioc，获取IOC实例
	 * 
	 * @return {@link PalmgIoc}
	 */
	public static PalmgIoc build() {
		return DefaultLocalSpringIocWrapper.Instance.build();
	}
}
