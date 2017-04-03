package com.poetry.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.poetry.action.base.BaseAction;
import com.poetry.config.Constants;
import com.poetry.dao.PoetryDao;
import com.poetry.entity.Poet;
import com.poetry.util.StringUtil;

/**
 * 生成页面
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:36:44 AM
 * @Description 生成页面代码
 */
public class PaginationAction extends BaseAction implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;

	public String execute() {
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
		String poetryName = (String) session.getAttribute("poetryName");

		// 获取列表
		List<String> poetListNames = new ArrayList<String>();
		List<String> poetryListNames = new ArrayList<String>();
		for (int i = 0; i < poetList.size(); i++) {
			poetListNames.add(poetList.get(i).getName());
		}
		for (int i = 0; i < poetryList.size(); i++) {
			poetryListNames.add(poetryList.get(i).replaceAll("。", "·"));
		}

		// 获取分页选项卡数据
		int pageSize = Constants.MAX_PAGE_ITEMS;
		int totalPoetPage = totalPoetNum % pageSize == 0 ? totalPoetNum / pageSize : totalPoetNum / pageSize + 1;
		int totalPoetryPage = totalPoetryNum % pageSize == 0 ? totalPoetryNum / pageSize
				: totalPoetryNum / pageSize + 1;
		int[] poetNumList = genPagination(totalPoetPage, currPoetPage, Constants.POET_PAGE_NUM);
		int[] poetryNumList = genPagination(totalPoetryPage, currPoetryPage, Constants.POETRY_PAGE_NUM);
		boolean poetLeft, poetryLeft, poetRight, poetryRight;
		poetLeft = poetryLeft = poetRight = poetryRight = false;
		if (currPoetPage > 1)
			poetLeft = true;
		if (currPoetryPage > 1)
			poetryLeft = true;
		if (currPoetPage < totalPoetPage)
			poetRight = true;
		if (currPoetryPage < totalPoetryPage)
			poetryRight = true;

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
		// 获得内容页面代码
		boolean isContentNotEmpty = false;
		if (StringUtil.isNotEmpty(poetryContent)) {
			poetryContent = poetryContent.replaceAll("。", "。<br/>");
			poetryName = poetryName.replaceAll("。", "·");
			isContentNotEmpty = true;
		}

		// 设置session属性，页面代码
		session.setAttribute("poetNumList", poetNumList);
		session.setAttribute("poetryNumList", poetryNumList);
		session.setAttribute("poetNumListLength", poetNumList.length);
		session.setAttribute("poetryNumListLength", poetryNumList.length);
		session.setAttribute("poetLeft", poetLeft);
		session.setAttribute("poetryLeft", poetryLeft);
		session.setAttribute("poetRight", poetRight);
		session.setAttribute("poetryRight", poetryRight);

		session.setAttribute("poetListNames", poetListNames);
		session.setAttribute("poetryListNames", poetryListNames);
		// content.jsp所需属性
		session.setAttribute("poetryContent", poetryContent);
		session.setAttribute("poetryName", poetryName);
		session.setAttribute("isContentNotEmpty", isContentNotEmpty);
		return SUCCESS;
	}

	/**
	 * 生成分页标签代码
	 * 
	 * @param totalPage
	 *            总页数
	 * @param currPage
	 *            当前页
	 * @param showPageNum
	 *            显示的分页标签个数
	 * @return 分页标签数组
	 */
	private int[] genPagination(int totalPage, int currPage, int showPageNum) {
		List<Integer> list = new ArrayList<Integer>();
		if (totalPage == 0) {
			return new int[0];
		} else {
			// 居中数字
			int minPageNum = showPageNum / 2 + 1;
			// 遍历最左边部分数字时
			if (currPage < minPageNum) {
				for (int i = 1; i <= showPageNum && i <= totalPage; i++) {
					list.add(i);
				}
			}

			// 遍历最右边部分数字时
			else if (currPage > totalPage - minPageNum + 1) {
				for (int i = totalPage - showPageNum + 1; i <= totalPage; i++) {
					list.add(i);
				}
			}

			// 遍历中间部分数字时
			else {
				for (int i = currPage - minPageNum + 1; i <= currPage + showPageNum - minPageNum; i++) {
					list.add(i);
				}
			}
		}
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}
}
