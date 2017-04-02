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
 * 诗歌查询DAO
 * @author Yanqiang
 *
 */
public class PoetryDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	List<Poet> list = new ArrayList<Poet>();
	List<String> titles = new ArrayList<String>();

	/**
	 * 通过诗人ID查询诗词标题数量
	 * 
	 * @param poetId
	 *            诗人ID
	 * @return 诗词数量
	 */
	public int searchTitleByPoetId(int poetId) {
		String sql = "SELECT id FROM poetries WHERE poet_id = ?";
		conn = DBUtil.getConnection();
		int totalNum = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, poetId);
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

	/**
	 * 通过诗人ID搜索诗词标题
	 * 
	 * @param poetId
	 *            诗人ID
	 * @param page
	 *            加入limit约束
	 * @return 诗题字符串列表
	 */
	public List<String> searchTitleByPoetId(int poetId, Page page) {
		String sql = "SELECT title FROM poetries WHERE poet_id = ? LIMIT ?, ?";
		titles.clear();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, poetId);
			pstmt.setInt(2, page.getCurrItemNum());
			pstmt.setInt(3, page.getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				titles.add(title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return titles;
	}

	/**
	 * 通过标题查询诗人列表
	 * 
	 * @param title
	 *            标题
	 * @param page
	 *            加入limit限定
	 * @return 诗人列表
	 */
	public List<Poet> searchPoetByPoetryTitle(String title, Page page) {
		String sql = "SELECT DISTINCT a.* FROM poets a, poetries b WHERE a.id = b.poet_id AND b.title LIKE ? LIMIT ?, ?";
		return fuzzySearchPoet(sql, title, page);
	}

	/**
	 * 通过标题查询诗人列表
	 * 
	 * @param title
	 *            标题
	 * @return 诗人数量
	 */
	public int searchPoetByPoetryTitle(String title) {
		String sql = "SELECT DISTINCT a.* FROM poets a, poetries b WHERE a.id = b.poet_id AND b.title LIKE ?";
		return fuzzySearchPoet(sql, title);
	}

	/**
	 * 通过内容查询诗人列表
	 * 
	 * @param content
	 *            标题
	 * @param page
	 *            加入limit限定
	 * @return 诗人列表
	 */
	public List<Poet> searchPoetByPoetryContent(String content, Page page) {
		String sql = "SELECT DISTINCT a.* FROM poets a, poetries b WHERE a.id = b.poet_id AND b.content LIKE ? limit ?, ?";
		return fuzzySearchPoet(sql, content, page);
	}

	/**
	 * 通过内容查询诗人数量
	 * 
	 * @param content
	 *            标题
	 * @return 诗人数量
	 */
	public int searchPoetByPoetryContent(String content) {
		String sql = "SELECT DISTINCT a.* FROM poets a, poetries b WHERE a.id = b.poet_id AND b.content LIKE ?";
		return fuzzySearchPoet(sql, content);
	}

	/**
	 * 模糊查询复用方法
	 * 
	 * @param sql
	 *            查询语句
	 * @param str
	 *            查询方式
	 * @param page
	 *            limit限制
	 * @return 诗人列表
	 */
	private List<Poet> fuzzySearchPoet(String sql, String str, Page page) {
		list.clear();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setInt(2, page.getCurrItemNum());
			pstmt.setInt(3, page.getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Poet poet = new Poet();
				poet.setId(rs.getInt("id"));
				poet.setName(rs.getString("name"));
				list.add(poet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 模糊查询复用方法
	 * 
	 * @param sql
	 *            查询语句
	 * @param str
	 *            查询方式
	 * @return 诗人数量
	 */
	private int fuzzySearchPoet(String sql, String str) {
		conn = DBUtil.getConnection();
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
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
	 * 通过作者ID和诗词名联合查询内容
	 * 
	 * @param poetId
	 *            诗人ID
	 * @param title
	 *            标题
	 * @return 诗词内容
	 */
	public String getContentByTitle(int poetId, String title) {
		String sql = "SELECT content FROM poetries WHERE poet_id = ? AND title = ?";
		conn = DBUtil.getConnection();
		String content = "";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, poetId);
			pstmt.setString(2, title);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				content = rs.getString("content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return content;
	}

	/**
	 * 根据诗人及标题模糊匹配获得标题列表
	 * 
	 * @param title
	 *            诗词不完整标题
	 * @param poetId
	 *            诗人ID
	 * @param page
	 *            加入limit约束
	 * @return 诗题列表
	 */
	public List<String> searchTitleByPoetAndTitle(String title, int poetId,
			Page page) {
		String sql = "SELECT title FROM poetries WHERE title LIKE ? AND poet_id = ? limit ?,?";
		return fuzzySearchTitle(sql, title, poetId, page);
	}

	/**
	 * 根据诗人及标题模糊匹配获得标题数量
	 * 
	 * @param title
	 *            诗词不完整标题
	 * @param poetId
	 *            诗人ID
	 * @param page
	 *            加入limit约束
	 * @return 诗题数量
	 */
	public int searchTitleByPoetAndTitle(String title, int poetId) {
		String sql = "SELECT title FROM poetries WHERE title LIKE ? AND poet_id = ?";
		return fuzzySearchTitle(sql, title, poetId);
	}

	/**
	 * 根据诗人及诗词内容模糊匹配获得标题列表
	 * 
	 * @param content
	 *            诗词不完整诗句
	 * @param poetId
	 *            诗人ID
	 * @param page
	 *            加入limit约束
	 * @return 诗题列表
	 */
	public List<String> searchTitleByPoetAndContent(String content, int poetId,
			Page page) {
		String sql = "SELECT title FROM poetries WHERE content LIKE ? AND poet_id = ? limit ?,?";
		return fuzzySearchTitle(sql, content, poetId, page);
	}

	/**
	 * 根据诗人及诗词内容模糊匹配获得标题数量
	 * 
	 * @param content
	 *            诗词不完整诗句
	 * @param poetId
	 *            诗人ID
	 * @return 诗题数量
	 */
	public int searchTitleByPoetAndContent(String content, int poetId) {
		String sql = "SELECT title FROM poetries WHERE content LIKE ? AND poet_id = ?";
		return fuzzySearchTitle(sql, content, poetId);
	}

	/**
	 * 标题模糊查询复用方法
	 * 
	 * @param sql
	 *            查询语句
	 * @param str
	 *            查询方式
	 * @param poetId
	 *            诗人ID
	 * @param page
	 *            加入limit约束
	 * @return 诗题列表
	 */
	private List<String> fuzzySearchTitle(String sql, String str, int poetId,
			Page page) {
		titles.clear();
		conn = DBUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setInt(2, poetId);
			pstmt.setInt(3, page.getCurrItemNum());
			pstmt.setInt(4, page.getPageSize());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				titles.add(title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, pstmt, conn);
		}
		return titles;
	}

	/**
	 * 标题模糊查询复用方法
	 * 
	 * @param sql
	 *            查询语句
	 * @param str
	 *            查询方式
	 * @param poetId
	 *            诗人ID
	 * @return 诗题数量
	 */
	private int fuzzySearchTitle(String sql, String str, int poetId) {
		conn = DBUtil.getConnection();
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + str + "%");
			pstmt.setInt(2, poetId);
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
}
