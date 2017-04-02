package com.jkxy.poetry.entity;

/**
 * 页面实体
 * 
 * @author Yanqiang
 * 
 */
public class Page {
	private int currPage;
	private int pageSize;
	private int currItemNum;

	public Page(int currPage, int pageSize) {
		this.currPage = currPage;
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrItemNum() {
		currItemNum = (currPage - 1) * pageSize;
		return currItemNum;
	}

	public void setCurrItemNum(int currItemNum) {
		this.currItemNum = currItemNum;
	}
}
