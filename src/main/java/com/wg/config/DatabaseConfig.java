package com.wg.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
	
	private static Connection connection;
	
	private static final String URL = "jdbc:mysql://localhost:3306/VRSDB";
    private static final String USER = "root";
    private static final String PASSWORD = "MySQL@#12345";

    public static Connection getConnection() throws SQLException {
    	connection = DriverManager.getConnection(URL, USER, PASSWORD);
    	return connection;
    }
}
