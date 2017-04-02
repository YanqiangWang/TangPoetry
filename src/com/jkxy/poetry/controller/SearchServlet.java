package com.jkxy.poetry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jkxy.poetry.config.Constants;
import com.jkxy.poetry.controller.base.PoetBase;
import com.jkxy.poetry.controller.base.PoetryBase;
import com.jkxy.poetry.entity.Page;
import com.jkxy.poetry.entity.Poet;
import com.jkxy.poetry.util.CommonUtils;

/**
 * 根据搜索结果修改相关属性
 * 
 * @author Yanqiang
 * 
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取method和input参数，并做简单处理
		String method = CommonUtils.getMethod(req);
		String input = CommonUtils.getInput(req);

		// 当前诗人列表页码
		int currPoetPage = 1;
		Page poetPage = new Page(currPoetPage, Constants.MAX_PAGE_ITEMS);
		// 当前诗题列表页码
		int currPoetryPage = 1;
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);

		List<Poet> poetList = PoetBase.getPoetList(method, input, poetPage);
		int totalPoetNum = PoetBase.getTotalPoetNum(method, input);

		// 获取第一个诗人姓名
		Poet poet = PoetBase.getFirstPoetName(poetList);
		String poetName = poet.getName();

		// 诗题总数
		int totalPoetryNum = PoetryBase.getTotalPoetryNum(method, input, poet
				.getId());
		// 诗题列表
		List<String> poetryList = PoetryBase.getPoetryList(method, input, poet
				.getId(), poetryPage);

		// 第一个诗题
		String poetryName = PoetryBase.getFirstPoetryName(poetryList);

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
}
