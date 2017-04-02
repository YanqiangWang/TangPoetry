package com.jkxy.poetry.util;

/**
 * 字符串工具类
 * 
 * @author Yanqiang
 * 
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
