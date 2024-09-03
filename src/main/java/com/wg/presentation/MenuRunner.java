package com.wg.presentation;

import java.sql.SQLException;

import com.wg.app.App;
import com.wg.controller.LoginController;
import com.wg.controller.UserController;
import com.wg.dao.UserDAO;
import com.wg.helper.InputSanitizer;
import com.wg.helper.PasswordUtil;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.UserRegisterService;

public class MenuRunner {

	private static UserDAO userDAO = new UserDAO();
	private static UserRegisterService userRegisterService = new UserRegisterService(userDAO);
	
	public static void displayStarterMenu() throws SQLException {
		while (true) {
			System.out.println(StringConstants.STARTER_MENU);
			System.out.print("Enter Your Choice: ");

			int choice = App.scanner.nextInt();
			App.scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				UserController.registerUser(App.scanner, userRegisterService);
				break;

			case 2:
				handleLogin();
				break;

			case 3:
				System.out.println("Thank you for visiting!");
				return;

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public static void handleLogin() {
		System.out.print("Enter User Email: ");
		String userEmail = InputSanitizer.sanitizeEmail(App.scanner.next());

		String userPassword = PasswordUtil.getPasswordFromUser();
		 
		
//		System.out.print("Enter Password: ");
//		String userPassword = InputSanitizer.sanitizePassword(App.scanner.next());

		LoginController.authenticateUser(userEmail, userPassword);
	}

	public static void displayRoleBasedMenu(User user) throws SQLException {
		switch (user.getRole()) {
		case ADMIN:
			AdminMenu.displayAdminMenu(user);
			break;
		case CUSTOMER:
			CustomerMenu.displayCustomerMenu(user);
			break;
		case EMPLOYEE:
			EmployeeMenu.displayEmployeeMenu(user);
			break;
		case MANAGER:
			ManagerMenu.displayManagerMenu(user);
			break;
		default:
			System.out.println("Invalid role.");
			return;
		}
	}

}