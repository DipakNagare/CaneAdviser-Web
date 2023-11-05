package com.cdac.caneadviser.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


/**
 * The persistent class for the group_master database table.
 * 
 */
@Entity
@Table(name="group_master")
@NamedQuery(name="GroupMaster.findAll", query="SELECT g FROM GroupMaster g")
public class GroupMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GROUP_ID")
	private int groupId;

	@Lob
	@Column(name="GROUP_DESCRIPTION")
	private String groupDescription;

	@Column(name="GROUP_NAME")
	private String groupName;

	//bi-directional many-to-one association to MobileAppUser
	@JsonIgnore
	@OneToMany(mappedBy="groupMaster")
	private List<MobileAppUser> mobileAppUsers;

	//bi-directional many-to-one association to UserMaster
	@JsonIgnore
	@OneToMany(mappedBy="groupMaster")
	private List<UserMaster> userMasters;

	public GroupMaster() {
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<MobileAppUser> getMobileAppUsers() {
		return this.mobileAppUsers;
	}

	public void setMobileAppUsers(List<MobileAppUser> mobileAppUsers) {
		this.mobileAppUsers = mobileAppUsers;
	}

	public MobileAppUser addMobileAppUser(MobileAppUser mobileAppUser) {
		getMobileAppUsers().add(mobileAppUser);
		mobileAppUser.setGroupMaster(this);

		return mobileAppUser;
	}

	public MobileAppUser removeMobileAppUser(MobileAppUser mobileAppUser) {
		getMobileAppUsers().remove(mobileAppUser);
		mobileAppUser.setGroupMaster(null);

		return mobileAppUser;
	}

	public List<UserMaster> getUserMasters() {
		return this.userMasters;
	}

	public void setUserMasters(List<UserMaster> userMasters) {
		this.userMasters = userMasters;
	}

	public UserMaster addUserMaster(UserMaster userMaster) {
		getUserMasters().add(userMaster);
		userMaster.setGroupMaster(this);

		return userMaster;
	}

	public UserMaster removeUserMaster(UserMaster userMaster) {
		getUserMasters().remove(userMaster);
		userMaster.setGroupMaster(null);

		return userMaster;
	}

    public String getFarmerName() {
        return null;
    }

}