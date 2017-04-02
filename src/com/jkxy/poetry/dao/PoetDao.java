package com.jkxy.poetry.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jkxy.poetry.entity.Page;
import com.jkxy.poetry.entity.Poet;
import com.jkxy.poetry.util.DBUtil;

/**
 * 诗人查询DAO
 * 
 * @author Yanqiang
 * 
 */
public class PoetDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	List<Poet> poetList = new ArrayList<Poet>();

	/**
	 * 通过ID获取诗人信息
	 * 
	 * @param id
	 *            诗人ID
	 * @return 诗人对象
	 */
	public Poet getPoetById(int id) {
		String sql = "SELECT * FROM poets WHERE id = ?";
		Poet poet = new Poet();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				poet.setId(rs.getInt("id"));
				poet.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return poet;
	}

	/**
	 * 通过name获取诗人信息
	 * 
	 * @param name
	 *            诗人姓名
	 * @return 诗人对象
	 */
	public Poet getPoetByName(String name) {
		String sql = "SELECT * FROM poets WHERE name = ?";
		Poet poet = new Poet();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				poet.setId(rs.getInt("id"));
				poet.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return poet;
	}

	/**
	 * 姓名模糊查询
	 * 
	 * @param str
	 *            诗人不完整的姓名
	 * @param page
	 *            加入limit约束
	 * @return 诗人对象列表
	 */
	public List<Poet> searchPoetByName(String name, Page page) {
		String sql = "SELECT * FROM poets WHERE name LIKE ? LIMIT ?, ?";
		poetList.clear();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name.trim() + "%");
			pstmt.setInt(2, page.getCurrItemNum());
			pstmt.setInt(3, page.getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Poet poet = new Poet();
				poet.setId(rs.getInt("id"));
				poet.setName(rs.getString("name"));
				poetList.add(poet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return poetList;
	}

	/**
	 * 姓名模糊查询
	 * 
	 * @param str
	 *            诗人不完整的姓名
	 * @return 诗人数量
	 */
	public int searchPoetByName(String name) {
		String sql = "SELECT * FROM poets WHERE name LIKE ?";
		int count = 0;
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name.trim() + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return count;
	}

	/**
	 * 查询所有诗人信息
	 * 
	 * @param page
	 *            加入limit约束
	 * 
	 * @return 诗人对象列表
	 */
	public List<Poet> searchAllPoet(Page page) {
		String sql = "SELECT * FROM poets LIMIT ?, ?";
		poetList.clear();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page.getCurrItemNum());
			pstmt.setInt(2, page.getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Poet poet = new Poet();
				poet.setId(rs.getInt("id"));
				poet.setName(rs.getString("name"));
				poetList.add(poet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return poetList;
	}

	/**
	 * 查询诗人的数量
	 * 
	 * @return 诗人数量
	 */
	public int searchAllPoet() {
		String sql = "SELECT id FROM poets";
		int totalNum = 0;
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalNum++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return totalNum;
	}
}
