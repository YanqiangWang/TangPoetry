package com.poetry.test;

import java.util.List;

import org.junit.Test;

import com.poetry.dao.PoetryDao;
import com.poetry.entity.Page;

/**
 * PoetryDAO测试类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:48:28 AM
 * @Description
 */
public class PoetryDaoTest {
	@Test
	public void testSearchTitleByPoetId() {
		PoetryDao poetryDao = new PoetryDao();
		List<String> titles = poetryDao.searchTitleByPoetId(1, new Page(2, 10));
		for (String title : titles) {
			System.out.println(title);
		}
	}
}
