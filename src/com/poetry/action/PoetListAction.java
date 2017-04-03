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
 * 选中诗题
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:38:29 AM
 * @Description 选中诗题后修改相关属性
 */
public class PoetListAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;

	@Override
	public String execute() throws Exception {
		// 读取session属性值
		HttpSession session = req.getSession();

		// 获取选中的诗人ID值
		int poetItemId = Integer.parseInt((String) req.getParameter("poetItemId"));
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
		List<String> poetryList = getPoetryList(method, input, poet.getId(), poetryPage);
		// 诗题总数
		int totalPoetryNum = getTotalPoetryNum(method, input, poet.getId());

		// 第一个诗题
		String poetryName = getFirstPoetryName(poetryList);

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

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
}
