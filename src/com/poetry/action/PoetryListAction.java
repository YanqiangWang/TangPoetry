package com.poetry.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.poetry.action.base.BaseAction;

/**
 * 选中诗题后处理
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:39:12 AM
 * @Description 选中诗题后修改相关属性
 */
public class PoetryListAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;

	@Override
	public String execute() throws Exception {
		HttpSession session = req.getSession();
		// 获取诗题列表
		List<String> poetryList = (List<String>) session.getAttribute("poetryList");

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
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
}
