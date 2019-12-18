package com.sms.SurveyManagementSystem.service;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.SurveyManagementSystem.dto.Answer;
import com.sms.SurveyManagementSystem.dto.AssignSurvey;
import com.sms.SurveyManagementSystem.dto.Options;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;
import com.sms.SurveyManagementSystem.repository.OptionRepository;
import com.sms.SurveyManagementSystem.repository.QuestionRepository;
import com.sms.SurveyManagementSystem.repository.SurveyRepository;
import com.sms.SurveyManagementSystem.repository.UserRepository;


@Service
@Transactional

public class SurveyManagementServiceImpl implements SurveyManagementService{

	@Autowired
	SurveyRepository surveyRepository;
	
	@Autowired
	QuestionRepository questionRespository;
	
	@Autowired
	OptionRepository optionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Survey createSurvey(Survey survey) throws UserException {
		// TODO Auto-generated method stub
		if(survey!=null)
		{
			surveyRepository.save(survey);
			return survey;
		}
		
		return null;
	}

	@Override
	public Survey updateSurvey(BigInteger surveyId, Survey survey)
			throws UserException {
		// TODO Auto-generated method stub
		Survey newSurvey = surveyRepository.findById(surveyId).get();
		if(newSurvey!=null)
		{
		newSurvey.setListOfQuestions(survey.getListOfQuestions());
		newSurvey.setListOfUsers(survey.getListOfUsers());
		newSurvey.setSurveyDescription(survey.getSurveyDescription());
		newSurvey.setSurveyTitle(survey.getSurveyTitle());
		newSurvey.setEnableSurvey(survey.isEnableSurvey());
		newSurvey.setDeleted(survey.isDeleted());
		return newSurvey;
		}
		else
		{
		return null;
		}
	}

	@Override
	public boolean removeSurvey(BigInteger surveyId) throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyRepository.findById(surveyId).get();
		if(survey!=null)
		{
			survey.setDeleted(true);
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public List<Survey> getSurveyList() throws UserException {
		// TODO Auto-generated method stub
		List<Survey> listOfSurvey=surveyRepository.findAll();
		Iterator<Survey> iterator=listOfSurvey.iterator();
		while(iterator.hasNext())
		{
			Survey survey=iterator.next();
			if(survey.isDeleted()==false)
			{
				listOfSurvey.add(survey);
			}
		}
		return listOfSurvey;
		
	}
	@Override
	public BigInteger validateSurveyId(String id) {
		// TODO Auto-generated method stub
		BigInteger surveyId=new BigInteger(id);
		Survey survey=surveyRepository.findById(surveyId).get();
		List<Survey> listOfSurvey=surveyRepository.findAll();
		Iterator<Survey> iterator=listOfSurvey.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next()== survey && iterator.next().isDeleted()==false)
			{
				return surveyId;
			}
		}
		
