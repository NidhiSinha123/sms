package com.sms.SurveyManagementSystem.dto;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Author:  Nidhi
 * Description:DTO
 * Created on: November 11, 2019
 * 
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Survey {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="survey_id")
	private BigInteger surveyId;
	
	@Column(name="survey_title")
	private String surveyTitle;
	
	@Column(name="survey_description")
	private String surveyDescription;
	
	@OneToMany(mappedBy = "survey")
	private List<Questions> listOfQuestions;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	@ManyToMany(mappedBy = "AssignedSurvey")
	Set<User> listOfUsers;
	
	@CreatedBy
	protected String createdBy;
	
	@CreatedDate	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@LastModifiedDate
	protected String lastModifiedDate;
	
	public Survey()
	{
		
	}

	public Survey(BigInteger surveyId, String surveyTitle,
			String surveyDescription, List<Questions> listOfQuestions,
			boolean isDeleted, Set<User> listOfUsers, String createdBy,
			Date creationDate, String lastModifiedBy, String lastModifiedDate) {
		super();
		this.surveyId = surveyId;
		this.surveyTitle = surveyTitle;
		this.surveyDescription = surveyDescription;
		this.listOfQuestions = listOfQuestions;
		this.isDeleted = isDeleted;
		this.listOfUsers = listOfUsers;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public BigInteger getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(BigInteger surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	public String getSurveyDescription() {
		return surveyDescription;
	}

	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
	}

	public List<Questions> getListOfQuestions() {
		return listOfQuestions;
	}

	public void setListOfQuestions(List<Questions> listOfQuestions) {
		this.listOfQuestions = listOfQuestions;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<User> getListOfUsers() {
		return listOfUsers;
	}

	public void setListOfUsers(Set<User> listOfUsers) {
		this.listOfUsers = listOfUsers;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result
				+ ((lastModifiedBy == null) ? 0 : lastModifiedBy.hashCode());
		result = prime
				* result
				+ ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
		result = prime * result
				+ ((listOfQuestions == null) ? 0 : listOfQuestions.hashCode());
		result = prime * result
				+ ((listOfUsers == null) ? 0 : listOfUsers.hashCode());
		result = prime
				* result
				+ ((surveyDescription == null) ? 0 : surveyDescription
						.hashCode());
		result = prime * result
				+ ((surveyId == null) ? 0 : surveyId.hashCode());
		result = prime * result
				+ ((surveyTitle == null) ? 0 : surveyTitle.hashCode());
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
		Survey other = (Survey) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (lastModifiedBy == null) {
			if (other.lastModifiedBy != null)
				return false;
		} else if (!lastModifiedBy.equals(other.lastModifiedBy))
			return false;
		if (lastModifiedDate == null) {
			if (other.lastModifiedDate != null)
				return false;
		} else if (!lastModifiedDate.equals(other.lastModifiedDate))
			return false;
		if (listOfQuestions == null) {
			if (other.listOfQuestions != null)
				return false;
		} else if (!listOfQuestions.equals(other.listOfQuestions))
			return false;
		if (listOfUsers == null) {
			if (other.listOfUsers != null)
				return false;
		} else if (!listOfUsers.equals(other.listOfUsers))
			return false;
		if (surveyDescription == null) {
			if (other.surveyDescription != null)
				return false;
		} else if (!surveyDescription.equals(other.surveyDescription))
			return false;
		if (surveyId == null) {
			if (other.surveyId != null)
				return false;
		} else if (!surveyId.equals(other.surveyId))
			return false;
		if (surveyTitle == null) {
			if (other.surveyTitle != null)
				return false;
		} else if (!surveyTitle.equals(other.surveyTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyTitle=" + surveyTitle
				+ ", surveyDescription=" + surveyDescription
				+ ", listOfQuestions=" + listOfQuestions + ", isDeleted="
				+ isDeleted + ", listOfUsers=" + listOfUsers + ", createdBy="
				+ createdBy + ", creationDate=" + creationDate
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}
	

	
}