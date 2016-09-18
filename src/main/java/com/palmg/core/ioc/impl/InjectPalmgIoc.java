package com.palmg.core.ioc.impl;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.palmg.core.ioc.PalmgIoc;
import com.palmg.core.ioc.factory.PalmIocFactory;

@Named
/**
 * 注入到容器中的容器自身实现类
 * @author chenkui
 *
 */
public class InjectPalmgIoc implements PalmgIoc {
	
	private PalmgIoc wrapper;
	
	@PostConstruct
	public void init(){
		wrapper = PalmIocFactory.build();
	}

	@Override
	public Object getBean(String beanName) {
		return wrapper.getBean(beanName);
	}

	@Override
	public <T> T getBean(Class<T> beanType) {
		return wrapper.getBean(beanType);
	}

	@Override
	public <T> T getBean(String beanName, Class<T> beanType) {
		return wrapper.getBean(beanName, beanType);
	}

	@Override
	public <T> T addBean(Class<T> clazz) {
		return wrapper.addBean(clazz);
	}

	@Override
	public <T> T addBean(String beanName, Class<T> clazz) {
		return wrapper.addBean(beanName, clazz);
	}

	@Override
	public boolean containBean(String beanName) {
		return wrapper.containBean(beanName);
	}

	@Override
	public int containBean(Class<?> clazz) {
		return wrapper.containBean(clazz);
	}

	@Override
	public void destroyBean(Object instance) {
		wrapper.destroyBean(instance);
	}

}
