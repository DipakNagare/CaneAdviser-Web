package com.cdac.caneadviser.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the query_assigned_master database table.
 * 
 */
@Entity
@Table(name="query_assigned_master")
@NamedQuery(name="QueryAssignedMaster.findAll", query="SELECT q FROM QueryAssignedMaster q")
public class QueryAssignedMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ASSIGNED_ID")
	private int assignedId;

	private String status;

	//bi-directional many-to-one association to UserMaster
	
	@ManyToOne
	@JoinColumn(name="ASSIGNED_TO_ANSWER")
	private UserMaster userMaster;

	//bi-directional many-to-one association to Queryhandler

	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private Queryhandler queryhandler;

	public QueryAssignedMaster() {
	}

	public int getAssignedId() {
		return this.assignedId;
	}

	public void setAssignedId(int assignedId) {
		this.assignedId = assignedId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserMaster getUserMaster() {
		return this.userMaster;
	}

	public void setUserMaster(UserMaster userMaster) {
		this.userMaster = userMaster;
	}

	public Queryhandler getQueryhandler() {
		return this.queryhandler;
	}

	public void setQueryhandler(Queryhandler queryhandler) {
		this.queryhandler = queryhandler;
	}

}