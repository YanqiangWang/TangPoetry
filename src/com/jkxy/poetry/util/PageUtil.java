package com.jkxy.poetry.util;

import com.jkxy.poetry.entity.Page;

/**
 * 分页标签代码生成工具类
 * 
 * @author Yanqiang
 * 
 */
public class PageUtil {

	/**
	 * 生成分页标签代码
	 * 
	 * @param tagUrl
	 *            目标URL
	 * @param totalNum
	 *            列表项总数
	 * @param pageSize
	 *            每页项数
	 * @param currPage
	 *            当前页码
	 * @param showPageNum
	 *            显示的分页标签个数
	 * @param currPageParam
	 *            设置当前页参数类别
	 * @return 分页标签代码
	 */
	public static String genPagination(String tagUrl, int totalNum, Page page,
			int showPageNum, String currPageParam) {
		// 获取page参数
		int pageSize = page.getPageSize();
		int currPage = page.getCurrPage();
		// 总页数
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		// 初始化页面代码
		StringBuffer pageCode = new StringBuffer();
		if (totalPage == 0) {
			return "未查询到数据";
		} else {
			// 上一页
			if (currPage > 1)
				pageCode.append("<li><a href='" + tagUrl + "?" + currPageParam
						+ "=" + (currPage - 1) + "'>&laquo;</a></li>");
			else {
				pageCode
						.append("<li class='disabled'><a href='#'>&laquo;</a></li>");
			}

			// 居中数字
			int minPageNum = showPageNum / 2 + 1;
			// 遍历最左边部分数字时
			if (currPage < minPageNum) {
				for (int i = 1; i <= showPageNum && i <= totalPage; i++) {
					addPageCode(pageCode, tagUrl, currPage, i, currPageParam);
				}
			}

			// 遍历最右边部分数字时
			else if (currPage > totalPage - minPageNum + 1) {
				for (int i = totalPage - showPageNum + 1; i <= totalPage; i++) {
					addPageCode(pageCode, tagUrl, currPage, i, currPageParam);
				}
			}

			// 遍历中间部分数字时
			else {
				for (int i = currPage - minPageNum + 1; i <= currPage
						+ showPageNum - minPageNum; i++) {
					addPageCode(pageCode, tagUrl, currPage, i, currPageParam);
				}
			}

			// 下一页
			if (currPage < totalPage)
				pageCode.append("<li><a href='" + tagUrl + "?" + currPageParam
						+ "=" + (currPage + 1) + "'>&raquo;</a></li>");
			else {
				pageCode
						.append("<li class='disabled'><a href='#'>&raquo;</a></li>");
			}
		}
		return pageCode.toString();
	}

	public static String genPagination(String poetName, String poetryName,
			String poetryContent) {
		poetryContent = poetryContent.replaceAll("。", "。<br/>");
		return "<div style='text-align:center'><h2>"
				+ poetryName.replaceAll("。", "·") + "</h2>" + "<h3>" + poetName
				+ "</h3>" + poetryContent + "</div>";
	}

	/**
	 * 根据当前标签选中状态添加代码
	 * 
	 * @param pageCode
	 *            页面代码
	 * @param tagUrl
	 *            目标URL
	 * @param currPage
	 *            当前页码
	 * @param iter
	 *            标签显示页码
	 * @param currPageParam
	 *            设置当前页参数类别
	 */
	private static void addPageCode(StringBuffer pageCode, String tagUrl,
			int currPage, int iter, String currPageParam) {
		if (currPage == iter) {
			pageCode.append("<li class='active'><a href='" + tagUrl + "?"
					+ currPageParam + "=" + iter + "'>" + iter + "</a></li>");
		} else {
			pageCode.append("<li><a href='" + tagUrl + "?" + currPageParam
					+ "=" + iter + "'>" + iter + "</a></li>");
		}
	}
}
