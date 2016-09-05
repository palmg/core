package com.palmg.core.main;

import com.palmg.core.bus.consumer.config.ConsumerOptions;
import com.palmg.utility.async.Callback;
import com.palmg.utility.async.Msg;

public interface AaronKernel {

	<T> AaronKernel addConsumer(String address,Callback<Msg<T>> callback, ConsumerOptions options);
}
