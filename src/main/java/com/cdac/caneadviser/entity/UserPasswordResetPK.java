package com.cdac.caneadviser.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the user_password_reset database table.
 * 
 */
@Embeddable
public class UserPasswordResetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="HASH_ID")
	private String hashId;

	public UserPasswordResetPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHashId() {
		return this.hashId;
	}
	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserPasswordResetPK)) {
			return false;
		}
		UserPasswordResetPK castOther = (UserPasswordResetPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.hashId.equals(castOther.hashId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.hashId.hashCode();
		
		return hash;
	}
}