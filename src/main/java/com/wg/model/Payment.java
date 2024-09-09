package com.wg.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import com.wg.model.enums.PaymentMethod;

public class Payment {
	private String paymentId;
	private String bookingId;
	private BigDecimal amountPaid;
	private BigDecimal finePaid;
	private Timestamp paymentDate;
	private PaymentMethod paymentMethod;
	
	public Payment(String paymentId, String bookingId, BigDecimal amountPaid,BigDecimal finePaid, Timestamp paymentDate,
			 PaymentMethod paymentMethod) {
		super();
		this.paymentId = (paymentId == null) ? UUID.randomUUID().toString() : paymentId;
		this.bookingId = bookingId;
		this.amountPaid = amountPaid;
		this.finePaid = finePaid;
		this.paymentDate = paymentDate;
		this.paymentMethod = paymentMethod;
	}
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId() {
		this.paymentId = UUID.randomUUID().toString();
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	public BigDecimal getFinePaid() {
		return finePaid;
	}
	public void setFinePaid(BigDecimal finePaid) {
		this.finePaid = finePaid;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId +
				", bookingId=" + bookingId + 
				", amountPaid=" + amountPaid +
				", finePaid=" + finePaid +
				", paymentDate=" + paymentDate + 
				", paymentMethod=" + paymentMethod + "]";
	}
	
}
