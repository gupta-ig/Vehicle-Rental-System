package com.wg.service;
 
import java.sql.SQLException;
import java.util.List;

import com.wg.dao.UserDAO;
import com.wg.model.User;
import com.wg.model.enums.Role;
 
public class UserRegisterService {
    private UserDAO userDAO;
 
    public UserRegisterService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// Register a new user
    public void registerUser(User user) throws SQLException {
        List<User> existingUser = userDAO.getById(user.getUserEmail());
        if(!existingUser.isEmpty()) {
            throw new IllegalArgumentException("Email already exists.");
        }
        userDAO.add(user);
    }
 
    // Get User By Id
    public List<User> getUserById(String userId) throws SQLException {
        return userDAO.getById(userId);
    }
 
    // Update a user
//    public void updateUser(User user) throws SQLException {
//        Optional<User> existingUser = userDAO.getById(user.getUserId());
//        if(existingUser.isPresent()) {
//            userDAO.update(user, user.getUserId());
//        } else {
//            throw new IllegalArgumentException("User ID does not exist.");
//        }
//    }
 
    // Delete a user
    public void deleteUser(String userEmail) throws SQLException {
        userDAO.delete(userEmail);
    }
 
    // Get all users
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAll();
    }

    // Get all Employees
	public List<User> getAllEmployees(Role role) throws SQLException {
		return userDAO.getAllEmployees(role);
	}
	
	// Get all Managers
		public List<User> getAllManagers(Role role) throws SQLException {
			return userDAO.getAllManagers(role);
		}

	// Get all Customers
	public List<User> getAllCustomers(Role role) throws SQLException {
		return userDAO.getAllCustomers(role);
	}
}