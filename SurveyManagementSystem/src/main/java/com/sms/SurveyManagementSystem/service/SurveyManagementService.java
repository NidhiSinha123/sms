package com.sms.SurveyManagementSystem.service;

import java.math.BigInteger;
import java.util.List;

import com.sms.SurveyManagementSystem.dto.Options;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;

public interface SurveyManagementService {
	public Survey createSurvey(Survey survey) throws UserException;
	public Survey updateSurvey(BigInteger surveyId,Survey survey) throws UserException;
	public boolean removeSurvey(BigInteger surveyId)throws UserException;
	public List<Survey> getSurveyList() throws UserException;
	public Survey searchSurveyById(BigInteger surveyId) throws UserException;
	public Questions addQuestion(BigInteger surveyId,Questions question) throws UserException;
	public boolean deleteQuestion(BigInteger surveyId,BigInteger questionId)throws UserException;
	public Questions updateQuestion(BigInteger surveyId,BigInteger questionId,Questions question)throws UserException;
	public boolean distributeSurvey(BigInteger userId,BigInteger surveyId)throws UserException;
	public List<User> viewNoOfRespondents(String surveyId);
	public List<User> viewPendingSurvey(String surveyId);
	public BigInteger validateSurveyId(String id);
	public Survey enableSurvey(BigInteger surveyId);
	public Survey disableSurvey(BigInteger id);
	public BigInteger validateQuestionId(String qId);
	public Options addOption(BigInteger questionId, Options option);
	public BigInteger validateOptionId(String oId);
	public boolean deleteOptions(BigInteger questionId, BigInteger optionId);
	public Options updateOption(BigInteger questionId, BigInteger optionId,
			Options option);
	public BigInteger validateUserId(String uId);

}
