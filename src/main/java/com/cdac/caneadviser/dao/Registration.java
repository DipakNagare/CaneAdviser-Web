package com.cdac.caneadviser.dao;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Registration {

    @NotNull(message = "Farmer Name is required")
    // @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String farmerName;

	@NotNull(message = "Country name is required")
	// @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
	private String country;

    @NotNull(message = "State is required")
    // @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String state;

    @NotNull(message = "District is required")
    // @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Invalid Format. Only Alphabets and Numbers are allowed")
    private String district;

    @NotNull(message = "Mobile Number is required")
    // @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Format. 10 digits are required")
    private String mobileNo;

    @Email(message = "Invalid Email Format")
    private String emailId;

    @NotNull(message = "Occupation is required")
    // @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String occupation;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot be more than 100")
    private Integer age;

    @NotNull(message = "Village is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String village;

    @NotNull(message = "Block is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String block;

    @NotNull(message = "Agricultural Land Area is required")
    @Pattern(regexp = "^[0-9. ]+$", message = "Invalid Format. Only Numbers and '.' are allowed")
    private String agriLandArea;

    @NotNull(message = "Crop Grown is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String cropGrown;

    @NotNull(message = "Cow Count is required")
    @Pattern(regexp = "^[0-9]+$", message = "Invalid Format. Only Numbers are allowed")
    private String cowCount;

    @NotNull(message = "Buffaloe Count is required")
    @Pattern(regexp = "^[0-9]+$", message = "Invalid Format. Only Numbers are allowed")
    private String buffaloeCount;

    @NotNull(message = "Other Enterprises is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String otherEnterprises;

    @NotNull(message = "Organic Farm Practiced is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String organicFarmPractised;

    @NotNull(message = "Area Practiced is required")
    @Pattern(regexp = "^[A-Za-z0-9. ]+$", message = "Invalid Format. Only Alphabets, Numbers, and '.' are allowed")
    private String areaPractised;

    @NotNull(message = "Organic Fertilizer Type is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Invalid Format. Only Alphabets are allowed")
    private String organicFertType;

    @NotNull(message = "IMEI Number is required")
    @Pattern(regexp = "^[A-Za-z0-9- ]+$", message = "Invalid Format. Only Alphabets, Numbers, '-' and ' ' are allowed")
    private String imeiNo;

	public String getFarmerName() {
		return farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getAgriLandArea() {
		return agriLandArea;
	}

	public void setAgriLandArea(String agriLandArea) {
		this.agriLandArea = agriLandArea;
	}

	public String getCropGrown() {
		return cropGrown;
	}

	public void setCropGrown(String cropGrown) {
		this.cropGrown = cropGrown;
	}

	public String getCowCount() {
		return cowCount;
	}

	public void setCowCount(String cowCount) {
		this.cowCount = cowCount;
	}

	public String getBuffaloeCount() {
		return buffaloeCount;
	}

	public void setBuffaloeCount(String buffaloeCount) {
		this.buffaloeCount = buffaloeCount;
	}

	public String getOtherEnterprises() {
		return otherEnterprises;
	}

	public void setOtherEnterprises(String otherEnterprises) {
		this.otherEnterprises = otherEnterprises;
	}

	public String getOrganicFarmPractised() {
		return organicFarmPractised;
	}

	public void setOrganicFarmPractised(String organicFarmPractised) {
		this.organicFarmPractised = organicFarmPractised;
	}

	public String getAreaPractised() {
		return areaPractised;
	}

	public void setAreaPractised(String areaPractised) {
		this.areaPractised = areaPractised;
	}

	public String getOrganicFertType() {
		return organicFertType;
	}

	public void setOrganicFertType(String organicFertType) {
		this.organicFertType = organicFertType;
	}

	public String getImeiNo() {
		return imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}


	    
}
