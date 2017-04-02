package com.jkxy.poetry.controller.base;

import java.util.ArrayList;
import java.util.List;

import com.jkxy.poetry.dao.PoetryDao;
import com.jkxy.poetry.entity.Page;

/**
 * 诗歌工具类
 * 
 * @author Yanqiang
 * 
 */
public class PoetryBase {
	/**
	 * 分类搜索方式：获取诗题列表
	 * 
	 * @param method
	 * @return
	 */
	public static List<String> getPoetryList(String method, String input,
			int poetId, Page poetryPage) {
		PoetryDao poetryDao = new PoetryDao();
		List<String> poetryList = new ArrayList<String>();
		if (method.equals("title")) {
			poetryList = poetryDao.searchTitleByPoetAndTitle(input, poetId,
					poetryPage);
		} else if (method.equals("content")) {
			poetryList = poetryDao.searchTitleByPoetAndContent(input, poetId,
					poetryPage);
		} else {
			poetryList = poetryDao.searchTitleByPoetId(poetId, poetryPage);
		}
		return poetryList;
	}

	/**
	 * 分类搜索方式：获取诗题数量
	 * 
	 * @param method
	 * @return
	 */
	public static int getTotalPoetryNum(String method, String input, int poetId) {
		PoetryDao poetryDao = new PoetryDao();
		int totalPoetryNum = 0;
		if (method.equals("title")) {
			totalPoetryNum = poetryDao.searchTitleByPoetAndTitle(input, poetId);
		} else if (method.equals("content")) {
			totalPoetryNum = poetryDao.searchTitleByPoetAndContent(input,
					poetId);
		} else {
			totalPoetryNum = poetryDao.searchTitleByPoetId(poetId);
		}
		return totalPoetryNum;
	}

	/**
	 * 获取诗题列表的第一个诗题
	 * 
	 * @param poetryList
	 * @return
	 */
	public static String getFirstPoetryName(List<String> poetryList) {
		String poetryName = "";
		if (poetryList.size() > 0) {
			poetryName = poetryList.get(0);
		}
		return poetryName;
	}
}
