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
import com.wg.helper.StringConstants;
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
        	throw new IllegalArgumentException(StringConstants.USER_REGISTER_SERVICE_CANNOT_BE_NULL);
        }
    	try {
    		
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
 
            //Role userRole = Role.CUSTOMER;
 
            String userRole = "";
            boolean validRole = false;
            while(!validRole) {
            	System.out.print(StringConstants.ENTER_ROLE);
                userRole = scanner.next().toUpperCase();
                
                if(userRole.equals("CUSTOMER") || userRole.equals("EMPLOYEE") || userRole.equals("MANAGER")) {
                	validRole = true;;
                }
                else {
                	System.out.println(StringConstants.PLEASE_ENTER_A_VALID_ROLE);
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
            System.out.println(StringConstants.USER_REGISTERED_SUCCESSFULLY);
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
        	
        	System.out.println(StringConstants.LIST_OF_ALL_THE_USERS);
    		List<User> users = getAllUsers();
    		
    		int choice;
    		System.out.print(StringConstants.ENTER_USER_S_SR_NO_TO_DELETE);
    		choice = App.scanner.nextInt();
        	
            if(UserLoginService.getUserRole(users.get(choice - 1).getUserId()) == Role.ADMIN) {
            	System.out.println(StringConstants.ADMIN_CANNOT_BE_DELETED);
            	return;
            }
 
            userRegisterService.deleteUser(users.get(choice - 1).getUserEmail());
            //logger.info(USER_DELETED_SUCCESSFULLY);
 
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
    		//logger.info("Retrieving all users");
    		UserPrinter.printUsers(users);
    		return users;
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
			//logger.info("Retrieving all employees");
			UserPrinter.printUsers(employees);
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}
	
	// Getting all Managers
	public void getAllManagers() {
		try {
			List<User> managers = userRegisterService.getAllManagers(Role.MANAGER);
			//logger.info("Retrieving all managers");
			UserPrinter.printUsers(managers);
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}

	// Getting all Customers
	public void getAllCustomers() {
		try {
			List<User> customers = userRegisterService.getAllCustomers(Role.CUSTOMER);
			//logger.info("Retrieving all customers");
			UserPrinter.printUsers(customers);
		}
		catch (Exception e) {
			System.out.println(StringConstants.ERROR_RETRIEVING_EMPLOYEES + e.getMessage());
		}
		
	}
}