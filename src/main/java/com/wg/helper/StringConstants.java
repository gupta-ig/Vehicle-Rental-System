package com.wg.helper;

public class StringConstants {
	
	public static final String STARTER_MENU = """

			-------- WELCOME TO VEHICLE RENTAL SYSTEM --------
			
			1. Register a new user
			2. Login
			3. Exit
			
			""";

	public static final String ADMIN_MENU = """
			
			--------Welcome Admin--------
			
			1. User Management
			2. Vehicle Management
			3. Review Management
			4. Complaint Management
			5. Logout
			
	        """;
	
	public static final String USER_MANAGEMENT = """
			
			--------User Management--------
			
			1. Get all Users
			2. Get all Customers
			3. Get all Employees
			4. Get all Managers
			5. Register a user
			6. Delete a user
			7. Back to Main Menu
			
			""";
	
	public static final String VEHICLE_MANAGEMENT = """
			
			--------Vehicle Management--------
			
			1. Get all Vehicles
			2. Get all Maintenance Vehicles
			3. Add a Vehicle
			4. Remove a Vehicle
			5. Back to Main Menu
			
			""";
	
	public static final String REVIEW_MANAGEMENT = """
			
			--------Review Management--------
			
			1. View all Review
			2. Delete a Review
			3. Back to Main Menu
			
			""";
	
	public static final String COMPLAINT_MANAGEMENT = """
			
			--------Complaint Management--------
			
			1. View all Complaints
			2. Update Complaint Status
			3. Back to Main Menu
			
			""";
	
	public static final String CUSTOMER_MENU = """
			
			--------Welcome Customer--------
			
			1. Book a vehicle
			2. Return a vehicle
			3. View booking history
			4. Would you like to give a review?
			5. See all reviews added by you
			6. Want to raise complaint?
			7. See all complaints raised by you
			8. Cancel booking
			9. Logout
			
	        """;
	
	public static final String EMPLOYEE_MENU = """
			
			--------Welcome Employee--------
			
	        1. Get All Customers.
	        2. Get All Vehicles.
	        3. Get All Maintenance Vehicles.
	        4. Want to raise complaint?
	        5. See complaints raised by you
	        6. Update Vehicle Status.
	        7. Logout.
	        
	        """;

	public static final String MANAGER_MENU = """
			
			--------Welcome Manager--------
			
	        1. Get All Vehicles.
	        2. Get All Maintenance Vehicles.
	        3. Update Vehicle Status.
	        4. Want to raise complaint?
	        5. Logout.
	        
	        """;

	public static final String NO_REVIEWS_PUBLISHED = "No reviews published.";

	public static final String START_TIME_CANNOT_BE_BEFORE_THE_CURRENT_TIME = "Start time cannot be before the current time.";
	public static final String ENTER_START_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS = "Enter start date and time (yyyy-MM-dd HH:mm:ss): ";
	public static final String ENTER_END_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS = "Enter end date and time (yyyy-MM-dd HH:mm:ss): ";
	public static final String ENTER_RETURN_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS = "Enter return date and time (yyyy-MM-dd HH:mm:ss): ";
	public static final String END_TIME_SHOULD_BE_AFTER_START_TIME = "End time should be after start time.";
	public static final String INVALID_DATE_FORMAT_PLEASE_TRY_AGAIN = "Invalid date format. Please try again.";
	public static final String NO_VEHICLES_AVAILABLE_FOR_THE_SELECTED_TIME = "No vehicles available for the selected time.";
	public static final String AVAILABLE_VEHICLES = "Available vehicles:";
	public static final String VEHICLE_SR_NO_TO_BOOK_THE_VEHICLE = "Enter vehicle Sr. No. to book the vehicle: ";
	public static final String VEHICLE_BOOKED_SUCCESSFULLY = "Vehicle booked successfully!";
	public static final String SELECTED_VEHICLE_IS_NOT_AVAILABLE_FOR_THE_SPECIFIED_TIME = "Selected vehicle is not available for the specified time.";
	public static final String AN_ERROR_OCCURRED_DURING_BOOKING = "An error occurred during booking: ";
	public static final String NOT_BOOKED_ANY_VEHICLE_YET = "Not booked any vehicle yet.";
	public static final String VEHICLE_SR_NO_TO_CANCEL_THE_VEHICLE_BOOKING = "Enter vehicle Sr. No. to cancel the vehicle booking: ";
	public static final String ERROR_CANCELING_BOOKING = "Error canceling booking: ";
	public static final String BOOKING_CANCELED_SUCCESSFULLY = "Booking canceled successfully!";
	public static final String NO_PAST_BOOKINGS = "No Past Bookings.";
	public static final String INVALID_INPUT = "Invalid input: ";
	public static final String ERROR_RETURNING_VEHICLE = "Error returning vehicle: ";
	public static final String VEHICLE_SR_NO_TO_RETURN_THE_VEHICLE_BOOKING = "Enter vehicle Sr. No. to return the vehicle booking: ";
	public static final String VEHICLE_RETURNED_SUCCESSFULLY = "Vehicle returned successfully!";
	
