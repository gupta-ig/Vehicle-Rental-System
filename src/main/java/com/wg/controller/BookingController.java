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

    
//	public void bookAVehicle(String userId) {
//
//		List<Vehicle> vehicles = vehicleController.getAllAvailableVehicles();
//		
//		if(vehicles == null) {
//			return;
//		}
//		
//		int choice;
//		System.out.print("Enter vehicle Sr. No. to book the vehicle: ");
//		choice = App.scanner.nextInt();
//		App.scanner.nextLine();
//		
//		
//		String vehicleId = vehicles.get(choice - 1).getVehicleId();
//		
//		try {
//			Booking newBooking = new Booking();
//			
//			newBooking.setBookingId();
//			newBooking.setCustomerId(userId);
//			newBooking.setVehicleId(vehicleId);
//		
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    		System.out.println("Enter start date and time (yyyy-MM-dd HH:mm:ss): ");
//
//			Timestamp startTime = null;
//	        while(true) {
//	        	try {
//	    			String startInput = App.scanner.nextLine();
//	    			
//	                LocalDateTime dateTime = LocalDateTime.parse(startInput, formatter);
//	                
//	                if(dateTime.isBefore(LocalDateTime.now())) {
//	                	System.err.println("The start time cannot be in the past. please enter a valid future date and time.");
//	                	continue;
//	                }
//	                
//	                startTime = Timestamp.valueOf(dateTime); 
//	                newBooking.setBookingStartTime(startTime);
//	                break;
//	                
//	            }
//	            catch (Exception e) {
//	                System.err.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
//	        		System.out.println("Enter start date and time (yyyy-MM-dd HH:mm:ss): ");
//
//	            }
//	        }
//    		System.out.println("Enter end date and time (yyyy-MM-dd HH:mm:ss): ");
//
//			Timestamp endTime = null;
//	        while(true) {
//	        	try {
//	    			String endInput = App.scanner.nextLine();
//	    			
//	                LocalDateTime dateTime = LocalDateTime.parse(endInput, formatter);
//	                
//	                if (dateTime.isBefore(LocalDateTime.now())) {
//	                    System.err.println("The end time cannot be in the past. Please enter a valid future date and time.");
//	                    continue;
//	                }
//
//	                if (startTime != null && dateTime.isBefore(startTime.toLocalDateTime())) {
//	                    System.err.println("The end time cannot be before the start time. Please enter a valid end date and time.");
//	                    continue;
//	                }
//	                
//	                endTime = Timestamp.valueOf(dateTime); 
//	                newBooking.setBookingEndTime(endTime);
//	                break;
//	            }
//	            catch (Exception e) {
//	                System.err.println("Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
//	        		System.out.println("Enter end date and time (yyyy-MM-dd HH:mm:ss): ");
//
//	            }
//	        }
//	        
//			newBooking.setBookingStatus(BookingStatus.BOOKED);
//			
//			BigDecimal totalAmount = BillingUtil.calculateTotalAmount(startTime, endTime);
//			newBooking.setTotalAmount(totalAmount);
//			
//			bookingService.bookAVehicle(newBooking);	
//			
//			// Send notification after booking is successful
//            notificationController.sendNotification(userId, "Your vehicle has been successfully booked.");
//			
//			vehicleService.changeVehicleStatus(vehicleId, AvailabilityStatus.BOOKED);
//			
//		}
//		catch (SQLException e) {
//			System.err.println("Error while booking vehicle: " + e.getMessage());
//		}
//
//	}

    public void makeBooking(Scanner scanner, String userId) {
        Timestamp startTime = null;
        Timestamp endTime = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        while (true) {
        	System.out.println("Enter start date and time (yyyy-MM-dd HH:mm:ss): ");
            String startInput = scanner.nextLine();
            // Assume formatter is defined elsewhere
            try {
                startTime = Timestamp.valueOf(LocalDateTime.parse(startInput, formatter));
                if (startTime.after(Timestamp.valueOf(LocalDateTime.now()))) {
                    break;
                } else {
                    System.out.println("Start time cannot be before the current time.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        while (true) {
            System.out.println("Enter end date and time (yyyy-MM-dd HH:mm:ss): ");
            String endInput = scanner.nextLine();
            try {
                endTime = Timestamp.valueOf(LocalDateTime.parse(endInput, formatter));
                if (endTime.after(startTime)) {
                    break;
                } else {
                    System.out.println("End time should be after start time.");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }

        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles(startTime, endTime);
            if (availableVehicles.isEmpty()) {
                System.out.println("No vehicles available for the selected time.");
            } else {
                System.out.println("Available vehicles:");
                VehiclePrinter.printVehicles(availableVehicles);
                //availableVehicles.forEach(vehicle -> System.out.println(vehicle.getVehicleName()));

                int choice;
        		System.out.print("Enter vehicle Sr. No. to book the vehicle: ");
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
                    System.out.println("Vehicle booked successfully!");
                    
                    paymentController.handlePayment(booking);
                    
                } else {
                    System.out.println("Selected vehicle is not available for the specified time.");
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred during booking: " + e.getMessage());
        }
    }
    
    
	public List<Booking> viewHistory(String userId) throws SQLException {
		
		List<Booking> bookings = bookingService.getAllBookings(userId); 
		if(bookings != null && bookings.size() > 0) {
			BookingPrinter.printBookings(bookings);
			return bookings;
		}
		else {
			System.out.println("Not booked any vehicle yet.");
		}
		return null;
	}
	
	public void cancelBooking(String userId) {
        try {
        	
        	if(viewHistory(userId) == null && viewHistory(userId).size() <= 0) {
        		System.out.println("No Past Bookings.");
        	}
        	BookingPrinter.printBookings(viewHistory(userId));
        	
        	int choice;
    		System.out.print("Enter vehicle Sr. No. to cancel the vehicle booking: ");
    		choice = App.scanner.nextInt();
    		App.scanner.nextLine();
    		
    		bookingService.cancelBooking(viewHistory(userId).get(choice - 1).getBookingId(), viewHistory(userId).get(choice - 1).getVehicleId());
    	
            System.out.println("Booking canceled successfully!");
            
        } catch (SQLException e) {
            System.err.println("Error canceling booking: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        }
    }

}
