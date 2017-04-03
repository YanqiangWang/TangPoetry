package com.poetry.controller.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;

/**
 * 自定义Servlet基类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:40:01 AM
 * @Description
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类搜索方式：获取诗题列表
	 * 
	 * @param method
	 * @return
	 */
	public static List<String> getPoetryList(String method, String input, int poetId, Page poetryPage) {
		PoetryDao poetryDao = new PoetryDao();
		List<String> poetryList = new ArrayList<String>();
		if (method.equals("title")) {
			poetryList = poetryDao.searchTitleByPoetAndTitle(input, poetId, poetryPage);
		} else if (method.equals("content")) {
			poetryList = poetryDao.searchTitleByPoetAndContent(input, poetId, poetryPage);
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
			totalPoetryNum = poetryDao.searchTitleByPoetAndContent(input, poetId);
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
	public static List<Poet> getPoetList(String method, String input, Page poetPage) {
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