	public static final String ERROR_PROCESSING_PAYMENT = "Error processing payment: ";
	public static final String PAYMENT_PROCESSED_SUCCESSFULLY = "Payment processed successfully!";
	public static final String ENTER_PAYMENT_METHOD = "Enter payment method(CASH, CARD, UPI): ";
	public static final String UPI = "UPI";
	public static final String CARD = "CARD";
	public static final String CASH = "CASH";
	public static final String PLEASE_ENTER_VALID_INPUT = "Please enter valid input.";
	public static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static final String THE_RETURN_TIME_CANNOT_BE_IN_PAST = "The return time cannot be in past.";

	public static final String INVALID_VEHICLE_TYPE = "Invalid vehicle type. Please specify 'CAR' or 'BIKE'.";

	public static final String INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE = "Invalid choice. Please enter valid choice.";
	public static final String THANK_YOU_FOR_VISITING = "Thank you for visiting...";
	public static final String DO_YOU_WANT_TO_CONTINUE = "Do you want to continue? (Y/N): ";
	public static final String LOGGING_OUT = "Logging out...";
	public static final String CANCEL_BOOKING = "Cancel Booking";
	public static final String VIEW_ALL_COMPLAINTS = "View all complaints";
	public static final String RAISING_A_COMPLAINT = "Raising a complaint";
	public static final String SEE_ALL_REVIEWS = "See all reviews";
	public static final String GIVE_A_REVIEW = "Give a review";
	public static final String YOUR_BOOKING_HISTORY = "Your booking history";
	public static final String RETURNING_A_VEHICLE = "Returning a vehicle";
	public static final String START_BOOKING_A_VEHICLE = "Start booking a Vehicle";
	public static final String ENTER_YOUR_CHOICE = "Enter your choice: ";
	

    public static final String NO_COMPLAINT_FOUND_WITH_THE_PROVIDED_ID = "No complaint found with the provided ID.";
    public static final String ERROR_RETRIEVING_REVIEWS = "Error retrieving reviews: ";
    public static final String VALIDATION_ERROR = "Validation Error: ";
    public static final String ERROR_WHILE_DELETING_REVIEW = "Error while deleting review: ";
    public static final String REVIEW_DELETED_SUCCESSFULLY = "Review deleted successfully!";
    public static final String ENTER_REVIEW_SR_NO_TO_DELETE = "Enter review sr. No. to delete: ";
    public static final String LIST_OF_ALL_THE_REVIEWS = "List of all the reviews: ";
    public static final String ERROR_ADDING_REVIEW = "Error adding review: ";
    public static final String REVIEW_ADDED_SUCCESSFULLY = "Review added successfully.";
    public static final String YOUR_REVIEW_DESCRIPTION = "Enter your review description: ";
    public static final String PLEASE_ENTER_A_VALID_NUMBER = "Please enter a valid number.";
    public static final String ENTER_RATING = "Enter rating (1-5): ";
    
