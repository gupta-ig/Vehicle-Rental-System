package com.wg.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;

public class BillingUtil {
	private static final BigDecimal HOURLY_RATE = new BigDecimal("50.00"); // Set your constant price here

    public static BigDecimal calculateTotalAmount(Timestamp startTime, Timestamp endTime) {
        // Calculate the duration between the two times
        Duration duration = Duration.between(startTime.toLocalDateTime(), endTime.toLocalDateTime());
        
        // Get the number of hours as a decimal value
        BigDecimal hours = new BigDecimal(duration.toMinutes()).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP);
        
        // Calculate the total amount
        BigDecimal totalAmount = hours.multiply(HOURLY_RATE);
        
        return totalAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
