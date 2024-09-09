package com.wg.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.wg.dao.PaymentDAO;
import com.wg.helper.BillingUtil;
import com.wg.model.Booking;
import com.wg.model.Payment;
import com.wg.model.enums.PaymentMethod;

public class PaymentService {

	private final PaymentDAO paymentDAO;

	public PaymentService(PaymentDAO paymentDAO) {
		super();
		this.paymentDAO = paymentDAO;
	}
	
	public void processPayment(Booking booking, PaymentMethod paymentMethod) throws SQLException {
        BigDecimal amount = BillingUtil.calculateTotalAmount(booking.getBookingStartTime(), booking.getBookingEndTime());

        Payment payment = new Payment();
        payment.setPaymentId();
        payment.setBookingId(booking.getBookingId());
        payment.setAmountPaid(amount);
        payment.setFinePaid(new BigDecimal(0.0));
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setPaymentMethod(paymentMethod);

        paymentDAO.add(payment);
    }
	
	public void processFinePayment(String bookingId, Timestamp bookingEndTime, PaymentMethod paymentMethod, Timestamp returnTime) throws SQLException {
		
		BigDecimal fine = BillingUtil.calculateFineAmount(bookingEndTime, returnTime);
		
		Payment payment = new Payment();
		payment.setPaymentId();
        payment.setBookingId(bookingId);
        payment.setAmountPaid(new BigDecimal(0.0));
        payment.setFinePaid(fine);
        payment.setPaymentDate(returnTime);
        payment.setPaymentMethod(paymentMethod);

        paymentDAO.add(payment);
		
	}
}
