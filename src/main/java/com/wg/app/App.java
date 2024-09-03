package com.wg.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.wg.presentation.MenuRunner;

public class App {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		MenuRunner.displayStarterMenu();
	
		scanner.close();
	}
}
