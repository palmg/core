package com.palmg.core.bus.consumer.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SendOptions {

	Map<String, List<String>> header;

	public SendOptions() {
		header = new HashMap<String, List<String>>();
	}

	public SendOptions addHeader(String key, String value) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);
		if (!Optional.ofNullable(header.get(key)).map(x -> x.add(value)).isPresent()) {
			List<String> list = new ArrayList<String>();
			list.add(value);
			header.put(key, list);
		}
		return this;
	}
}
