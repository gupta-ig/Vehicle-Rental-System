package com.wg.service;
 
import java.sql.SQLException;
import java.util.List;

import com.wg.dao.UserDAO;
import com.wg.helper.PasswordUtil;
import com.wg.model.User;
import com.wg.model.enums.Role;

public class UserLoginService {
	private UserDAO userDAO;
	 
    public UserLoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

    public User authenticate(String userEmail, String password) throws SQLException {
    	List<User> userList = userDAO.getById(userEmail);
        if (!userList.isEmpty()) {
        	for (User user : userList) {
                // Verify hashed password
//                if (PasswordUtil.checkPassword(password, user.getPassword())) {
//                    return user;
//                }
        		if(password.equals(user.getPassword())) {
        			return user;
        		}
        	}
        }
        return null; 
    }
    public Role getUserRole(String userId) throws SQLException {
        return userDAO.getUserRoleByUserId(userId);
    }
}