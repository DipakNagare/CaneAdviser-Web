package com.cdac.caneadviser.entity;

import java.io.Serializable;
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
@Table(name="analytics")
@NamedQuery(name="Analytic.findAll", query="SELECT a FROM Analytic a")
public class Analytic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sno;

	private String accContent;

	private String accDate;

	private String accSubtop;

	private String accTIme;

	private String deviceModel;

	private String networkType;

	private String timeSpent;

	//bi-directional many-to-one association to FarmerDetail
	@ManyToOne
	@JoinColumn(name="farmId")
	private FarmerDetail farmerDetail;

	public Analytic() {
	}

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

	public String getAccTIme() {
		return this.accTIme;
	}

	public void setAccTIme(String accTIme) {
		this.accTIme = accTIme;
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