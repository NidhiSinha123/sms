package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
 * Author:  Nidhi
 * Description:DTO
 * Created on: December 13, 2019
 * 
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AssignSurvey {

	@Id
	@Column(name="assigned_sid")
	private BigInteger assignedSurveyId;
	
	@Column(name="survey_id")
	private BigInteger surveyId;
	
	@Column(name="user_id")
	private BigInteger userId;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="status")
	private Integer status;
	
	@OneToMany(mappedBy="assignedSurvey")
	private Set<Answer> listOfAnswers;
	
	
	public AssignSurvey()
	{
		
	}


	public AssignSurvey(BigInteger assignedSurveyId, BigInteger surveyId,
			BigInteger userId, Date date, Integer status) {
		super();
		this.assignedSurveyId = assignedSurveyId;
		this.surveyId = surveyId;
		this.userId = userId;
		this.date = date;
		this.status = status;
	}


	public BigInteger getAssignedSurveyId() {
		return assignedSurveyId;
	}


	public void setAssignedSurveyId(BigInteger assignedSurveyId) {
		this.assignedSurveyId = assignedSurveyId;
	}


	public BigInteger getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(BigInteger surveyId) {
		this.surveyId = surveyId;
	}


	public BigInteger getUserId() {
		return userId;
	}


	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((assignedSurveyId == null) ? 0 : assignedSurveyId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((surveyId == null) ? 0 : surveyId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignSurvey other = (AssignSurvey) obj;
		if (assignedSurveyId == null) {
			if (other.assignedSurveyId != null)
				return false;
		} else if (!assignedSurveyId.equals(other.assignedSurveyId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (surveyId == null) {
			if (other.surveyId != null)
				return false;
		} else if (!surveyId.equals(other.surveyId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AssignSurvey [assignedSurveyId=" + assignedSurveyId
				+ ", surveyId=" + surveyId + ", userId=" + userId + ", date="
				+ date + ", status=" + status + "]";
	}
	
	
}
