package com.poetry.util;

import java.util.List;

import com.poetry.entity.Poet;

/**
 * 列表页面代码生成工具类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:43:15 AM
 * @Description
 */
public class ListUtil {
	/**
	 * 由当前诗人列表生成页面代码
	 * 
	 * @param poetList
	 *            诗人列表
	 * @return 诗人列表页面代码
	 */
	public static String genPoetList(List<Poet> poetList) {
		StringBuffer poetListCode = new StringBuffer();
		for (int i = 0; i < poetList.size(); i++) {
			poetListCode.append("<a href='PoetListServlet?poetItemId=" + (i + 1) + "' class='list-group-item'>"
					+ poetList.get(i).getName() + "</a>");
		}
		return poetListCode.toString();
	}

	/**
	 * 由当前诗题列表生成页面代码
	 * 
	 * @param poetryList
	 *            诗题列表
	 * @return 诗题列表页面代码
	 */
	public static String genPoetryList(List<String> poetryList) {
		StringBuffer poetryListCode = new StringBuffer();
		for (int i = 0; i < poetryList.size(); i++) {
			poetryListCode.append("<a href='PoetryListServlet?poetryItemId=" + (i + 1) + "' class='list-group-item'>"
					+ poetryList.get(i).replaceAll("。", "·") + "</a>");
		}
		return poetryListCode.toString();
	}
}
