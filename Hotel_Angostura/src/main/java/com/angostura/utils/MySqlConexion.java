package com.angostura.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConexion {
	public static Connection getConexion() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url,user,pass;
			user="root";
			pass="mysql";
			url="jdbc:mysql://localhost:3306/BDAngostura?serverTimezone=UTC";
			cn = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
