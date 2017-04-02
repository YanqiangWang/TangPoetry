package com.jkxy.poetry.entity;

/**
 * 诗词实体
 * 
 * @author qingt
 * 
 */
public class Poetry {
	private int id;
	private int poetId;
	private String content;
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoetId() {
		return poetId;
	}

	public void setPoetId(int poetId) {
		this.poetId = poetId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Poetry[id=" + id + ", poetId=" + poetId + ", title=" + title
				+ ", content=" + content;
	}
}
