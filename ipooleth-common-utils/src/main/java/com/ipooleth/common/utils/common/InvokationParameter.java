package com.ipooleth.common.utils.common;


import com.ipooleth.common.utils.JasonUtil;
import com.ipooleth.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Webservice接口通用方法参数类
 *
 */
public final class InvokationParameter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String channel;
	private String param;
	private String client;

	public InvokationParameter() {

	}

	public InvokationParameter(String channel, String param,String client) {
		this.channel = channel;
		this.param = param;
	}

	public String getChannel() {
		return channel;
	}
	public String getParam() {
		return param;
	}

	public <T> T getJsonParamObject(Class<T> clazz) {
		if (StringUtil.isEmpty(param)) return null;
		T result = null;
		try {
			result = JasonUtil.fromJson(param, clazz);
		} catch (IOException e) {
			logger.error("Unable to convert Param:'" + param + "' to object, not a valid json format", e);
		}
		return result;
	}

	public Map<String, Object> getJsonParamMap() {
		if (StringUtil.isEmpty(param)) return null;

		Map<String, Object> result = null;
		try {
			result = JasonUtil.jsonToMap(param);
		} catch (IOException e) {
			logger.error("Unable to convert Param:'" + param + "' to Map, not a valid json format", e);
		}
		return result;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}


	@Override
	public String toString() {
		return "InvokationParameter{" +
				"channel='" + channel + '\'' +
				", param='" + param + '\'' +
				", client='" + client + '\'' +
				'}';
	}
}
