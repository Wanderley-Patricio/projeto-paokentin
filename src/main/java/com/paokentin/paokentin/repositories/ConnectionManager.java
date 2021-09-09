package com.paokentin.paokentin.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String DB = "projetopaokentin";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB;

	private static Connection conn = null;

	static Connection getCurrentConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		return conn;
	}

	static Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
