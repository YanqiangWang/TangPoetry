package com.jkxy.poetry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jkxy.poetry.config.Constants;
import com.jkxy.poetry.controller.base.PoetryBase;
import com.jkxy.poetry.entity.Page;
import com.jkxy.poetry.entity.Poet;

/**
 * 对诗题分页按钮点击后的session属性处理
 * 
 * @author Yanqiang
 * 
 */
public class PoetryPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		// 获取当前诗题列表页码
		int currPoetryPage = Integer.parseInt(req
				.getParameter("currPoetryPage"));
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);
		// 获取当前已选择的诗人
		List<Poet> poetList = (List<Poet>) session.getAttribute("poetList");
		int poetItemId = (Integer) session.getAttribute("poetItemId");
		Poet poet = new Poet();
		if (poetList.size() > 0)
			poet = poetList.get(poetItemId - 1);

		// 获取查询方式，输入内容
		String method = (String) session.getAttribute("method");
		String input = (String) session.getAttribute("input");

		// 分搜索方式获取诗题列表
		List<String> poetryList = PoetryBase.getPoetryList(method, input, poet
				.getId(), poetryPage);

		// 第一个诗题
		String poetryName = PoetryBase.getFirstPoetryName(poetryList);

		// 重置诗题列表ID
		int poetryItemId = 1;

		// 设置session属性初始值
		// 当前页码
		session.setAttribute("currPoetryPage", currPoetryPage);
		// 首个诗题
		session.setAttribute("poetryName", poetryName);
		session.setAttribute("poetryList", poetryList);
		// 选中ID
		session.setAttribute("poetryItemId", poetryItemId);

		req.getRequestDispatcher("PaginationServlet").forward(req, resp);
	}
}