package com.palmg.core.main.config;

import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.palmg.utility.properties.PropertiesLoadUtil;

/**
 * 使用properties文件来管理全局配置
 * 
 * @author chenkui
 *
 */
public class PropertiesSysConfig implements SysConfig {
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesSysConfig.class);

	private final static String PROPETTY_FILE_PATH = "configs/default_conf.properties";

	private final static String SPRING_XMLFILE_LOAD_PATH = "default.spring.loadPath";

	Optional<Properties> prpperty;

	public PropertiesSysConfig() {
		try {
			prpperty = Optional.of(PropertiesLoadUtil.classPathLoad(PROPETTY_FILE_PATH));
		} catch (Throwable t) {
			LOG.error("load base config properties error!", t);
			System.exit(0);
		}
	}

	@Override
	public String getSpringXmlPath() {
		return prpperty.map(p->p.getProperty(SPRING_XMLFILE_LOAD_PATH)).orElseGet(()->{
			LOG.error("get spring default xml path error! Terminate!");
			System.exit(0);
			return "";
		});
	}
}
