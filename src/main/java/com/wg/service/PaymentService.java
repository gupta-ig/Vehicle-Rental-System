package com.wg.service;

import java.sql.SQLException;

import com.wg.dao.PaymentDAO;
import com.wg.model.Booking;
import com.wg.model.Payment;

public class PaymentService {

	private PaymentDAO paymentDAO;

	public PaymentService(PaymentDAO paymentDAO) {
		super();
		this.paymentDAO = paymentDAO;
	}
	
	public void processPayment(Booking booking, Payment payment) throws SQLException {
        paymentDAO.add(payment);
    }
}
