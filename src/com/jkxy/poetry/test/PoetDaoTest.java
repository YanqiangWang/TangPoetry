package com.jkxy.poetry.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jkxy.poetry.dao.PoetDao;
import com.jkxy.poetry.entity.Page;
import com.jkxy.poetry.entity.Poet;

public class PoetDaoTest {
	@Test
	public void testGetPoetById() {
		PoetDao poetDao = new PoetDao();
		Poet poet = poetDao.getPoetById(9);
		Assert.assertEquals("李忱", poet.getName());
	}

//	@Test
	public void testGetPoetByName() {
		PoetDao poetDao = new PoetDao();
		Poet poet = poetDao.getPoetByName("李忱");
		Assert.assertEquals(9, poet.getId());
	}

//	 @Test
	public void testSearchPoetByName() {
		PoetDao poetDao = new PoetDao();
		List<Poet> poetList = poetDao.searchPoetByName("李白", new Page(1, 9));
		for (Poet poet : poetList) {
			System.out.println(poet);
		}
	}

//	@Test
	public void testSearchAllPoets() {
		PoetDao poetDao = new PoetDao();
		List<Poet> poetList = poetDao.searchAllPoet(new Page(10, 10));
		for (Poet poet : poetList) {
			System.out.println(poet);
		}
	}
}
