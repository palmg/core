/*
 * Copyright palmg(www.palmg.com)
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Apache License v2.0 which accompanies this distribution.
 * 
 *     The Apache License v2.0 is available at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * You may elect to redistribute this code under this licenses and copyright.
 * ------------------------------------------------------
 */
package com.palmg.core;

import com.palmg.core.config.PalmgConfig;
import com.palmg.core.main.Toolkit;

/**
 * Palmg全局toolkit
 * 
 * @author chkui
 */
public interface Palmg extends Toolkit {
	public static Palmg builder() {
		// TODO
		return null;
	}

	public static Palmg builder(final String springXmlPath) {
		// TODO
		return null;
	}

	public static Palmg builder(final PalmgConfig config) {
		// TODO
		return null;
	}
}
