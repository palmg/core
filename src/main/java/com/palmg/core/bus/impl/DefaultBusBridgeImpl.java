package com.palmg.core.bus.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.palmg.core.bus.BusBridge;
import com.palmg.core.main.AaronKernel;

@Component
public class DefaultBusBridgeImpl implements BusBridge {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultBusBridgeImpl.class);
	
	@Autowired
	private AaronKernel kernel;
	
	public DefaultBusBridgeImpl(){
	}
	
	@PostConstruct
	private void initBus(){
		LOG.info("Begin create palmg bus!");
		LOG.info("Create palmg bus end!");
	}

}