		return null;
	}

	@Override
	public Survey enableSurvey(BigInteger surveyId) {
		// TODO Auto-generated method stub
		Survey survey=surveyRepository.findById(surveyId).get();
		if(survey!=null)
		{
			survey.setEnableSurvey(true);
			return survey;
		}
		else
		{
			return null;
		}
	
	}

	@Override
	public Survey disableSurvey(BigInteger id) {
		// TODO Auto-generated method stub
		Survey survey=surveyRepository.findById(id).get();
		if(survey!=null){
			survey.setEnableSurvey(false);
			return survey;
		}
		else
		{

			return null;
		}
		
	}

	@Override
	public Survey searchSurveyById(BigInteger surveyId) throws UserException {
		// TODO Auto-generated method stub
		List<Survey> listOfSurvey=surveyRepository.findAll();
		Survey survey=surveyRepository.findById(surveyId).get();
		if(listOfSurvey!=null)
		{
			Iterator<Survey> iterator=listOfSurvey.iterator();
			while(iterator.hasNext())
			{
				if(iterator.next().equals(survey) && iterator.next().isDeleted()==false)
				{
					return iterator.next();
				}
			}
			return null;
		}
		else
		return null;
	}

	@Override
	public Questions addQuestion(BigInteger surveyId, Questions question)
			throws UserException {
		// TODO Auto-generated method stub
		Survey survey = surveyRepository.findById(surveyId).get();
		if(survey!=null)
		{
			Questions newQuestion=new Questions();
			newQuestion.setDeleted(false);
			Set<Options> listOfOptions = new HashSet<Options>();
			List<Answer> listOfAnswers = new ArrayList<Answer>();
			newQuestion.setListOfOptions(listOfOptions);
			newQuestion.setListOfAnswers(listOfAnswers);
			newQuestion.setQuestionDescription(question.getQuestionDescription());
			newQuestion.setQuestionType(question.getQuestionType());
			newQuestion.setSurvey(survey);
			questionRespository.save(newQuestion);
			return newQuestion;
			
		}
		else
		{
			return null;
		}
		
		
	}
	@Override
	public BigInteger validateQuestionId(String qId) {
		// TODO Auto-generated method stub
		BigInteger questionId=new BigInteger(qId);
		Questions question = questionRespository.findById(questionId).get();
		if(question!=null && question.isDeleted()== false)
		{
			return questionId;
		}
		return null;
	}

	@Override
	public boolean deleteQuestion(BigInteger surveyId, BigInteger questionId)
			throws UserException {
		// TODO Auto-generated method stub
		Survey survey=surveyRepository.findById(surveyId).get();
		List<Questions> listOfQuestions = survey.getListOfQuestions();
		Iterator<Questions> iterator=listOfQuestions.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getQuestionId()== questionId)
			{
				iterator.next().setDeleted(true);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Questions updateQuestion(BigInteger surveyId, BigInteger questionId,
			Questions question) throws UserException {
		// TODO Auto-generated method stub
		Survey survey = surveyRepository.findById(surveyId).get();
		List<Questions> listOfQuestions =  survey.getListOfQuestions();
		Iterator<Questions> iterator = listOfQuestions.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getQuestionId()== questionId && iterator.next().isDeleted()== false)
			{
				Questions newQuestion =iterator.next();
				newQuestion.setDeleted(question.isDeleted());
				newQuestion.setListOfAnswers(question.getListOfAnswers());
				newQuestion.setListOfOptions(question.getListOfOptions());
				newQuestion.setQuestionType(question.getQuestionType());
				newQuestion.setQuestionDescription(question.getQuestionDescription());
				return newQuestion;
			}
		}
		return null;
	}

	

	@Override
	public Options addOption(BigInteger questionId, Options option) {
		// TODO Auto-generated method stub
		Options newOption = new Options();
		if(option!=null)
		{
			newOption.setQuestions(option.getQuestions());
			newOption.setAnswer(null);
			newOption.setDeleted(false);
			newOption.setOptionDescription(option.getOptionDescription());
			optionRepository.save(newOption);
			Questions question = questionRespository.findById(questionId).get();
			question.getListOfOptions().add(newOption);
			return newOption;
			
		}
		else return null;
	}

	@Override
	public BigInteger validateOptionId(String oId) {
		// TODO Auto-generated method stub
		BigInteger optionId = new BigInteger(oId);
		Options option = optionRepository.findById(optionId).get();
		if(option!=null && option.isDeleted()==false)
		{
			return optionId;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public boolean deleteOptions(BigInteger questionId, BigInteger optionId) {
		// TODO Auto-generated method stub
		Questions question = questionRespository.findById(questionId).get();
		if(question!=null)
		{
			Set<Options> listOfOptions = question.getListOfOptions();
			Iterator<Options> iterator = listOfOptions.iterator();
			while(iterator.hasNext())
			{
				if(iterator.next().getOptionId() == optionId)
				{
					iterator.next().setDeleted(true);
					return true;
				}
			}
			return false;
		}
		else
		return false;
	}

	@Override
	public Options updateOption(BigInteger questionId, BigInteger optionId,
			Options option) {
		// TODO Auto-generated method stub
		Questions question = questionRespository.findById(questionId).get();
		Set<Options> listOfOptions = question.getListOfOptions();
		Iterator<Options> iterator = listOfOptions.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getOptionId()==optionId && iterator.next().isDeleted()==false)
			{
				iterator.next().setOptionDescription(option.getOptionDescription());
				iterator.next().setAnswer(option.getAnswer());
				iterator.next().setDeleted(option.isDeleted());
				iterator.next().setQuestions(option.getQuestions());
				return iterator.next();
			}
		}
		
		return null;
	}

	@Override
	public BigInteger validateUserId(String uId) {
		// TODO Auto-generated method stub
		BigInteger userId = new BigInteger(uId);
		User user = userRepository.findById(userId).get();
		if(user!=null)
		{
			return user.getUserId();
		}
		else
		return null;
	}
	@Override
	public boolean distributeSurvey(BigInteger userId, BigInteger surveyId)
			throws UserException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).get();
		Survey survey = surveyRepository.findById(surveyId).get();
		if(user!=null && survey !=null)
		{
			Set<Survey> listOfSurvey = user.getAssignedSurvey();
			listOfSurvey.add(survey);
			AssignSurvey assignSurvey =new AssignSurvey();
			
			
			return true;
		}
		else
		return false;
	}

	@Override
	public List<User> viewNoOfRespondents(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewPendingSurvey(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

	

}