    public static final String ERROR_OCCURRED_WHILE_RETRIEVING_COMPLAINTS = "Error occurred while retrieving complaints: ";
	public static final String ERROR_OCCURRED_WHILE_SUBMITTING_THE_COMPLAINT = "Error occurred while submitting the complaint: ";
	public static final String COMPLAINT_SUBMITTED_SUCCESSFULLY = "Complaint submitted successfully!";
	public static final String ENTER_THE_COMPLAINT_DESCRIPTION = "Enter the complaint description: ";

	public static final String DISPLAYING_CUSTOMERS_LIST = "Displaying customers list";
	public static final String DISPLAYING_VEHICLES_LIST = "Displaying vehicles list";
	public static final String UPDATING_VEHICLE_STATUS = "Updating vehicle status";
	public static final String DISPLAY_ALL_THE_MAINTENANCE_VEHICLE = "Display all the maintenance vehicle";
	
	public static final String ENTER_VEHICLE_S_SR_NO_TO_BE_DELETED = "Enter vehicle's sr. no. to be deleted: ";
	public static final String ERROR_WHILE_CHANGING_STATUS = "Error while changing status: ";
	public static final String VEHICLE_STATUS_CHANGED_SUCCESSFULLY = "Vehicle status changed successfully!";
	public static final String PLEASE_ENTER_A_VALID_STATUS = "Please enter a valid status.";
	public static final String VEHICLE_STATUS_TO_BE_CHANGED_INTO = "Enter vehicle status to be changed into(AVAILABLE, BOOKED, MAINTENANCE): ";
	public static final String ENTER_VEHICLE_SR_NO_TO_CHANGE_STATUS = "Enter vehicle sr. No. to change status: ";
	public static final String ERROR_RETRIEVING_MAINTENANCE_VEHICLES = "Error retrieving maintenance vehicles: ";
	public static final String GETTING_ALL_MAINTENANCE_VEHICLES = "Getting all maintenance vehicles";
	public static final String AN_ERROR_OCCURRED_WHILE_RETRIEVING_AVAILABLE_VEHICLES = "An error occurred while retrieving available vehicles: ";
	public static final String NO_VEHICLES_AVAILABLE_FOR_THE_SPECIFIED_TIME = "No vehicles available for the specified time.";
	public static final String ERROR_RETRIEVING_VEHICLES = "Error retrieving vehicles: ";
	public static final String NO_VEHICLES_AVAILABLE = "No vehicles available.";
	public static final String ERROR_WHILE_DELETING_VEHICLE = "Error while deleting vehicle: ";
	public static final String VEHICLE_REMOVED_SUCCESSFULLY = "Vehicle removed successfully!";
	public static final String ERROR_WHILE_REGISTERING_VEHICLE = "Error while registering vehicle: ";
	public static final String VEHICLE_REGISTERED_SUCCESSFULLY = "Vehicle registered successfully!\n";
	public static final String VEHICLE_TYPE = "Enter vehicle type (BIKE, CAR): ";
	public static final String VEHICLE_MANUFACTURE_YEAR = "Enter vehicle manufacture year: ";
	public static final String VEHICLE_REGISTERATION_NUMBER = "Enter vehicle registeration number: ";
	public static final String VEHICLE_MODEL = "Enter vehicle model: ";
	public static final String VEHICLE_MANUFACTURER = "Enter vehicle manufacturer: ";
	
