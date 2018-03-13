package com.ipooleth.common.utils.common;


import javax.servlet.ServletException;

public class ErrorException extends ServletException {

	private static final long serialVersionUID = 1L;

	// 异常代码
	private String errcode;

	// 异常参数
	private String[] params;

	// 异常描述
	private String errm;
	
	// 自定义参数0
	private String extraparam0;
	
	// 自定义参数1
	private String extraparam1;
	
	// 自定义参数2
	private String extraparam2;
	
	// 自定义参数2
	private String extraparam3;

	public String getExtraparam1() {
		return extraparam1;
	}

	public void setExtraparam1(String extraparam1) {
		this.extraparam1 = extraparam1;
	}

	public String getExtraparam2() {
		return extraparam2;
	}

	public void setExtraparam2(String extraparam2) {
		this.extraparam2 = extraparam2;
	}

	public String getExtraparam3() {
		return extraparam3;
	}

	public void setExtraparam3(String extraparam3) {
		this.extraparam3 = extraparam3;
	}

	public ErrorException() {
		super();
	}

	public ErrorException(String errcode) {
		this(errcode, "");
	}

	public ErrorException(String errcode, String param) {
		this(errcode, new String[] { param });
	}

	public ErrorException(String errcode, String param1, String param2) {
		this(errcode, new String[] { param1, param2 });
	}

	public ErrorException(String errcode, String[] params) {
		super(errcode);
		this.errcode = errcode;
		this.params = params;

		// 获取资源代码对应描述
		this.errm = getErrmFromCfg(errcode, params);
	}
	
	public ErrorException(String errcode, String[] params, String extraparam0) {
		super(errcode);
		this.errcode = errcode;
		this.params = params;
		this.extraparam0=extraparam0;
		// 获取资源代码对应描述
		this.errm = getErrmFromCfg(errcode, params);
	}
	
	public ErrorException(String errcode, String[] params, String extraparam0, String extraparam1) {
		super(errcode);
		this.errcode = errcode;
		this.params = params;
		this.extraparam0=extraparam0;
		this.extraparam1=extraparam1;
		// 获取资源代码对应描述
		this.errm = getErrmFromCfg(errcode, params);
	}

	/**
	 * 从配置文件中获取错误信息
	 * 
	 * @param errcode
	 * @param params
	 * @return
	 */
	public static String getErrmFromCfg(String errcode, String[] params) {
		return ApplicationResourcesUtil.getMessageCfg(errcode, params);
	}

	public String getErrcode() {
		return errcode;
	}

	public String[] getParams() {
		return params;
	}

	public String getErrm() {
		return errm;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public void setErrm(String errm) {
		this.errm = errm;
	}
	
	public String getExtraparam0() {
		return extraparam0;
	}

	public void setExtraparam0(String extraparam0) {
		this.extraparam0 = extraparam0;
	}
	
}
