package com.cdac.caneadviser.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the farmer_details database table.
 * 
 */
@Entity
@Table(name="farmer_details")
@NamedQuery(name="FarmerDetail.findAll", query="SELECT f FROM FarmerDetail f")
public class FarmerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FARM_ID")
	private int farmId;

	@Column(name="AADHAR_NO")
	private String aadharNo;

	@Lob
	private String address;

	private Integer age;

	@Column (name = "COUNTRY")
	private String country;

	@Column(name="CREATED_ON")
	private String createdOn;

	@Column(name="CULTIVATED_AREA")
	private String cultivatedArea;

	private String district;

	@Column(name="EMAIL_ID")
	private String emailId;

	@Column(name="FARMER_NAME")
	private String farmerName;

	@Column(name="IMEI_NO")
	private String imeiNo;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	private String otp;

	private String qualification;

	private String state;

	private String status;

	@Column(name="SUGAR_VARIETY")
	private String sugarVariety;

	private String yield;


	//bi-directional many-to-one association to Analytic
	@OneToMany(mappedBy="farmerDetail")
	@JsonIgnore
	private List<Analytic> analytics;

	//bi-directional many-to-one association to Queryhandler
	@OneToMany(mappedBy="farmerDetail")
	private List<Queryhandler> queryhandlers;

	public FarmerDetail() {
	}

	public int getFarmId() {
		return this.farmId;
	}

	public void setFarmId(int farmId) {
		this.farmId = farmId;
	}

	public String getAadharNo() {
		return this.aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCultivatedArea() {
		return this.cultivatedArea;
	}

	public void setCultivatedArea(String cultivatedArea) {
		this.cultivatedArea = cultivatedArea;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFarmerName() {
		return this.farmerName;
	}

	public void setFarmerName(String farmerName) {
		this.farmerName = farmerName;
	}

	public String getImeiNo() {
		return this.imeiNo;
	}

	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSugarVariety() {
		return this.sugarVariety;
	}

	public void setSugarVariety(String sugarVariety) {
		this.sugarVariety = sugarVariety;
	}

	public String getYield() {
		return this.yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}

	public List<Analytic> getAnalytics() {
		return this.analytics;
	}

	public void setAnalytics(List<Analytic> analytics) {
		this.analytics = analytics;
	}

	public Analytic addAnalytic(Analytic analytic) {
		getAnalytics().add(analytic);
		analytic.setFarmerDetail(this);

		return analytic;
	}

	public Analytic removeAnalytic(Analytic analytic) {
		getAnalytics().remove(analytic);
		analytic.setFarmerDetail(null);

		return analytic;
	}

	public List<Queryhandler> getQueryhandlers() {
		return this.queryhandlers;
	}

	public void setQueryhandlers(List<Queryhandler> queryhandlers) {
		this.queryhandlers = queryhandlers;
	}

	public Queryhandler addQueryhandler(Queryhandler queryhandler) {
		getQueryhandlers().add(queryhandler);
		queryhandler.setFarmerDetail(this);

		return queryhandler;
	}

	public Queryhandler removeQueryhandler(Queryhandler queryhandler) {
		getQueryhandlers().remove(queryhandler);
		queryhandler.setFarmerDetail(null);

		return queryhandler;
	}


}