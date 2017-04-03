package com.poetry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poetry.controller.base.BaseServlet;

/**
 * 选中诗题后处理Servlet
 * @author Yanqiang
 * @date Apr 3, 2017 1:39:12 AM
 * @Description 选中诗题后修改相关属性
 */
public class PoetryListServlet extends BaseServlet {
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
		// 获取诗题列表
		List<String> poetryList = (List<String>) session
				.getAttribute("poetryList");

		// 获取选中的诗题ID
		String poetryItemIdStr = (String) req.getParameter("poetryItemId");
		int poetryItemId = Integer.parseInt(poetryItemIdStr);
		// 获取选中的诗题
		String poetryName = poetryList.get(poetryItemId - 1);

		// 设置session属性值
		// 选中诗题名称
		session.setAttribute("poetryName", poetryName);
		// 选中诗题ID
		session.setAttribute("poetryItemId", poetryItemId);

		req.getRequestDispatcher("PaginationServlet").forward(req, resp);
	}
}
