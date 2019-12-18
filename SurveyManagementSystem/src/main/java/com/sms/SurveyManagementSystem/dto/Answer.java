package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Answer {
	
	@Id
	@Column(name="answer_id")
	private BigInteger answerId;
	
	@Column(name="question_description")
	private String description;
	
	@OneToMany(mappedBy="answer")
	private Set<Options> listOfOptions;
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private Questions questionId;
	
	@ManyToOne
	@JoinColumn(name="assigned_survey_id")
	private AssignSurvey assignedSurvey;
	
	public Answer()
	{

	}

	public Answer(BigInteger answerId, Questions question, String description,
			Set<Options> listOfOptions, Questions questionId,
			AssignSurvey assignedSurvey) {
		super();
		this.answerId = answerId;
		this.description = description;
		this.listOfOptions = listOfOptions;
		this.questionId = questionId;
		this.assignedSurvey = assignedSurvey;
	}

	public BigInteger getAnswerId() {
		return answerId;
	}

	public void setAnswerId(BigInteger answerId) {
		this.answerId = answerId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Options> getListOfOptions() {
		return listOfOptions;
	}

	public void setListOfOptions(Set<Options> listOfOptions) {
		this.listOfOptions = listOfOptions;
	}

	public Questions getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Questions questionId) {
		this.questionId = questionId;
	}

	public AssignSurvey getAssignedSurvey() {
		return assignedSurvey;
	}

	public void setAssignedSurvey(AssignSurvey assignedSurvey) {
		this.assignedSurvey = assignedSurvey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((answerId == null) ? 0 : answerId.hashCode());
		result = prime * result
				+ ((assignedSurvey == null) ? 0 : assignedSurvey.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((listOfOptions == null) ? 0 : listOfOptions.hashCode());
		
		result = prime * result
				+ ((questionId == null) ? 0 : questionId.hashCode());
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
		Answer other = (Answer) obj;
		if (answerId == null) {
			if (other.answerId != null)
				return false;
		} else if (!answerId.equals(other.answerId))
			return false;
		if (assignedSurvey == null) {
			if (other.assignedSurvey != null)
				return false;
		} else if (!assignedSurvey.equals(other.assignedSurvey))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (listOfOptions == null) {
			if (other.listOfOptions != null)
				return false;
		} else if (!listOfOptions.equals(other.listOfOptions))
			return false;
		
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId 
				+ ", description=" + description + ", listOfOptions="
				+ listOfOptions + ", questionId=" + questionId
				+ ", assignedSurvey=" + assignedSurvey + "]";
	}

	
	
	
	
	
	

}
