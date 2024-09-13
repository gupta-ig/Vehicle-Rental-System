package com.wg.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Duration;

public class BillingUtil {
	
	private static final BigDecimal HOURLY_RATE = new BigDecimal("200.00");
    private static final BigDecimal FINE_RATE = new BigDecimal("20.00");         // Set your fine per hour
	
	public BigDecimal calculateTotalAmount(Timestamp startTime, Timestamp endTime) {
//        BigDecimal hourlyRate;

//        // Determine the hourly rate based on vehicle type
//        if ("CAR".equalsIgnoreCase(vehicleType)) {
//            hourlyRate = CAR_HOURLY_RATE;
//        } else if ("BIKE".equalsIgnoreCase(vehicleType)) {
//            hourlyRate = BIKE_HOURLY_RATE;
//        } else {
//            throw new IllegalArgumentException(StringConstants.INVALID_VEHICLE_TYPE);
//        }

        // Calculate the duration between start time and end time
        Duration bookingDuration = Duration.between(startTime.toLocalDateTime(), endTime.toLocalDateTime());
        BigDecimal bookingHours = new BigDecimal(bookingDuration.toMinutes()).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP);

        // Calculate the total amount for the booked time
        BigDecimal totalAmount = bookingHours.multiply(HOURLY_RATE);

        
        return totalAmount.setScale(2, RoundingMode.HALF_UP);
    }

	public BigDecimal calculateFineAmount(Timestamp bookingEndTime, Timestamp vehicleReturnTime) {
		
		// Check if the vehicle was returned late
        if (vehicleReturnTime.after(bookingEndTime)) {
            Duration lateDuration = Duration.between(bookingEndTime.toLocalDateTime(), vehicleReturnTime.toLocalDateTime());
            BigDecimal lateHours = new BigDecimal(lateDuration.toMinutes()).divide(new BigDecimal("60"), 2, RoundingMode.HALF_UP);
            BigDecimal fineAmount = lateHours.multiply(FINE_RATE);

            // Add the fine to the total amount
            fineAmount = fineAmount.add(fineAmount);
            
            return fineAmount.setScale(2, RoundingMode.HALF_UP);
        }
        return new BigDecimal(0.0);
	}
}
