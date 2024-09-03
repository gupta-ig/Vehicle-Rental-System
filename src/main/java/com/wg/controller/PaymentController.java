package com.wg.controller;

import java.sql.SQLException;

import com.wg.app.App;
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
        	System.out.print("Enter payment method(CASH, CARD, UPI): ");
            paymentInput = App.scanner.next().toUpperCase();
            
            if(paymentInput.equals("CASH") || paymentInput.equals("CARD") || paymentInput.equals("UPI")) {
            	validInput = true;
            }
            else {
            	System.out.println("Please enter correct input.");
            }
        }

        PaymentMethod paymentMethod = PaymentMethod.valueOf(paymentInput);
        
        try {
            paymentService.processPayment(booking, paymentMethod);
            System.out.println("Payment processed successfully!");
        } catch (SQLException e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
    }
}
