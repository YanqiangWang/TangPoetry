package com.jkxy.poetry.entity;

/**
 * 诗人实体
 * 
 * @author qingt
 * 
 */
public class Poet {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Poet() {
		id = 0;
		name = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Poet[id=" + id + ", name=" + name + "]";
	}

}
