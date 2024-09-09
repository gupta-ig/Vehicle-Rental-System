package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wg.model.Payment;
import com.wg.model.enums.PaymentMethod;

public class PaymentDAO extends GenericDAO<Payment, String>{

	@Override
	protected String getTableName() {
		return "PAYMENT";
	}

	@Override
	protected Payment mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Payment(
				result.getString("payment_id"),
	            result.getString("booking_id"),
	            result.getBigDecimal("amount_paid"),
	            result.getBigDecimal("fine"),
	            result.getTimestamp("payment_date"),
	            PaymentMethod.valueOf(result.getString("payment_method"))
	            );
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Payment payment) throws SQLException {
		stmt.setString(1, payment.getPaymentId());
        stmt.setString(2, payment.getBookingId());
        stmt.setBigDecimal(3, payment.getAmountPaid());
        stmt.setBigDecimal(4, payment.getFinePaid());
        stmt.setTimestamp(5, payment.getPaymentDate());
        stmt.setString(6, payment.getPaymentMethod().name());
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "payment_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String paymentId) throws SQLException {
		stmt.setString(1, paymentId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?, ?";
	}

}
