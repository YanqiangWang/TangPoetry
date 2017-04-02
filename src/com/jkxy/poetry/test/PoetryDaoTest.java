package com.jkxy.poetry.test;

import java.util.List;

import org.junit.Test;

import com.jkxy.poetry.dao.PoetryDao;
import com.jkxy.poetry.entity.Page;

public class PoetryDaoTest {
	@Test
	public void testSearchTitleByPoetId() {
		PoetryDao poetryDao = new PoetryDao();
		List<String> titles = poetryDao
				.searchTitleByPoetId(1, new Page(2, 10));
		for (String title : titles) {
			System.out.println(title);
		}
	}
}
