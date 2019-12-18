package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;
import java.util.Arrays;
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

/*
 * Author:  Nidhi
 * Description:DTO
 * Created on: December 13, 2019
 * 
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="question_id")
	private BigInteger questionId;
	
	@Column(name="question_description")
	private String questionDescription; 
	
	@Column(name="question_type")
	private String questionType;
	
	@OneToMany(mappedBy="questions")
	private Set<Options> listOfOptions;
	
	@OneToMany(mappedBy="questionId")
	private List<Answer> listOfAnswers;
	
 	
	@ManyToOne
	@JoinColumn(name="survey_id")
	private Survey survey;
	
 	@Column(name="question_isDeleted")
 	private boolean isDeleted;
 	
 	@CreatedBy
	protected String createdBy;
	
	@CreatedDate	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationDate;
	
	@LastModifiedBy
	protected String lastModifiedBy;
	
	@LastModifiedDate
	protected String lastModifiedDate;
	
	public Questions()
	{
		
	}
	public Questions(BigInteger questionId, String questionDescription,
			String questionType, Set<Options> listOfOptions,
			List<Answer> listOfAnswers, Survey survey, boolean isDeleted,
			String createdBy, Date creationDate, String lastModifiedBy,
			String lastModifiedDate) {
		super();
		this.questionId = questionId;
		this.questionDescription = questionDescription;
		this.questionType = questionType;
		this.listOfOptions = listOfOptions;
		this.listOfAnswers = listOfAnswers;
		this.survey = survey;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}





	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}


	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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


	public Set<Options> getListOfOptions() {
		return listOfOptions;
	}


	public void setListOfOptions(Set<Options> listOfOptions) {
		this.listOfOptions = listOfOptions;
	}
	public List<Answer> getListOfAnswers() {
		return listOfAnswers;
	}


	public void setListOfAnswers(List<Answer> listOfAnswers) {
		this.listOfAnswers = listOfAnswers;
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
				+ ((listOfOptions == null) ? 0 : listOfOptions.hashCode());
		result = prime
				* result
				+ ((questionDescription == null) ? 0 : questionDescription
						.hashCode());
		result = prime * result
				+ ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result
				+ ((questionType == null) ? 0 : questionType.hashCode());
		result = prime * result + ((survey == null) ? 0 : survey.hashCode());
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
		Questions other = (Questions) obj;
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
		if (listOfOptions == null) {
			if (other.listOfOptions != null)
				return false;
		} else if (!listOfOptions.equals(other.listOfOptions))
			return false;
		if (questionDescription == null) {
			if (other.questionDescription != null)
				return false;
		} else if (!questionDescription.equals(other.questionDescription))
			return false;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		if (questionType == null) {
			if (other.questionType != null)
				return false;
		} else if (!questionType.equals(other.questionType))
			return false;
		if (survey == null) {
			if (other.survey != null)
				return false;
		} else if (!survey.equals(other.survey))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", questionDescription="
				+ questionDescription + ", questionType=" + questionType
				+ ", listOfOptions=" + listOfOptions + ", survey=" + survey
				+ ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", lastModifiedBy="
				+ lastModifiedBy + ", lastModifiedDate=" + lastModifiedDate
				+ "]";
	}

	
}
