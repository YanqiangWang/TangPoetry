package com.poetry.util;

/**
 * 自定义String工具类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:50:04 AM
 * @Description
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            待判断的字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 *            待判断的字符串
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
