package com.jkxy.poetry.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 通用工具类
 * 
 * @author Yanqiang
 * 
 */
public class CommonUtils {
	/**
	 * 获取method参数
	 * 
	 * @param req
	 * @return
	 */
	public static String getMethod(HttpServletRequest req) {
		String method = req.getParameter("method");
		if (StringUtil.isEmpty(method))
			method = "default";
		return method;
	}

	/**
	 * 获取input参数
	 * 
	 * @param req
	 * @return
	 * @throws ServletException
	 */
	public static String getInput(HttpServletRequest req) throws IOException {
		String input = req.getParameter("searchText");
		if (StringUtil.isEmpty(input)) {
			// 避免空指针异常
			input = "";
		} else {
			// 解决编码转换问题
			input = new String(req.getParameter("searchText").getBytes(
					"iso-8859-1"), "UTF-8");
		}
		return input;
	}
}
