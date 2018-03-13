package com.ipooleth.common.utils.common;


import com.ipooleth.common.utils.Constansts;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class ApplicationResourcesUtil {
	// 异常描述资源文件
	private static final ResourceBundle errmBundle = ResourceBundle.getBundle("ApplicationResources");
	
	public static String getMessageCfg(String errcode, String[] params) {
		try {
			return params == null ? errmBundle.getString(errcode)
					: MessageFormat.format(errmBundle.getString(errcode),
							params);
		} catch (MissingResourceException e) {
			return errmBundle.getString(Constansts.NO_DEFINE_ERROR_CODE);
		}
	}
	
	public static String getMessageCfg(String errcode) {
		try {
			return errmBundle.getString(errcode);
		} catch (MissingResourceException e) {
			return errmBundle.getString(Constansts.NO_DEFINE_ERROR_CODE);
		}
	}
}
