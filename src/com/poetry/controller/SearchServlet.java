package com.poetry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poetry.config.Constants;
import com.poetry.controller.base.BaseServlet;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;
import com.poetry.util.StringUtil;

/**
 * 搜索处理Servlet
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:39:41 AM
 * @Description 根据搜索结果修改相关属性
 */
public class SearchServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取method和input参数，并做简单处理
		String method = getMethod(req);
		String input = getInput(req);

		// 当前诗人列表页码
		int currPoetPage = 1;
		Page poetPage = new Page(currPoetPage, Constants.MAX_PAGE_ITEMS);
		// 当前诗题列表页码
		int currPoetryPage = 1;
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);

		List<Poet> poetList = getPoetList(method, input, poetPage);
		int totalPoetNum = getTotalPoetNum(method, input);

		// 获取第一个诗人姓名
		Poet poet = getFirstPoetName(poetList);
		String poetName = poet.getName();

		// 诗题总数
		int totalPoetryNum = getTotalPoetryNum(method, input, poet.getId());
		// 诗题列表
		List<String> poetryList = getPoetryList(method, input, poet.getId(), poetryPage);

		// 第一个诗题
		String poetryName = getFirstPoetryName(poetryList);

		// 重置session属性
		HttpSession session = req.getSession();
		// 重置当前页码
		session.setAttribute("currPoetPage", currPoetPage);
		session.setAttribute("currPoetryPage", currPoetryPage);
		// 选中ID
		session.setAttribute("poetItemId", 1);
		session.setAttribute("poetryItemId", 1);
		// 是何种搜索结果
		session.setAttribute("method", method);
		// 输入搜索内容
		session.setAttribute("input", input);
		// 初始化诗人数目，首位诗人姓名，诗题数目，首个诗题
		session.setAttribute("totalPoetNum", totalPoetNum);
		session.setAttribute("poetName", poetName);
		session.setAttribute("totalPoetryNum", totalPoetryNum);
		session.setAttribute("poetryName", poetryName);
		// 诗人列表，作品列表
		session.setAttribute("poetList", poetList);
		session.setAttribute("poetryList", poetryList);

		req.getRequestDispatcher("PaginationServlet").forward(req, resp);
	}

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
			/*
			 * input = new String(req.getParameter("searchText").getBytes(
			 * "iso-8859-1"), "UTF-8");
			 */
		}
		return input;
	}
}
