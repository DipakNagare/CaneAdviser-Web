package com.cdac.caneadviser.entity;

import java.io.Serializable;
import javax.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the mobile_app_user database table.
 * 
 */
@Entity
@Table(name="mobile_app_user")
@NamedQuery(name="MobileAppUser.findAll", query="SELECT m FROM MobileAppUser m")
public class MobileAppUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	@Lob
	private String address;

	@Column(name="ADMIN_APPROVAL_STATUS")
	private String adminApprovalStatus;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Column(name="EMI_NO")
	private String emiNo;

	@Column(name="MOBILE_MODEL_NAME")
	private String mobileModelName;

	private int otp;

	private String status;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to RoleMaster
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private RoleMaster roleMaster;

	//bi-directional many-to-one association to GroupMaster
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private GroupMaster groupMaster;

	public MobileAppUser() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAdminApprovalStatus() {
		return this.adminApprovalStatus;
	}

	public void setAdminApprovalStatus(String adminApprovalStatus) {
		this.adminApprovalStatus = adminApprovalStatus;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmiNo() {
		return this.emiNo;
	}

	public void setEmiNo(String emiNo) {
		this.emiNo = emiNo;
	}

	public String getMobileModelName() {
		return this.mobileModelName;
	}

	public void setMobileModelName(String mobileModelName) {
		this.mobileModelName = mobileModelName;
	}

	public int getOtp() {
		return this.otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RoleMaster getRoleMaster() {
		return this.roleMaster;
	}

	public void setRoleMaster(RoleMaster roleMaster) {
		this.roleMaster = roleMaster;
	}

	public GroupMaster getGroupMaster() {
		return this.groupMaster;
	}

	public void setGroupMaster(GroupMaster groupMaster) {
		this.groupMaster = groupMaster;
	}

}