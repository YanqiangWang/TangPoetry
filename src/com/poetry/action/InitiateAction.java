package com.poetry.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.poetry.action.base.BaseAction;
import com.poetry.config.Constants;
import com.poetry.dao.PoetDao;
import com.poetry.dao.PoetryDao;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;

/**
 * 初始化Action
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:36:29 AM
 * @Description 初始化网站所需参数
 */
public class InitiateAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;

	@Override
	public String execute() {
		// 当前诗人列表页码
		int currPoetPage = 1;
		Page poetPage = new Page(currPoetPage, Constants.MAX_PAGE_ITEMS);
		// 当前诗题列表页码
		int currPoetryPage = 1;
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);

		PoetDao poetDao = new PoetDao();
		PoetryDao poetryDao = new PoetryDao();
		// 获取所有诗人数量
		int totalPoetNum = poetDao.searchAllPoet();
		// 获取诗人列表
		List<Poet> poetList = poetDao.searchAllPoet(poetPage);
		// 获取第一个诗人姓名
		Poet poet = getFirstPoetName(poetList);
		String poetName = poet.getName();
		// 诗题总数
		int totalPoetryNum = poetryDao.searchTitleByPoetId(poet.getId());
		// 诗题列表
		List<String> poetryList = poetryDao.searchTitleByPoetId(poet.getId(), poetryPage);
		// 第一个诗题
		String poetryName = getFirstPoetryName(poetryList);

		// 选中ID
		int poetItemId = 1;
		int poetryItemId = 0; // 显示网站帮助信息

		// 设置session属性初始值
		HttpSession session = req.getSession();
		// 初始化当前页码
		session.setAttribute("currPoetPage", currPoetPage);
		session.setAttribute("currPoetryPage", currPoetryPage);
		// 初始化诗人数目，首位诗人姓名，诗题数目，首个诗题
		session.setAttribute("totalPoetNum", totalPoetNum);
		session.setAttribute("poetName", poetName);
		session.setAttribute("totalPoetryNum", totalPoetryNum);
		session.setAttribute("poetryName", poetryName);
		// 诗人列表，作品列表
		session.setAttribute("poetList", poetList);
		session.setAttribute("poetryList", poetryList);
		// 选中ID
		session.setAttribute("poetItemId", poetItemId);
		session.setAttribute("poetryItemId", poetryItemId);
		// 是何种搜索结果
		session.setAttribute("method", "default");
		// 输入搜索内容
		session.setAttribute("input", "");
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
}
