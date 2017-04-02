package com.jkxy.poetry.controller.base;

import java.util.ArrayList;
import java.util.List;

import com.jkxy.poetry.dao.PoetDao;
import com.jkxy.poetry.dao.PoetryDao;
import com.jkxy.poetry.entity.Page;
import com.jkxy.poetry.entity.Poet;

/**
 * 诗人工具类
 * 
 * @author Yanqiang
 * 
 */
public class PoetBase {
	/**
	 * 获取诗人列表的第一个诗人
	 * 
	 * @param poetList
	 * @return
	 */
	public static Poet getFirstPoetName(List<Poet> poetList) {
		Poet poet = new Poet();
		if (poetList.size() > 0) {
			poet = poetList.get(0);
		}
		return poet;
	}

	/**
	 * 分查询方式处理:作者列表
	 * 
	 * @param method
	 * @param input
	 * @param poetPage
	 * @return
	 */
	public static List<Poet> getPoetList(String method, String input,
			Page poetPage) {
		PoetDao poetDao = new PoetDao();
		PoetryDao poetryDao = new PoetryDao();
		List<Poet> poetList = new ArrayList<Poet>();
		// 根据作者查询
		if (method.equals("author")) {
			poetList = poetDao.searchPoetByName(input, poetPage);
		}
		// 根据诗题查询
		else if (method.equals("title")) {
			poetList = poetryDao.searchPoetByPoetryTitle(input, poetPage);
		}
		// 根据诗词内容查询
		else if (method.equals("content")) {
			poetList = poetryDao.searchPoetByPoetryContent(input, poetPage);
		}
		// 未经搜索
		else {
			poetList = poetDao.searchAllPoet(poetPage);
		}
		return poetList;
	}

	/**
	 * 分查询方式处理:作者数量
	 * 
	 * @param method
	 * @param input
	 * @return
	 */
	public static int getTotalPoetNum(String method, String input) {
		PoetDao poetDao = new PoetDao();
		PoetryDao poetryDao = new PoetryDao();
		int totalPoetNum = 0;
		// 根据作者查询
		if (method.equals("author")) {
			totalPoetNum = poetDao.searchPoetByName(input);
		}
		// 根据诗题查询
		else if (method.equals("title")) {
			totalPoetNum = poetryDao.searchPoetByPoetryTitle(input);
		}
		// 根据诗词内容查询
		else if (method.equals("content")) {
			totalPoetNum = poetryDao.searchPoetByPoetryContent(input);
		}
		return totalPoetNum;
	}
}
