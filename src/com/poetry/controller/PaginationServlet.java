package com.poetry.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poetry.config.Constants;
import com.poetry.controller.base.BaseServlet;
import com.poetry.dao.PoetryDao;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;
import com.poetry.util.ListUtil;
import com.poetry.util.PageUtil;
import com.poetry.util.StringUtil;

/**
 * 生成页面Servlet
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:36:44 AM
 * @Description 生成页面代码
 */
public class PaginationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取session中保存的属性
		HttpSession session = req.getSession();
		List<Poet> poetList = (List<Poet>) session.getAttribute("poetList");
		List<String> poetryList = (List<String>) session.getAttribute("poetryList");
		int totalPoetNum = (Integer) session.getAttribute("totalPoetNum");
		int totalPoetryNum = (Integer) session.getAttribute("totalPoetryNum");
		int currPoetPage = (Integer) session.getAttribute("currPoetPage");
		int currPoetryPage = (Integer) session.getAttribute("currPoetryPage");
		int poetItemId = (Integer) session.getAttribute("poetItemId");
		int poetryItemId = (Integer) session.getAttribute("poetryItemId");
		String poetName = (String) session.getAttribute("poetName");
		String poetryName = (String) session.getAttribute("poetryName");

		// 生成Page对象
		Page poetPage = new Page(currPoetPage, Constants.MAX_PAGE_ITEMS);
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);

		// 获得诗词内容
		PoetryDao poetryDao = new PoetryDao();
		String poetryContent = "";
		// 判断列表是非为空，如果未查到结果，自然不用写内容到右栏
		if (poetList.size() > 0 && poetryList.size() > 0)
			// 如果poetryItemId == 0,默认显示主页提示信息
			if (poetItemId > 0 && poetryItemId > 0) {
				poetryContent = poetryDao.getContentByTitle(poetList.get(poetItemId - 1).getId(),
						poetryList.get(poetryItemId - 1));
			}
		// 获取列表代码
		String poetListCode = ListUtil.genPoetList(poetList);
		String poetryListCode = ListUtil.genPoetryList(poetryList);

		// 获取分页选项卡代码
		String poetPageCode = PageUtil.genPagination("PoetPageServlet", totalPoetNum, poetPage, Constants.POET_PAGE_NUM,
				"currPoetPage");
		String poetryPageCode = PageUtil.genPagination("PoetryPageServlet", totalPoetryNum, poetryPage,
				Constants.POETRY_PAGE_NUM, "currPoetryPage");
		// 获得内容页面代码
		String contentCode = "";
		if (StringUtil.isNotEmpty(poetryContent)) {
			contentCode = PageUtil.genPagination(poetName, poetryName, poetryContent);
		} else {
			// 默认显示主页提示信息
			contentCode = Constants.CONTENT_CODE;
		}

		// 设置session属性，页面代码
		session.setAttribute("poetPageCode", poetPageCode);
		session.setAttribute("poetryPageCode", poetryPageCode);
		session.setAttribute("poetListCode", poetListCode);
		session.setAttribute("poetryListCode", poetryListCode);
		session.setAttribute("contentCode", contentCode);

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
