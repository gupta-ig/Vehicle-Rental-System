package com.wg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wg.dao.UserDAO;
import com.wg.model.User;
import com.wg.model.enums.Role;

class UserLoginServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserLoginService userLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateSuccess() throws SQLException {
        // Mock the scenario where user exists and password is correct
        User user = new User(
            "1", "John", "Doe", "1234567890", "john.doe@example.com", 
            com.wg.model.enums.Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()), 
            "hashedPassword"
        );

        when(userDAO.getById("john.doe@example.com")).thenReturn(Collections.singletonList(user));
//        when(PasswordUtil.checkPassword("password", "$10$d0/HtbP1X1Tk7eeySw31P.sv9QgFgZkOeaw8OY8uoo21VShdScMsC")).thenReturn(true);

        // Call the method
        User authenticatedUser = userLoginService.authenticate("john.doe@example.com", "hashedPassword");

        // Verify the user is authenticated
        assertNotNull(authenticatedUser);
        assertEquals("john.doe@example.com", authenticatedUser.getUserEmail());
    }

    @Test
    void testAuthenticateInvalidPassword() throws SQLException {
        // Mock the scenario where user exists but password is incorrect
        User user = new User(
            "1", "John", "Doe", "1234567890", "john.doe@example.com", 
            com.wg.model.enums.Gender.MALE, Role.CUSTOMER, new Timestamp(System.currentTimeMillis()),
            "$10$d0/HtbP1X1Tk7eeySw31P.sv9QgFgZkOeaw8OY8uoo21VShdScMsC"
        );

        when(userDAO.getById("john.doe@example.com")).thenReturn(Collections.singletonList(user));
//       when(PasswordUtil.checkPassword("wrongPassword", "$10$d0/HtbP1X1Tk7eeySw31P.sv9QgFgZkOeaw8OY8uoo21VShdScMsC")).thenReturn(false);

        // Call the method
        User authenticatedUser = userLoginService.authenticate("john.doe@example.com", "wrongPassword");

        // Verify that no user is authenticated
        assertNull(authenticatedUser);
    }

    @Test
    void testAuthenticateUserNotFound() throws SQLException {
        // Mock the scenario where no user is found
        when(userDAO.getById("nonexistent@example.com")).thenReturn(Collections.emptyList());

        // Call the method
        User authenticatedUser = userLoginService.authenticate("nonexistent@example.com", "password");

        // Verify that no user is authenticated
        assertNull(authenticatedUser);
    }

    @Test
    void testGetUserRoleSuccess() throws SQLException {
        // Mock the scenario where the role is returned
        when(userDAO.getUserRoleByUserId("1")).thenReturn(Role.CUSTOMER);

        // Call the method
        Role role = userLoginService.getUserRole("1");

        // Verify that the role is returned correctly
        assertEquals(Role.CUSTOMER, role);
    }
}
