package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.dao.UserDAO;
import com.wg.helper.InputSanitizer;
import com.wg.helper.InputValidator;
import com.wg.helper.LoggingUtil;
import com.wg.helper.PasswordUtil;
import com.wg.helper.StringConstants;
import com.wg.helper.UserPrinter;
import com.wg.model.User;
import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;
import com.wg.service.UserLoginService;
import com.wg.service.UserRegisterService;
 
public class UserController {
 
	private static final Logger logger = LoggingUtil.getLogger(UserController.class);
    
    private final UserRegisterService userRegisterService;
 
    public UserController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }
    
    UserDAO userDAO = new UserDAO();
    UserLoginService userLoginService = new UserLoginService(userDAO);
    
    // Register a new User
    public static void registerUser(Scanner scanner, UserRegisterService userRegisterService) {
        if(userRegisterService == null) {
        	throw new IllegalArgumentException(StringConstants.USER_REGISTER_SERVICE_CANNOT_BE_NULL);
        }
        try {
        	System.out.println(StringConstants.REGISTER_AN_USER);
    		String firstName;
    		while(true) {
            	System.out.print(StringConstants.ENTER_FIRST_NAME);
                firstName = InputSanitizer.sanitizeName(scanner.next());
                if(InputValidator.isNameValid(firstName)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_NAME);
                }
            }
            
            System.out.print(StringConstants.ENTER_LAST_NAME);
            String lastName = InputSanitizer.sanitizeName(scanner.next());
 
            String phoneNumber;
            while(true) {
            	System.out.print(StringConstants.ENTER_PHONE_NUMBER);
                phoneNumber = InputSanitizer.sanitizePhoneNumber(scanner.next());
                if(InputValidator.isPhoneNumberValid(phoneNumber)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_PHONE_NUMBER);
                }
            }
            
            String password;
            System.out.println(StringConstants.PASSWORD_DESCRIPTION);
            
            while (true) {
            	System.out.print(StringConstants.ENTER_YOUR_PASSWORD);
                password = InputSanitizer.sanitizePassword(scanner.next());

                if (PasswordUtil.isPasswordValid(password)) {
                	break;
                } 
                else {
                	System.out.println(StringConstants.INVALID_PASSWORD_PLEASE_TRY_AGAIN);
                    System.out.println(StringConstants.PASSWORD_DESCRIPTION);
                }
            }
            
            String userPassword = PasswordUtil.hashPassword(password);
            
            String userEmail;
            while(true) {
            	System.out.print(StringConstants.ENTER_USER_EMAIL);
                userEmail = InputSanitizer.sanitizeEmail(scanner.next());
                
                if(InputValidator.isEmailValid(userEmail)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_EMAIL_ID_PLEASE_TRY_AGAIN);
                }
            }
 
            String genderInput = "";
            boolean validGender = false;
            while(!validGender) {
            	System.out.print(StringConstants.ENTER_GENDER);
                genderInput = scanner.next().toUpperCase();
                
                if( genderInput.equals("MALE") || genderInput.equals("FEMALE") || genderInput.equals("OTHER")) {
                	validGender = true;
                }
                else {
                	System.out.println(StringConstants.PLEASE_ENTER_A_VALID_GENDER);
                }
            }

            Gender gender = Gender.valueOf(genderInput);
            
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
 
//          Role userRole = Role.ADMIN;
 
            String role = "";
            boolean validRole = false;
            while(!validRole) {
            	System.out.print(StringConstants.ENTER_ROLE);
            	role = scanner.next().toUpperCase();
                
                if(role.equals("CUSTOMER") || role.equals("EMPLOYEE") || role.equals("MANAGER")) {
                	validRole = true;;
                }
                else {
                	System.out.println(StringConstants.PLEASE_ENTER_A_VALID_ROLE);
                }
            }
 
            Role userRole = Role.valueOf(role);

            User newUser = new User();
            newUser.setUserId();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setUserEmail(userEmail);
            newUser.setGender(gender);
            newUser.setRole(userRole);
            newUser.setCreatedAt(createdAt);
            newUser.setPassword(userPassword);
            
            userRegisterService.registerUser(newUser);
            System.out.println(StringConstants.USER_REGISTERED_SUCCESSFULLY);
            
            logger.info(StringConstants.USER_REGISTERED_SUCCESSFULLY);
        } 
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_REGISTERING_USER + e.getMessage());
        }
        catch (IllegalArgumentException e) {
        	 System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
    }
 
    //Delete User
    public void deleteUser(Scanner scanner) {
        try {
    		List<User> users = getAllUsers();
    		if(users == null) {
    			return;
    		}
    		
    		int index;
    		while(true) {
    			System.out.print(StringConstants.ENTER_USER_S_SR_NO_TO_DELETE);
        		index = App.scanner.nextInt();
        		if(index > 0 && index <= users.size()) {
        			break;
        		}
        		else {
        			System.out.println("Please enter valid index.");
        		}
    		}
        	
            
			if(userLoginService.getUserRole(users.get(index - 1).getUserId()) == Role.ADMIN) {
            	System.out.println(StringConstants.ADMIN_CANNOT_BE_DELETED);
            	return;
            }
            userRegisterService.deleteUser(users.get(index - 1).getUserEmail());
            System.out.println("User deleted successfully!");
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
    		if(users.size() <= 0) {
    			System.out.println("No users found.");
    			return null;
    		}
    		else {
    			System.out.println(StringConstants.DISPLAYING_ALL_USERS_LIST);
    			UserPrinter.printUsers(users);
        		return users;
    		}
    	}
    	catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_USERS + e.getMessage());
    	}
    	return null;
    }

    // Getting all employees
	public void getAllEmployees() {
		try {
			List<User> employees = userRegisterService.getAllEmployees(Role.EMPLOYEE);
			if(employees.size() <= 0) {
    			System.out.println("No employees found.");
    		}
			else {
	        	System.out.println(StringConstants.DISPLAYING_ALL_EMPLOYEES_LIST);
				UserPrinter.printUsers(employees);
			}
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}
	
	// Getting all Managers
	public void getAllManagers() {
		try {
			List<User> managers = userRegisterService.getAllManagers(Role.MANAGER);
			if(managers.size() <= 0) {
    			System.out.println("No managers found.");
    		}
			else {
	        	System.out.println(StringConstants.DISPLAYING_ALL_MANAGERS_LIST);
				UserPrinter.printUsers(managers);
			}
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}

	// Getting all Customers
	public void getAllCustomers() {
		try {
			List<User> customers = userRegisterService.getAllCustomers(Role.CUSTOMER);
			if(customers.size() <= 0) {
    			System.out.println("No customers found.");
    		}
			else {
	        	System.out.println(StringConstants.DISPLAYING_ALL_CUSTOMERS_LIST);
				UserPrinter.printUsers(customers);
			}
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}
}