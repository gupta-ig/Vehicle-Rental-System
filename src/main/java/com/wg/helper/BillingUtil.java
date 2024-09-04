package com.wg.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;

public class BillingUtil {
	
	private static final BigDecimal CAR_HOURLY_RATE = new BigDecimal("100.00"); // Set your car price here
    private static final BigDecimal BIKE_HOURLY_RATE = new BigDecimal("50.00");  // Set your bike price here
    private static final BigDecimal FINE_RATE = new BigDecimal("20.00");         // Set your fine per hour
	
	public static BigDecimal calculateTotalAmount(Timestamp startTime, Timestamp endTime, Timestamp returnTime, String vehicleType) {
        BigDecimal hourlyRate;

        // Determine the hourly rate based on vehicle type
        if ("CAR".equalsIgnoreCase(vehicleType)) {
            hourlyRate = CAR_HOURLY_RATE;
        } else if ("BIKE".equalsIgnoreCase(vehicleType)) {
            hourlyRate = BIKE_HOURLY_RATE;
        } else {
            throw new IllegalArgumentException(StringConstants.INVALID_VEHICLE_TYPE);
        }

        // Calculate the duration between start time and end time
        Duration bookingDuration = Duration.between(startTime.toLocalDateTime(), endTime.toLocalDateTime());
        BigDecimal bookingHours = new BigDecimal(bookingDuration.toMinutes()).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP);

        // Calculate the total amount for the booked time
        BigDecimal totalAmount = bookingHours.multiply(hourlyRate);

        // Check if the vehicle was returned late
        if (returnTime.after(endTime)) {
            Duration lateDuration = Duration.between(endTime.toLocalDateTime(), returnTime.toLocalDateTime());
            BigDecimal lateHours = new BigDecimal(lateDuration.toMinutes()).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP);
            BigDecimal fineAmount = lateHours.multiply(FINE_RATE);

            // Add the fine to the total amount
            totalAmount = totalAmount.add(fineAmount);
        }

        return totalAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
