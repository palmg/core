package com.palmg.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalmgTest {
	
	private Palmg palmg;
	
	@Before
	public void before(){
		palmg = Palmg.build();
	}
	
	@Test
	public void budildPalmg(){
		Assert.assertNotNull(palmg.getIoc());
	}
}
