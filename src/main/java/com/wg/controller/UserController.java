package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.helper.InputSanitizer;
import com.wg.helper.InputValidator;
import com.wg.helper.LoggingHelper;
import com.wg.helper.PasswordUtil;
import com.wg.helper.UserPrinter;
import com.wg.model.User;
import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;
import com.wg.service.UserLoginService;
import com.wg.service.UserRegisterService;
 
public class UserController {
 
	private static final Logger logger = LoggingHelper.getLogger(UserController.class);
    
    private final UserRegisterService userRegisterService;
 
    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }
    
    
    // Register a new User
    public static void registerUser(Scanner scanner, UserRegisterService userRegisterService) {
        if(userRegisterService == null) {
        	throw new IllegalArgumentException("UserRegisterService cannot be null");
        }
    	try {
    		
    		String firstName;
    		while(true) {
            	System.out.print("Enter First Name: ");
                firstName = InputSanitizer.sanitizeName(scanner.next());
                if(InputValidator.isNameValid(firstName)) {
                	break;
                }
                else {
                	System.out.println("Invalid Name");
                }
            }
            
            System.out.print("Enter Last Name: ");
            String lastName = InputSanitizer.sanitizeName(scanner.next());
 
            String phoneNumber;
            while(true) {
            	System.out.print("Enter Phone Number: ");
                phoneNumber = InputSanitizer.sanitizePhoneNumber(scanner.next());
                if(InputValidator.isPhoneNumberValid(phoneNumber)) {
                	break;
                }
                else {
                	System.out.println("Invalid Phone Number");
                }
            }
            
            String password;
            System.out.println("Password must be at least 12 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character.");
            
            while (true) {
            	System.out.print("Enter your password: ");
                password = InputSanitizer.sanitizePassword(scanner.next());

                if (PasswordUtil.isPasswordValid(password)) {
                	break;
                } 
                else {
                	System.out.println("Invalid password. Please try again.");
                    System.out.println("Password must be at least 12 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character.");
                }
            }
            
            String userPassword = PasswordUtil.hashPassword(password);
            
            String userEmail;
            while(true) {
            	System.out.print("Enter User Email: ");
                userEmail = InputSanitizer.sanitizeEmail(scanner.next());
                
                if(InputValidator.isEmailValid(userEmail)) {
                	break;
                }
                else {
                	System.out.println("Invalid email id. Please try again.");
                }
            }
 
            String genderInput = "";
            boolean validGender = false;
            while(!validGender) {
            	System.out.print("Enter Gender (MALE, FEMALE, OTHER): ");
                genderInput = scanner.next().toUpperCase();
                
                if( genderInput.equals("MALE") || genderInput.equals("FEMALE") || genderInput.equals("OTHER")) {
                	validGender = true;
                }
                else {
                	System.out.println("Please enter a valid gender.");
                }
            }

            Gender gender = Gender.valueOf(genderInput);
            
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
 
            //Role userRole = Role.CUSTOMER;
 
            String userRole = "";
            boolean validRole = false;
            while(!validRole) {
            	System.out.print("Enter Role (CUSTOMER, EMPLOYEE, MANAGER): ");
                userRole = scanner.next().toUpperCase();
                
                if(userRole.equals("CUSTOMER") || userRole.equals("EMPLOYEE") || userRole.equals("MANAGER")) {
                	validRole = true;;
                }
                else {
                	System.out.println("Please enter a valid role.");
                }
            }
 
            Role role = Role.valueOf(userRole);

            User newUser = new User();
            newUser.setUserId();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setUserEmail(userEmail);
            newUser.setGender(gender);
            newUser.setRole(role);
            newUser.setCreatedAt(createdAt);
            newUser.setPassword(userPassword);
            
            userRegisterService.registerUser(newUser);
            System.out.println("User registered successfully!\n");
//            logger.info("User Authenticated Successfully! Useremail: " + userEmail);
        } 
        catch (SQLException e) {
            System.err.println("Error while registering user: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	 System.err.println("Validation Error: " + e.getMessage());
        }
    }
 
    //Delete User
    public void deleteUser(Scanner scanner) {
        try {
        	
        	System.out.println("List of all the users: ");
    		List<User> users = getAllUsers();
    		
    		int choice;
    		System.out.print("Enter user's sr. No. to delete: ");
    		choice = App.scanner.nextInt();
        	
            if(UserLoginService.getUserRole(users.get(choice - 1).getUserId()) == Role.ADMIN) {
            	System.out.println("Admin cannot be deleted");
            	return;
            }
 
            userRegisterService.deleteUser(users.get(choice - 1).getUserEmail());
            logger.info("User deleted successfully");
 
        }
        catch (SQLException e) {
            System.err.println("Error while deleting user: " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
        	 System.err.println("Validation Error: " + e.getMessage());
        }
    }
    
    // View All Users
    public List<User> getAllUsers() {
    	try {
    		List<User> users = userRegisterService.getAllUsers();
    		logger.info("Retrieving all users");
    		UserPrinter.printUsers(users);
    		return users;
    	}
    	catch (Exception e) {
    		System.out.println("Error retrieving users: " + e.getMessage());
    	}
    	return null;
    }

    // Getting all employees
	public void getAllEmployees() {
		try {
			List<User> employees = userRegisterService.getAllEmployees(Role.EMPLOYEE);
			logger.info("Retrieving all employees");
			UserPrinter.printUsers(employees);
		}
		catch (Exception e) {
			System.out.println("Error retrieving employees: " + e.getMessage());
		}
		
	}
	
	// Getting all Managers
	public void getAllManagers() {
		try {
			List<User> managers = userRegisterService.getAllManagers(Role.MANAGER);
			logger.info("Retrieving all managers");
			UserPrinter.printUsers(managers);
		}
		catch (Exception e) {
			System.out.println("Error retrieving employees: " + e.getMessage());
		}
		
	}

	// Getting all Customers
	public void getAllCustomers() {
		try {
			List<User> customers = userRegisterService.getAllCustomers(Role.CUSTOMER);
			logger.info("Retrieving all customers");
			UserPrinter.printUsers(customers);
		}
		catch (Exception e) {
			System.out.println("Error retrieving employees: " + e.getMessage());
		}
		
	}
}