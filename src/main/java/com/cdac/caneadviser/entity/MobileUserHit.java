package com.cdac.caneadviser.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the mobile_user_hit database table.
 * 
 */
@Entity
@Table(name="mobile_user_hit")
@NamedQuery(name="MobileUserHit.findAll", query="SELECT m FROM MobileUserHit m")
public class MobileUserHit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sno;

	private String farmId;

	private String openedDate;

	private String openedTime;

	public MobileUserHit() {
	}

	public int getSno() {
		return this.sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getFarmId() {
		return this.farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String getOpenedDate() {
		return this.openedDate;
	}

	public void setOpenedDate(String openedDate) {
		this.openedDate = openedDate;
	}

	public String getOpenedTime() {
		return this.openedTime;
	}

	public void setOpenedTime(String openedTime) {
		this.openedTime = openedTime;
	}

}