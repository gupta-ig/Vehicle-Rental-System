package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.model.User;
import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;
 
public class UserDAO extends GenericDAO<User, String> {

	@Override
    protected String getTableName() {
        return "USERS";
    }
 
    @Override
    protected User mapResultSetToEntity(ResultSet result) throws SQLException {
    	//System.out.println("Map function called");
        return new User(
        	result.getString("user_id"),
            result.getString("first_name"),
            result.getString("last_name"),
            result.getString("phone_number"),
            result.getString("user_email"),
            Gender.valueOf(result.getString("gender")),
            Role.valueOf(result.getString("user_role")),
            result.getTimestamp("created_at"),
            result.getString("user_password")
        );
    }
 
    @Override
    protected void setPreparedStatementForEntity(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getUserId());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setString(4, user.getPhoneNumber());
        stmt.setString(5, user.getUserEmail());
        stmt.setString(6, user.getGender().name());
        stmt.setString(7, user.getRole().name());
        stmt.setTimestamp(8, user.getCreatedAt());
        stmt.setString(9, user.getPassword());
    }
 
    @Override
    protected String getPrimaryKeyColumn() {
        return "user_email";
    }
 
    @Override
    protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String userId) throws SQLException {
        stmt.setString(1, userId);
    }
 
    @Override
    protected String getPlaceholders() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?";
    }
 
    public Role getUserRoleByUserId(String userId) {
    	String SELECT_QUERY = "SELECT user_role FROM " + getTableName() + " WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
            stmt.setString(1, userId);
            try (ResultSet result = stmt.executeQuery()) {
            	if(result.next()) {
            		String roleStr = result.getString("user_role");
                    return Role.valueOf(roleStr);
            	}
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user role: " + e.getMessage());
        }
		return null;
    }

	public List<User> getAllEmployees(Role role) throws SQLException {
		String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE user_role = ?";
		List<User> employees = new ArrayList<>();
		try(PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
			stmt.setString(1, role.name());
			try(ResultSet result = stmt.executeQuery()) {
				while(result.next()) {
					employees.add(mapResultSetToEntity(result));
				}
			}
		}
		return employees;
	}
	
	public List<User> getAllManagers(Role role) throws SQLException {
		String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE user_role = ?";
		List<User> managers = new ArrayList<>();
		try(PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
			stmt.setString(1, role.name());
			try(ResultSet result = stmt.executeQuery()) {
				while(result.next()) {
					managers.add(mapResultSetToEntity(result));
				}
			}
		}
		return managers;
	}

	public List<User> getAllCustomers(Role role) throws SQLException {
		String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE user_role = ?";
		List<User> customers = new ArrayList<>();
		try(PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
			stmt.setString(1, role.name());
			try(ResultSet result = stmt.executeQuery()) {
				while(result.next()) {
					customers.add(mapResultSetToEntity(result));
				}
			}
		}
		return customers;
	}

	
}
 