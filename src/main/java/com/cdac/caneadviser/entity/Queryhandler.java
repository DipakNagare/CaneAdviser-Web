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

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the queryhandler database table.
 * 
 */
@Entity
@NamedQuery(name="Queryhandler.findAll", query="SELECT q FROM Queryhandler q")
public class Queryhandler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QUE_ID")
	private int queId;

	@Column(name="ANSWERED_BY")
	private String answeredBy;

	@Column(name="ANSWERED_DATE")
	private String answeredDate;

	@Column(name="ASKED_DATE")
	private String askedDate;

	@Lob
	private String image1;

	@Lob
	private String image2;

	@Lob
	private String image3;

	@Lob
	private String query;

	@Lob
	@Column(name="QUERY_ANSWER")
	private String queryAnswer;

	@Lob
	@Column(name="QUERY_DESC")
	private String queryDesc;

	//bi-directional many-to-one association to QueryAssignedMaster
	@JsonIgnore
	@OneToMany(mappedBy="queryhandler")
	private List<QueryAssignedMaster> queryAssignedMasters;

	//bi-directional many-to-one association to FarmerDetail
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ASKED_BY")
	private FarmerDetail farmerDetail;

	public Queryhandler() {
	}

	public int getQueId() {
		return this.queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public String getAnsweredBy() {
		return this.answeredBy;
	}

	public void setAnsweredBy(String answeredBy) {
		this.answeredBy = answeredBy;
	}

	public String getAnsweredDate() {
		return this.answeredDate;
	}

	public void setAnsweredDate(String answeredDate) {
		this.answeredDate = answeredDate;
	}

	public String getAskedDate() {
		return this.askedDate;
	}

	public void setAskedDate(String askedDate) {
		this.askedDate = askedDate;
	}

	public String getImage1() {
		return this.image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return this.image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return this.image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQueryAnswer() {
		return this.queryAnswer;
	}

	public void setQueryAnswer(String queryAnswer) {
		this.queryAnswer = queryAnswer;
	}

	public String getQueryDesc() {
		return this.queryDesc;
	}

	public void setQueryDesc(String queryDesc) {
		this.queryDesc = queryDesc;
	}

	public List<QueryAssignedMaster> getQueryAssignedMasters() {
		return this.queryAssignedMasters;
	}

	public void setQueryAssignedMasters(List<QueryAssignedMaster> queryAssignedMasters) {
		this.queryAssignedMasters = queryAssignedMasters;
	}

	public QueryAssignedMaster addQueryAssignedMaster(QueryAssignedMaster queryAssignedMaster) {
		getQueryAssignedMasters().add(queryAssignedMaster);
		queryAssignedMaster.setQueryhandler(this);

		return queryAssignedMaster;
	}

	public QueryAssignedMaster removeQueryAssignedMaster(QueryAssignedMaster queryAssignedMaster) {
		getQueryAssignedMasters().remove(queryAssignedMaster);
		queryAssignedMaster.setQueryhandler(null);

		return queryAssignedMaster;
	}

	public FarmerDetail getFarmerDetail() {
		return this.farmerDetail;
	}

	public void setFarmerDetail(FarmerDetail farmerDetail) {
		this.farmerDetail = farmerDetail;
	}

}