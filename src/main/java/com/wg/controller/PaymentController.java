package com.wg.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.dao.NotificationDAO;
import com.wg.helper.BillingUtil;
import com.wg.helper.LoggingUtil;
import com.wg.helper.StringConstants;
import com.wg.model.Booking;
import com.wg.model.Payment;
import com.wg.model.enums.PaymentMethod;
import com.wg.service.NotificationService;
import com.wg.service.PaymentService;

public class PaymentController {
	private PaymentService paymentService;
	
	NotificationDAO notificationDAO = new NotificationDAO();
    NotificationService notificationService = new NotificationService(notificationDAO);
    NotificationController notificationController = new NotificationController(notificationService);

    BillingUtil billingUtil = new BillingUtil();
    
    private static final Logger logger = LoggingUtil.getLogger(BookingController.class);
    
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	
	public void handlePayment(Booking booking, Timestamp returnTime) {
		PaymentMethod paymentMethod = inputPaymentMethod();
        try {
        	Payment payment = new Payment();
            payment.setPaymentId();
            payment.setBookingId(booking.getBookingId());
            
            BigDecimal amount = billingUtil.calculateTotalAmount(booking.getBookingStartTime(), booking.getBookingEndTime()); 
            payment.setAmountPaid(amount);
            
            if(returnTime.after(booking.getBookingEndTime()))
            {
            	BigDecimal fine = billingUtil.calculateFineAmount(booking.getBookingEndTime(), returnTime);
            	payment.setFinePaid(fine);
            }
            else {
            	payment.setFinePaid(new BigDecimal(0.0));
            }
            payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
            payment.setPaymentMethod(paymentMethod);
            
            paymentService.processPayment(booking, payment);
            
            logger.info(StringConstants.PAYMENT_PROCESSED_SUCCESSFULLY);
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
