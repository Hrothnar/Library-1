package com.tk.neo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConnection {
	private static Connection connection;
	@Value("${url}")
	private String url;
	@Value("${username_bd}")
	private String username;
	@Value("${password}")
	private String password;

	public Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
