package com.wg.presentation;

import java.sql.SQLException;

import com.wg.app.App;
import com.wg.controller.LoginController;
import com.wg.controller.UserController;
import com.wg.dao.UserDAO;
import com.wg.helper.Choice;
import com.wg.helper.InputSanitizer;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.UserRegisterService;

public class MenuRunner {
	private static UserDAO userDAO = new UserDAO();
	private static UserRegisterService userRegisterService = new UserRegisterService(userDAO);
	
	public static void displayStarterMenu() throws SQLException {
		while (true) {	
			System.out.println(StringConstants.STARTER_MENU);
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);

			int choice = Choice.enterChoice();

			switch (choice) {
			case 1:
				UserController.registerUser(App.scanner, userRegisterService);
				break;

			case 2:
				handleLogin();
				break;

			case 3:
				System.out.println(StringConstants.THANK_YOU_FOR_VISITING);
				return;

			default:
				System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
			}
		}
	}

	public static void handleLogin() {
		System.out.print(StringConstants.ENTER_USER_EMAIL);
		String userEmail = InputSanitizer.sanitizeEmail(App.scanner.next());

		//String userPassword = PasswordUtil.getPasswordFromUser();
		 
		
		System.out.print("Enter Password: ");
		String userPassword = InputSanitizer.sanitizePassword(App.scanner.next());

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
			System.out.println(StringConstants.INVALID_ROLE);
			return;
		}
	}

}