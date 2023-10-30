package com.cdac.caneadviser.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_master database table.
 * 
 */
@Entity
@Table(name="user_master")
@NamedQuery(name="UserMaster.findAll", query="SELECT u FROM UserMaster u")
public class UserMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Lob
	private String address;

	@Column(name="CONTACT_NO")
	private String contactNo;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private String gender;

	private String name;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE")
	private Date startDate;

	private String status;

	//bi-directional many-to-one association to QueryAssignedMaster
	@JsonIgnore
	@OneToMany(mappedBy="userMaster")
	private List<QueryAssignedMaster> queryAssignedMasters;

	//bi-directional many-to-one association to RoleMaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private RoleMaster roleMaster;

	//bi-directional many-to-one association to GroupMaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private GroupMaster groupMaster;

	public UserMaster() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<QueryAssignedMaster> getQueryAssignedMasters() {
		return this.queryAssignedMasters;
	}

	public void setQueryAssignedMasters(List<QueryAssignedMaster> queryAssignedMasters) {
		this.queryAssignedMasters = queryAssignedMasters;
	}

	public QueryAssignedMaster addQueryAssignedMaster(QueryAssignedMaster queryAssignedMaster) {
		getQueryAssignedMasters().add(queryAssignedMaster);
		queryAssignedMaster.setUserMaster(this);

		return queryAssignedMaster;
	}

	public QueryAssignedMaster removeQueryAssignedMaster(QueryAssignedMaster queryAssignedMaster) {
		getQueryAssignedMasters().remove(queryAssignedMaster);
		queryAssignedMaster.setUserMaster(null);

		return queryAssignedMaster;
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