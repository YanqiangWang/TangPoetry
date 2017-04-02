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
 * 选中诗题后修改相关属性
 * 
 * @author Yanqiang
 * 
 */
public class PoetListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 读取session属性值
		HttpSession session = req.getSession();

		// 获取选中的诗人ID值
		int poetItemId = Integer.parseInt((String) req
				.getParameter("poetItemId"));
		// 诗题ID重置为1
		int poetryItemId = 1;

		// 获取诗人列表
		List<Poet> poetList = (List<Poet>) session.getAttribute("poetList");

		// 获取选中诗人姓名
		Poet poet = poetList.get(poetItemId - 1);
		String poetName = poet.getName();

		// 当前诗题列表页码
		int currPoetryPage = 1;
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);

		// 搜索方式
		String method = (String) session.getAttribute("method");
		// 输入内容
		String input = (String) session.getAttribute("input");
		// 诗题列表
		List<String> poetryList = PoetryBase.getPoetryList(method, input, poet
				.getId(), poetryPage);
		// 诗题总数
		int totalPoetryNum = PoetryBase.getTotalPoetryNum(method, input, poet
				.getId());

		// 第一个诗题
		String poetryName = PoetryBase.getFirstPoetryName(poetryList);

		// 设置session属性值
		// 诗题分页重置为1
		session.setAttribute("currPoetryPage", currPoetryPage);
		// 诗人名字改变
		session.setAttribute("poetName", poetName);
		// 诗题数，首个诗题名
		session.setAttribute("totalPoetryNum", totalPoetryNum);
		session.setAttribute("poetryName", poetryName);
		// 作品列表
		session.setAttribute("poetryList", poetryList);
		// 选中ID
		session.setAttribute("poetItemId", poetItemId);
		session.setAttribute("poetryItemId", poetryItemId);

		req.getRequestDispatcher("PaginationServlet").forward(req, resp);
	}
}
