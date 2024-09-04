package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.wg.app.App;
import com.wg.helper.StringConstants;
import com.wg.model.Booking;
import com.wg.model.enums.PaymentMethod;
import com.wg.service.PaymentService;

public class PaymentController {
	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	public void handlePayment(Booking booking) {

		String paymentInput = "";
		boolean validInput = false;
        while(!validInput) {
        	System.out.print(StringConstants.ENTER_PAYMENT_METHOD);
            paymentInput = App.scanner.next().toUpperCase();
            
            if(paymentInput.equals(StringConstants.CASH) || paymentInput.equals(StringConstants.CARD) || paymentInput.equals(StringConstants.UPI)) {
            	validInput = true;
            }
            else {
            	System.out.println(StringConstants.PLEASE_ENTER_VALID_INPUT);
            }
        }

        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentInput);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringConstants.DATE_FORMATTER);
        
        Timestamp returnTime = null;
        
        while (true) {
            System.out.print(StringConstants.ENTER_DATE_AND_TIME_YYYY_MM_DD_HH_MM_SS);
            String returnInput = App.scanner.nextLine();
            try {
            	returnTime = Timestamp.valueOf(LocalDateTime.parse(returnInput, formatter));
                if (returnTime.after(Timestamp.valueOf(LocalDateTime.now()))) {
                    break;
                } else {
                    System.out.println(StringConstants.THE_RETURN_TIME_CANNOT_BE_IN_PAST);
                }
            } catch (Exception e) {
                System.out.println(StringConstants.INVALID_DATE_FORMAT_PLEASE_TRY_AGAIN);
            }
        }
        
        try {
            paymentService.processPayment(booking, paymentMethod, returnTime, booking.getVehicleReturnTime().toString());
            System.out.println(StringConstants.PAYMENT_PROCESSED_SUCCESSFULLY);
        } catch (SQLException e) {
            System.err.println(StringConstants.ERROR_PROCESSING_PAYMENT + e.getMessage());
        }
    }
}
