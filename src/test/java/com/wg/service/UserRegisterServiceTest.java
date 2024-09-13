package com.wg.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wg.dao.UserDAO;
import com.wg.model.User;
import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;

class UserRegisterServiceTest {
	
	@Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserRegisterService userRegisterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	@Test
    void testRegisterUserSuccess() throws SQLException {
        when(userDAO.getById("john.doe@example.com")).thenReturn(Collections.emptyList());

        // Create a user
        User user = new User(
            "1", "John", "Doe", "1234567890", "john.doe@example.com", Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");

        userRegisterService.registerUser(user);
        verify(userDAO, times(1)).add(user);
    }
	
    @Test
    void testRegisterUserEmailExists() throws SQLException {
        User existingUser = new User("1", "John", "Doe", "1234567890", "john.doe@example.com", Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getById("john.doe@example.com")).thenReturn(Arrays.asList(existingUser));
       
        User newUser = new User("2", "Jane", "Doe", "0987654321", "john.doe@example.com", Gender.FEMALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");

        // Assert that an exception is thrown
        assertThrows(IllegalArgumentException.class, () -> userRegisterService.registerUser(newUser), "Email already exists.");

        verify(userDAO, never()).add(any(User.class));
    } 

    // Test getting a user by ID
    @Test
    void testGetUserById() throws SQLException {
        User user = new User("1", "John", "Doe", "1234567890", "john.doe@example.com", Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getById("1")).thenReturn(Arrays.asList(user));

        List<User> result = userRegisterService.getUserById("1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());

        verify(userDAO, times(1)).getById("1");
    }

    // Test deleting a user
    @Test
    void testDeleteUser() throws SQLException {
        userRegisterService.deleteUser("john.doe@example.com");

        verify(userDAO, times(1)).delete("john.doe@example.com");
    }

    // Test getting all users
    @Test
    void testGetAllUsers() throws SQLException {
        User user1 = new User("1", "John", "Doe", "1234567890", "john.doe@example.com", Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        User user2 = new User("2", "Jane", "Doe", "0987654321", "jane.doe@example.com", Gender.FEMALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userRegisterService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(userDAO, times(1)).getAll();
    }

    // Test getting all employees
    @Test
    void testGetAllEmployees() throws SQLException {
        User employee = new User("1", "Employee", "Smith", "1234567890", "employee.smith@example.com", Gender.MALE, Role.EMPLOYEE, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getAllEmployees(Role.EMPLOYEE)).thenReturn(Arrays.asList(employee));

        List<User> result = userRegisterService.getAllEmployees(Role.EMPLOYEE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Role.EMPLOYEE, result.get(0).getRole());

        verify(userDAO, times(1)).getAllEmployees(Role.EMPLOYEE);
    }
    
    // Test getting all managers
    @Test
    void testGetAllManagers() throws SQLException {
        User manager = new User("1", "Manager", "Smith", "1234567890", "manager.smith@example.com", Gender.MALE, Role.MANAGER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getAllManagers(Role.MANAGER)).thenReturn(Arrays.asList(manager));

        List<User> result = userRegisterService.getAllManagers(Role.MANAGER);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Role.MANAGER, result.get(0).getRole());

        verify(userDAO, times(1)).getAllManagers(Role.MANAGER);
    }
    
    // Test getting all customers
    @Test
    void testGetAllCustomers() throws SQLException {
        User customer = new User("1", "Customer", "Smith", "1234567890", "customer.smith@example.com", Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), "hashedPassword");
        when(userDAO.getAllCustomers(Role.CUSTOMER)).thenReturn(Arrays.asList(customer));

        List<User> result = userRegisterService.getAllCustomers(Role.CUSTOMER);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Role.CUSTOMER, result.get(0).getRole());

        verify(userDAO, times(1)).getAllCustomers(Role.CUSTOMER);
    }

}
