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


/**
 * The persistent class for the role_master database table.
 * 
 */
@Entity
@Table(name="role_master")
@NamedQuery(name="RoleMaster.findAll", query="SELECT r FROM RoleMaster r")
public class RoleMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_ID")
	private int roleId;

	@Lob
	@Column(name="ROLE_DESCRIPTION")
	private String roleDescription;

	@Column(name="ROLE_NAME")
	private String roleName;

	//bi-directional many-to-one association to MobileAppUser
	@OneToMany(mappedBy="roleMaster")
	private List<MobileAppUser> mobileAppUsers;

	//bi-directional many-to-one association to UserMaster
	@OneToMany(mappedBy="roleMaster")
	private List<UserMaster> userMasters;

	public RoleMaster() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<MobileAppUser> getMobileAppUsers() {
		return this.mobileAppUsers;
	}

	public void setMobileAppUsers(List<MobileAppUser> mobileAppUsers) {
		this.mobileAppUsers = mobileAppUsers;
	}

	public MobileAppUser addMobileAppUser(MobileAppUser mobileAppUser) {
		getMobileAppUsers().add(mobileAppUser);
		mobileAppUser.setRoleMaster(this);

		return mobileAppUser;
	}

	public MobileAppUser removeMobileAppUser(MobileAppUser mobileAppUser) {
		getMobileAppUsers().remove(mobileAppUser);
		mobileAppUser.setRoleMaster(null);

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
		userMaster.setRoleMaster(this);

		return userMaster;
	}

	public UserMaster removeUserMaster(UserMaster userMaster) {
		getUserMasters().remove(userMaster);
		userMaster.setRoleMaster(null);

		return userMaster;
	}

}