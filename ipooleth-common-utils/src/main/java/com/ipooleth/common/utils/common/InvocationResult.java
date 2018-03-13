package com.ipooleth.common.utils.common;


import com.ipooleth.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Webservice接口通用返回结果类。
 *
 */
public final class InvocationResult{
	private boolean isSucceeded = false;
	private String errorCode = "";
	private String errorMessage = "";
	private Object result = "";

	private String dateTime = DateUtil.getCurrentDateTime();
	
	private static final Logger logger = LoggerFactory
			.getLogger(InvocationResult.class);
	
	public InvocationResult(boolean isSucceeded, String errorCode,
							String errorMessage, Object result) {
		this.isSucceeded = isSucceeded;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.result = result;
	}
	
	public InvocationResult(boolean isSucceeded,
							Object result) {
		super();
		this.isSucceeded = isSucceeded;
		this.result = result;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Object getResult() {
		return result;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setResult(Object result) {
		this.result = result;
	}



	//获取ip地址
	public static String getIp(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
				.getRequestAttributes()).getRequest();
		return getIpAddrByRequest(request);


	}

	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("AECIP");
		logger.info("aecip"+ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
