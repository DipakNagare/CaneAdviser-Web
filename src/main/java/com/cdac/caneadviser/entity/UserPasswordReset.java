package com.cdac.caneadviser.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.sql.Timestamp;


/**
 * The persistent class for the user_password_reset database table.
 * 
 */
@Entity
@Table(name="user_password_reset")
@NamedQuery(name="UserPasswordReset.findAll", query="SELECT u FROM UserPasswordReset u")
public class UserPasswordReset implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserPasswordResetPK id;

	private Timestamp validity;

	public UserPasswordReset() {
	}

	public UserPasswordResetPK getId() {
		return this.id;
	}

	public void setId(UserPasswordResetPK id) {
		this.id = id;
	}

	public Timestamp getValidity() {
		return this.validity;
	}

	public void setValidity(Timestamp validity) {
		this.validity = validity;
	}

}