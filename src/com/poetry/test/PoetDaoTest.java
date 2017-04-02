package com.poetry.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.poetry.dao.PoetDao;
import com.poetry.entity.Page;
import com.poetry.entity.Poet;

/**
 * PoetDao测试类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:48:13 AM
 * @Description
 */
public class PoetDaoTest {
	@Test
	public void testGetPoetById() {
		PoetDao poetDao = new PoetDao();
		Poet poet = poetDao.getPoetById(9);
		Assert.assertEquals("李忱", poet.getName());
	}

	@Test
	public void testGetPoetByName() {
		PoetDao poetDao = new PoetDao();
		Poet poet = poetDao.getPoetByName("李忱");
		Assert.assertEquals(9, poet.getId());
	}

	@Test
	public void testSearchPoetByName() {
		PoetDao poetDao = new PoetDao();
		List<Poet> poetList = poetDao.searchPoetByName("李白", new Page(1, 9));
		for (Poet poet : poetList) {
			System.out.println(poet);
		}
	}

	@Test
	public void testSearchAllPoets() {
		PoetDao poetDao = new PoetDao();
		List<Poet> poetList = poetDao.searchAllPoet(new Page(10, 10));
		for (Poet poet : poetList) {
			System.out.println(poet);
		}
	}
}
