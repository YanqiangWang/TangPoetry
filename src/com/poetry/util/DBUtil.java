package com.poetry.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 数据库连接工具类
 * 
 * @author Yanqiang
 * @date Apr 3, 2017 1:42:33 AM
 * @Description
 */
public class DBUtil {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static Connection conn;

	/**
	 * 加载配置文件
	 */
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("dbinfo");
		DRIVER = bundle.getString("DRIVER");
		URL = bundle.getString("URL");
		USER = bundle.getString("USER");
		PASSWORD = bundle.getString("PASSWORD");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return 新建的一个连接
	 */
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 * 
	 * @param rs
	 *            结果集
	 * @param stmt
	 *            会话
	 * @param conn
	 *            连接
	 */
	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
