package com.wg.controller;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.dao.NotificationDAO;
import com.wg.helper.LoggingUtil;
import com.wg.helper.StringConstants;
import com.wg.model.Booking;
import com.wg.model.enums.PaymentMethod;
import com.wg.service.NotificationService;
import com.wg.service.PaymentService;

public class PaymentController {
	private final PaymentService paymentService;
	
	NotificationDAO notificationDAO = new NotificationDAO();
    NotificationService notificationService = new NotificationService(notificationDAO);
    NotificationController notificationController = new NotificationController(notificationService);

    private static final Logger logger = LoggingUtil.getLogger(BookingController.class);
    
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	public void handlePayment(Booking booking) {
		PaymentMethod paymentMethod = inputPaymentMethod();
        try {
            paymentService.processPayment(booking, paymentMethod);
        } catch (SQLException e) {
            System.err.println(StringConstants.ERROR_PROCESSING_PAYMENT + e.getMessage());
        }
    }

	public PaymentMethod inputPaymentMethod() {
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
		return paymentMethod;
	}
}
