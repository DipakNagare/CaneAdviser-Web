package com.cdac.caneadviser.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the analytics database table.
 * 
 */
@Entity
@Table(name = "analytics")
@NamedQuery(name = "Analytic.findAll", query = "SELECT a FROM Analytic a")
public class Analytic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sno")
	private int sno;
	// private int farmId;
	@Column(name = "accContent")
	private String accContent;

	@Column(name = "accDate")
	private String accDate;

	@Column(name = "accSubtop")
	private String accSubtop;	
	
	@Column(name = "acctime")
	private String acctime;

	@Column(name = "deviceModel")
	private String deviceModel;

	@Column(name = "networkType")
	private String networkType;
	
	@Column(name = "timeSpent")
	private String timeSpent;

	// bi-directional many-to-one association to FarmerDetail
	@ManyToOne
	@JoinColumn(name = "farmId")
	@JsonIgnore
	private FarmerDetail farmerDetail;

	public Analytic() {
	}

	// public int getFarmId() {
	// 	return this.farmId;
	// }

	// public void setFarmId(int farmId) {
	// 	this.farmId = farmId;
	// }

	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getAccContent() {
		return this.accContent;
	}

	public void setAccContent(String accContent) {
		this.accContent = accContent;
	}

	public String getAccDate() {
		return this.accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public String getAccSubtop() {
		return this.accSubtop;
	}

	public void setAccSubtop(String accSubtop) {
		this.accSubtop = accSubtop;
	}

	public String getAccTime() {
		return this.acctime;
	}

	public void setAccTime(String acctime) {
		this.acctime = acctime;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getNetworkType() {
		return this.networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getTimeSpent() {
		return this.timeSpent;
	}

	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}

	public FarmerDetail getFarmerDetail() {
		return this.farmerDetail;
	}

	public void setFarmerDetail(FarmerDetail farmerDetail) {
		this.farmerDetail = farmerDetail;
	}

}