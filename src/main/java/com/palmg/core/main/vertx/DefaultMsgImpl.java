package com.palmg.core.main.vertx;

import java.util.List;

import com.palmg.utility.async.Msg;

import io.vertx.core.eventbus.Message;

public class DefaultMsgImpl<T> implements Msg<T> {
	
	Message<T> vertxMsg;
	
	Msg<T> setvertxMsg(Message<T> vertxMsg){
		this.vertxMsg = vertxMsg;
		return this;
	}

	@Override
	public List<String> getHeader(String name) {
		vertxMsg.headers().getAll(name);
		return null;
	}
	
	@Override
	public String getAddress() {
		return vertxMsg.address();
	}

	@Override
	public T getMessage() {
		return vertxMsg.body();
	}

	@Override
	public void reply(Object msg) {
		vertxMsg.reply(msg);
	}

	@Override
	public void fail(String errMsg) {
		vertxMsg.fail(-1, errMsg);
	}
}
