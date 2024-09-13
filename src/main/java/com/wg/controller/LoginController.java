package com.wg.controller;
 
import java.sql.SQLException;

import com.wg.model.User;
import com.wg.presentation.MenuRunner;
import com.wg.service.UserLoginService;
 
public class LoginController {
 
    private static UserLoginService userLoginService;
    
    public LoginController(UserLoginService userLoginService) {
		super();
		this.userLoginService = userLoginService;
	}

	public static void authenticateUser(String userEmail, String password) {
        if (userEmail == null || userEmail.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Credentials can't be empty");
            return;
        }
 
        try {
            User user = userLoginService.authenticate(userEmail, password);
            if (user != null) {
                MenuRunner.displayRoleBasedMenu(user);
            } else {
                System.out.println("Invalid userEmail or password");
            }
        } 
        catch (SQLException e) {
            System.out.println("An error occurred during authentication: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
 