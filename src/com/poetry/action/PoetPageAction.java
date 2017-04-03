package com.poetry.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.poetry.action.base.BaseAction;
import com.poetry.config.Constants;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;

/**
 * 诗人分页按钮处理
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:38:59 AM
 * @Description 对诗人分页按钮点击后的session属性处理
 */
public class PoetPageAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;

	@Override
	public String execute() throws Exception {
		HttpSession session = req.getSession();
		// 获取当前诗人列表页码
		int currPoetPage = Integer.parseInt(req.getParameter("currPoetPage"));
		Page poetPage = new Page(currPoetPage, Constants.MAX_PAGE_ITEMS);
		// 重置当前诗题列表页码
		int currPoetryPage = 1;
		Page poetryPage = new Page(currPoetryPage, Constants.MAX_PAGE_ITEMS);
		// 获取查询方式，输入内容
		String method = (String) session.getAttribute("method");
		String input = (String) session.getAttribute("input");

		// 获取下一页的诗人列表
		List<Poet> poetList = getPoetList(method, input, poetPage);

		// 获取第一个诗人姓名
		Poet poet = getFirstPoetName(poetList);
		String poetName = poet.getName();
		// 诗题总数
		int totalPoetryNum = getTotalPoetryNum(method, input, poet.getId());
		// 诗题列表
		List<String> poetryList = getPoetryList(method, input, poet.getId(), poetryPage);

		// 第一个诗题
		String poetryName = getFirstPoetryName(poetryList);

		// 重置点击选择ID值
		int poetItemId = 1;
		int poetryItemId = 1;

		// 设置session属性初始值
		// 当前页码
		session.setAttribute("currPoetPage", currPoetPage);
		session.setAttribute("currPoetryPage", currPoetryPage);
		// 首位诗人姓名，诗题数目，首个诗题
		session.setAttribute("poetName", poetName);
		session.setAttribute("totalPoetryNum", totalPoetryNum);
		session.setAttribute("poetryName", poetryName);
		// 诗人列表，作品列表
		session.setAttribute("poetList", poetList);
		session.setAttribute("poetryList", poetryList);
		// 选中ID
		session.setAttribute("poetItemId", poetItemId);
		session.setAttribute("poetryItemId", poetryItemId);

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
}
