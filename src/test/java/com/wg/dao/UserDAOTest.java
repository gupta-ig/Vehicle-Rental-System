package com.wg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.wg.model.User;
import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;

public class UserDAOTest {

	@Mock
	private Connection connection;
	
	@Mock 
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private UserDAO userDAO;
	
	private User mockUser;
	
	@BeforeEach
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		mockUser = new User(
				"123",
				"Anish",
				"Sharma",
				"0987654321",
				"anish@gmail.com",
				Gender.MALE,
				Role.MANAGER,
				new Timestamp(System.currentTimeMillis()),
				"password@123");
		
		 when(connection.prepareStatement(anyString())).thenReturn(stmt); 
	}
	
	@Test
	public void testMapResultSetToEntity() throws SQLException {
		when(resultSet.getString("user_id")).thenReturn("123");
		when(resultSet.getString("first_name")).thenReturn("Anish");
		when(resultSet.getString("last_name")).thenReturn("Sharma");
		when(resultSet.getString("phone_number")).thenReturn("0987654321");
		when(resultSet.getString("user_email")).thenReturn("anish@gmail.com");
		when(resultSet.getString("gender")).thenReturn("MALE");
		when(resultSet.getString("user_role")).thenReturn("MANAGER");
		when(resultSet.getTimestamp("created_at")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getString("user_password")).thenReturn("password@123");
		
		User resultUser = userDAO.mapResultSetToEntity(resultSet);
		
		assertNotNull(resultUser);
		assertEquals("123", resultUser.getUserId());
		assertEquals("Anish", resultUser.getFirstName());
		assertEquals("Sharma", resultUser.getLastName());
		assertEquals("0987654321", resultUser.getPhoneNumber());
		assertEquals("anish@gmail.com", resultUser.getUserEmail());
		assertEquals("MALE", resultUser.getGender().name());
		assertEquals("MANAGER", resultUser.getRole().name());
		assertNotNull(resultUser.getCreatedAt());
		assertEquals("password@123", resultUser.getPassword());
	}
	
	@Test
	public void testSetPreparedStatementForEntity() throws SQLException {
		doNothing().when(stmt).setString(anyInt(), anyString());
		
		userDAO.setPreparedStatementForEntity(stmt, mockUser);
		
		verify(stmt, times(8)).setString(anyInt(), anyString());
		verify(stmt, times(1)).setTimestamp(anyInt(), any(Timestamp.class));
	}
	
	@Test
	public void testGetUserRoleByUserId() throws SQLException {
		when(stmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true);
		when(resultSet.getString("user_role")).thenReturn(mockUser.getRole().name());
		
		Role role = userDAO.getUserRoleByUserId(mockUser.getUserId());
		
		assertNotNull(role);
		assertEquals(Role.MANAGER, mockUser.getRole());
	}
	
	@Test
	public void testGetAllEmployees() throws SQLException {
		when(stmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		
		when(resultSet.getString("user_id")).thenReturn("123");
		when(resultSet.getString("gender")).thenReturn("MALE");
		when(resultSet.getString("user_role")).thenReturn("EMPLOYEE");
		
		List<User> employees = new ArrayList<>();
		employees.add(mockUser);
		
		List<User> resultUser = userDAO.getAllEmployees(Role.EMPLOYEE);
		
		assertNotNull(resultUser);
		assertEquals(1, resultUser.size());
		assertEquals(mockUser.getUserId(), resultUser.get(0).getUserId());
	}
	
	@Test
	public void testGetAllManagers() throws SQLException {
		when(stmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		
		when(resultSet.getString("user_id")).thenReturn("123");
		when(resultSet.getString("gender")).thenReturn("MALE");
		when(resultSet.getString("user_role")).thenReturn("MANAGER");
		
		List<User> managers = new ArrayList<>();
		managers.add(mockUser);
		
		List<User> resultUser = userDAO.getAllManagers(Role.MANAGER);
		
		assertNotNull(resultUser);
		assertEquals(1, resultUser.size());
		assertEquals(mockUser.getUserId(), resultUser.get(0).getUserId());
	}
	
	@Test
	public void testGetAllCustomers() throws SQLException {
		when(stmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		
		when(resultSet.getString("user_id")).thenReturn("123");
		when(resultSet.getString("gender")).thenReturn("MALE");
		when(resultSet.getString("user_role")).thenReturn("CUSTOMER");
		
		List<User> customers = new ArrayList<>();
		customers.add(mockUser);
		
		List<User> resultUser = userDAO.getAllCustomers(Role.CUSTOMER);
		
		assertNotNull(resultUser);
		assertEquals(1, resultUser.size());
		assertEquals(mockUser.getUserId(), resultUser.get(0).getUserId());
	}
}
