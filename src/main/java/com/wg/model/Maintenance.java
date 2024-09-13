package com.wg.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Maintenance {
	private String maintenanceId;
    private String vehicleId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String description;
    private BigDecimal totalBill;
    
	public Maintenance(String maintenanceId, String vehicleId, Timestamp startDate, Timestamp endDate,
			String description, BigDecimal totalBill) {
		super();
		this.maintenanceId = (maintenanceId == null) ? UUID.randomUUID().toString() : maintenanceId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.totalBill = totalBill;
	}

	public String getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId() {
		this.maintenanceId = UUID.randomUUID().toString();
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}

	@Override
	public String toString() {
		return "Maintenance [maintenanceId=" + maintenanceId + 
				", vehicleId=" + vehicleId + 
				", startDate=" + startDate + 
				", endDate=" + endDate + 
				", description=" + description + 
				", totalBill=" + totalBill + "]";
	}
}
