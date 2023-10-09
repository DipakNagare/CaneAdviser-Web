package com.cdac.caneadviser.dao;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Analytic {

    @NotNull(message = "Farm ID is required")
    @Pattern(regexp = "\\d+", message = "Farm ID must contain only numbers")
    private String farmId;

    @NotNull(message = "Account Content is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Invalid Format. Only Alphabets & Numbers are allowed")
    @Size(max = 255, message = "Account Content should not exceed 255 characters")
    private String accContent;

    @NotNull(message = "Account Date is required")
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Invalid Date Format. Use DD-MM-YYYY format")
    private String accDate;

    @NotNull(message = "Account Subtopic is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "Invalid Format. Only Alphabets & Numbers are allowed")
    @Size(max = 255, message = "Account Subtopic should not exceed 255 characters")
    private String accSubtop;

    @NotNull(message = "Account Time is required")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "Invalid Time Format. Use HH:MM format")
    private String accTime;

    @NotNull(message = "Device Model is required")
    @Pattern(regexp = "^[A-Za-z0-9_ ]+$", message = "Invalid Format. Only Alphabets, Numbers, and Underscore are allowed")
    private String deviceModel;

    @NotNull(message = "Network Type is required")
    @Pattern(regexp = "^[A-Za-z0-9_ ]+$", message = "Invalid Format. Only Alphabets, Numbers, and Underscore are allowed")
    private String networkType;

    @NotNull(message = "Time Spent is required")
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Invalid Time Format. Use HH:MM:SS format")
    private String timeSpent;

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getAccContent() {
		return accContent;
	}

	public void setAccContent(String accContent) {
		this.accContent = accContent;
	}

	public String getAccDate() {
		return accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public String getAccSubtop() {
		return accSubtop;
	}

	public void setAccSubtop(String accSubtop) {
		this.accSubtop = accSubtop;
	}

	public String getAccTime() {
		return accTime;
	}

	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
}