	public static final String ERROR_RETRIEVING_EMPLOYEES = "Error retrieving employees: ";
	public static final String ERROR_RETRIEVING_USERS = "Error retrieving users: ";
	public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
	public static final String ADMIN_CANNOT_BE_DELETED = "Admin cannot be deleted";
	public static final String ENTER_USER_S_SR_NO_TO_DELETE = "Enter user's sr. No. to delete: ";
	public static final String LIST_OF_ALL_THE_USERS = "List of all the users: ";
	public static final String ERROR_WHILE_REGISTERING_USER = "Error while registering user: ";
	public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully!\n";
	public static final String PLEASE_ENTER_A_VALID_ROLE = "Please enter a valid role.";
	public static final String ENTER_ROLE = "Enter Role (CUSTOMER, EMPLOYEE, MANAGER): ";
	public static final String PLEASE_ENTER_A_VALID_GENDER = "Please enter a valid gender.";
	public static final String ENTER_GENDER = "Enter Gender (MALE, FEMALE, OTHER): ";
	public static final String INVALID_EMAIL_ID_PLEASE_TRY_AGAIN = "Invalid email id. Please try again.";
	public static final String ENTER_USER_EMAIL = "Enter User Email: ";
	public static final String INVALID_PASSWORD_PLEASE_TRY_AGAIN = "Invalid password. Please try again.";
	public static final String ENTER_YOUR_PASSWORD = "Enter your password: ";
	public static final String PASSWORD_DESCRIPTION = "Password must be at least 12 characters long, contain at least one digit, one uppercase letter, one lowercase letter, and one special character.";
	public static final String ENTER_FIRST_NAME = "Enter First Name: ";
	public static final String INVALID_NAME = "Invalid Name";
	public static final String ENTER_LAST_NAME = "Enter Last Name: ";
	public static final String ENTER_PHONE_NUMBER = "Enter Phone Number: ";
	public static final String INVALID_PHONE_NUMBER = "Invalid Phone Number";
	public static final String USER_REGISTER_SERVICE_CANNOT_BE_NULL = "UserRegisterService cannot be null";

	public static final String ERROR_RETRIEVING_NOTIFICATIONS = "Error retrieving notifications: ";
	public static final String ERROR_SENDING_NOTIFICATION = "Error sending notification: ";
	public static final String NOTIFICATION_SENT_SUCCESSFULLY = "Notification sent successfully.";
	
	public static final String COMPLAINT_STATUS_CHANGED_SUCCESSFULLY = "Complaint status changed successfully!";
	public static final String ENTER_THE_COMPLAINT_STATUS_YOU_WANT_TO_CHANGE_YOUR_COMPLAINT = "Enter the complaint status you want to change your complaint(UNDER_PROCESS, DECLINED, RESOLVED): ";

	public static final String NO_COMPLAINTS_REGISTERED = "No complaints registered.";

	public static final String INVALID_ROLE = "Invalid role.";
	
	public static final String UPDATING_COMPLAINT_STATUS = "Updating complaint status";
	public static final String DELETE_REVIEWS = "Delete Reviews";
	public static final String VIEWING_REVIEWS = "Viewing Reviews";
	public static final String DELETE_A_USER = "Delete a user";
	public static final String REGISTER_AN_USER = "Register an user";
	public static final String DISPLAYING_ALL_MANAGERS_LIST = "Displaying all managers list";
	public static final String DISPLAYING_ALL_EMPLOYEES_LIST = "Displaying all employees list";
	public static final String DISPLAYING_ALL_CUSTOMERS_LIST = "Displaying all customers list";
	public static final String DISPLAYING_ALL_USERS_LIST = "Displaying all users list";
	public static final String ADD_A_VEHICLE = "Adding a Vehicle";
	public static final String REMOVE_A_VEHICLE = "Removing a vehicle";
	public static final String ERROR_UPDATING_COMPLAINT_STATUS = "Error updating complaint status ";
	public static final String PLEASE_ENTER_VALID_INDEX = "Please enter valid index.";
	public static final String THE_COMPLAINT_ALREADY_HAVE_SAME_STATUS = "The complaint already have same status.";
	
}
