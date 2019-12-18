package com.sms.SurveyManagementSystem.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Options {
	
	@Id
	private BigInteger optionId;
	
	@Column(name="option_description")
	private String optionDescription;
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private Questions questions;
	
	@ManyToOne
	@JoinColumn(name="answer_id")
	private Answer answer;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	public Options()
	{
		
	}

	public Options(BigInteger optionId, String optionDescription,
			Questions questions, Answer answer, boolean isDeleted) {
		super();
		this.optionId = optionId;
		this.optionDescription = optionDescription;
		this.questions = questions;
		this.answer = answer;
		this.isDeleted = isDeleted;
	}



	public BigInteger getOptionId() {
		return optionId;
	}

	public void setOptionId(BigInteger optionId) {
		this.optionId = optionId;
	}

	public String getOptionDescription() {
		return optionDescription;
	}

	public void setOptionDescription(String optionDescription) {
		this.optionDescription = optionDescription;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime
				* result
				+ ((optionDescription == null) ? 0 : optionDescription
						.hashCode());
		result = prime * result
				+ ((optionId == null) ? 0 : optionId.hashCode());
		result = prime * result
				+ ((questions == null) ? 0 : questions.hashCode());
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
		Options other = (Options) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (optionDescription == null) {
			if (other.optionDescription != null)
				return false;
		} else if (!optionDescription.equals(other.optionDescription))
			return false;
		if (optionId == null) {
			if (other.optionId != null)
				return false;
		} else if (!optionId.equals(other.optionId))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Options [optionId=" + optionId + ", optionDescription="
				+ optionDescription + ", questions=" + questions + ", answer="
				+ answer + ", isDeleted=" + isDeleted + "]";
	}


}
