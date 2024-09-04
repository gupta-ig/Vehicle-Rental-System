package com.wg.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.wg.app.App;
import com.wg.dao.NotificationDAO;
import com.wg.dao.PaymentDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.BillingUtil;
import com.wg.helper.BookingPrinter;
import com.wg.helper.StringConstants;
import com.wg.helper.VehiclePrinter;
import com.wg.model.Booking;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.BookingStatus;
import com.wg.service.BookingService;
import com.wg.service.NotificationService;
import com.wg.service.PaymentService;
import com.wg.service.VehicleService;

public class BookingController {
	
	BookingService bookingService;

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	
	//private static final Logger logger = LoggingHelper.getLogger(BookingController.class);
    
	private static VehicleDAO vehicleDAO = new VehicleDAO();
	private static VehicleService vehicleService = new VehicleService(vehicleDAO);
	private static VehicleController vehicleController = new VehicleController(vehicleService); 
	
	NotificationDAO notificationDAO = new NotificationDAO();
    NotificationService notificationService = new NotificationService(notificationDAO);
    NotificationController notificationController = new NotificationController(notificationService);

    PaymentDAO paymentDAO = new PaymentDAO();
    PaymentService paymentService = new PaymentService(paymentDAO);
    PaymentController paymentController = new PaymentController(paymentService);


    public void makeBooking(Scanner scanner, String userId) {
        Timestamp startTime = null;
        Timestamp endTime = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConstants.DATE_FORMATTER);
        
        while (true) {
        	System.out.println(StringConstants.ENTER_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS);
            String startInput = scanner.nextLine();
            try {
                startTime = Timestamp.valueOf(LocalDateTime.parse(startInput, formatter));
                if (startTime.after(Timestamp.valueOf(LocalDateTime.now()))) {
                    break;
                } else {
                    System.out.println(StringConstants.START_TIME_CANNOT_BE_BEFORE_THE_CURRENT_TIME);
                }
            } catch (Exception e) {
                System.out.println(StringConstants.INVALID_DATE_FORMAT_PLEASE_TRY_AGAIN);
            }
        }

        while (true) {
            System.out.println(StringConstants.ENTER_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS);
            String endInput = scanner.nextLine();
            try {
                endTime = Timestamp.valueOf(LocalDateTime.parse(endInput, formatter));
                if (endTime.after(startTime)) {
                    break;
                } else {
                    System.out.println(StringConstants.END_TIME_SHOULD_BE_AFTER_START_TIME);
                }
            } catch (Exception e) {
                System.out.println(StringConstants.INVALID_DATE_FORMAT_PLEASE_TRY_AGAIN);
            }
        }

        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles(startTime, endTime);
            if (availableVehicles.isEmpty()) {
                System.out.println(StringConstants.NO_VEHICLES_AVAILABLE_FOR_THE_SELECTED_TIME);
            } else {
                System.out.println(StringConstants.AVAILABLE_VEHICLES);
                VehiclePrinter.printVehicles(availableVehicles);
                //availableVehicles.forEach(vehicle -> System.out.println(vehicle.getVehicleName()));

                int choice;
        		System.out.print(StringConstants.VEHICLE_SR_NO_TO_BOOK_THE_VEHICLE);
        		choice = App.scanner.nextInt();
        		App.scanner.nextLine();
        		
        		Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
                
        		Booking booking = new Booking();
                booking.setBookingId();
                booking.setCustomerId(userId);
                booking.setVehicleId(availableVehicles.get(choice - 1).getVehicleId());
                booking.setBookingStartTime(startTime);
                booking.setBookingEndTime(endTime);
                booking.setBookingStatus(BookingStatus.BOOKED);
                booking.setCreatedAt(createdAt);
                
        		
                if (bookingService.isVehicleAvailable(availableVehicles.get(choice - 1).getVehicleId(), startTime, endTime)) {
                    bookingService.bookVehicle(booking);
                    System.out.println(StringConstants.VEHICLE_BOOKED_SUCCESSFULLY);
                    
                    paymentController.handlePayment(booking);
                    
                } else {
                    System.out.println(StringConstants.SELECTED_VEHICLE_IS_NOT_AVAILABLE_FOR_THE_SPECIFIED_TIME);
                }
            }
        } catch (SQLException e) {
            System.out.println(StringConstants.AN_ERROR_OCCURRED_DURING_BOOKING + e.getMessage());
        }
    }
    
    
	public List<Booking> viewHistory(String userId) throws SQLException {
		
		List<Booking> bookings = bookingService.getAllBookings(userId); 
		if(bookings != null && bookings.size() > 0) {
			BookingPrinter.printBookings(bookings);
			return bookings;
		}
		else {
			System.out.println(StringConstants.NOT_BOOKED_ANY_VEHICLE_YET);
		}
		return null;
	}
	
	public void cancelBooking(String userId) {
        try {
        	
        	if(viewHistory(userId) == null && viewHistory(userId).size() <= 0) {
        		System.out.println(StringConstants.NO_PAST_BOOKINGS);
        	}
        	BookingPrinter.printBookings(viewHistory(userId));
        	
        	int choice;
    		System.out.print(StringConstants.VEHICLE_SR_NO_TO_CANCEL_THE_VEHICLE_BOOKING);
    		choice = App.scanner.nextInt();
    		App.scanner.nextLine();
    		
    		bookingService.cancelBooking(viewHistory(userId).get(choice - 1).getBookingId(), viewHistory(userId).get(choice - 1).getVehicleId());
    	
            System.out.println(StringConstants.BOOKING_CANCELED_SUCCESSFULLY);
            
        } catch (SQLException e) {
            System.err.println(StringConstants.ERROR_CANCELING_BOOKING + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(StringConstants.INVALID_INPUT + e.getMessage());
        }
    }

	public void returnVehicle(String userId) {
        try {
        	if(viewHistory(userId) == null && viewHistory(userId).size() <= 0) {
        		System.out.println(StringConstants.NO_PAST_BOOKINGS);
        	}
        	BookingPrinter.printBookings(viewHistory(userId));
        	
        	int choice;
    		System.out.print(StringConstants.VEHICLE_SR_NO_TO_RETURN_THE_VEHICLE_BOOKING);
    		choice = App.scanner.nextInt();
    		App.scanner.nextLine();
    		
    		bookingService.returnVehicle(viewHistory(userId).get(choice - 1).getBookingId());
    		
    		System.out.println(StringConstants.VEHICLE_RETURNED_SUCCESSFULLY);
        }
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_RETURNING_VEHICLE + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(StringConstants.INVALID_INPUT + e.getMessage());
        }
		
        
    }
	
}
