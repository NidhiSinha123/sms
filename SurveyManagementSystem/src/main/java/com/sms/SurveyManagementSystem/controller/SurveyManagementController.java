package com.sms.SurveyManagementSystem.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.sms.SurveyManagementSystem.dto.AssignSurvey;
import com.sms.SurveyManagementSystem.dto.Options;
import com.sms.SurveyManagementSystem.dto.Questions;
import com.sms.SurveyManagementSystem.dto.Survey;
import com.sms.SurveyManagementSystem.dto.User;
import com.sms.SurveyManagementSystem.exception.UserException;
import com.sms.SurveyManagementSystem.repository.SurveyRepository;
import com.sms.SurveyManagementSystem.service.SurveyManagementService;
/*
 * Author:  Nidhi
 * Description:Controller
 * Created on: December 13, 2019
 * 
 */
@RestController
public class SurveyManagementController {

	@Autowired
	SurveyManagementService service;
	
	@PostMapping(value="/addSurvey")
	public ResponseEntity<?> addSurvey(@RequestBody Survey survey)
	{
		Survey newSurvey = new Survey();
		try
		{
			List<Questions> listOfQuestions = new ArrayList<Questions>();
			Set<User> listOfUsers = new HashSet<User>();
			newSurvey.setSurveyTitle(survey.getSurveyTitle());
			newSurvey.setSurveyDescription(survey.getSurveyDescription());
			newSurvey.setDeleted(false);
			newSurvey.setListOfQuestions(listOfQuestions);
			newSurvey.setListOfUsers(listOfUsers);
			newSurvey.setEnableSurvey(true);
			service.createSurvey(newSurvey);
			
		}
		catch(UserException exception)
		{
			return new ResponseEntity<Survey>(newSurvey,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Survey>(newSurvey,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/deleteSurvey")
	public ResponseEntity<?> deleteSurvey(@RequestParam("surveyId") String id)
	{
		BigInteger surveyId = service.validateSurveyId(id);
		if(surveyId!=null)
		{
			try
			{
				boolean status= service.removeSurvey(surveyId);
				if(status)
				{
					return new ResponseEntity<String>("Survey deleted successfully",HttpStatus.OK);
				}
				
					return new ResponseEntity<String>("Survey not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch(UserException exception)
			{
				return new ResponseEntity<String>("Exception while deleting",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("No survey found with the given survey id",HttpStatus.OK);
		}
	}
	
	@PutMapping(value="/updateSurvey")
	public ResponseEntity<?> updateSurvey(@RequestParam("surveyId") String id,@ModelAttribute Survey survey) throws UserException
	{
		BigInteger surveyId = service.validateSurveyId(id);
		if(surveyId!=null && survey!=null)
		{
			Survey newSurvey = new Survey();
			newSurvey.setSurveyId(surveyId);
			newSurvey.setSurveyTitle(survey.getSurveyTitle());
			newSurvey.setSurveyDescription(survey.getSurveyDescription());
			List<Questions> listOfQuestions = new ArrayList<Questions>();
			Set<User> listOfUsers = new HashSet<User>();
			newSurvey.setDeleted(false);
			newSurvey.setListOfQuestions(listOfQuestions);
			newSurvey.setListOfUsers(listOfUsers);
			newSurvey.setEnableSurvey(survey.isEnableSurvey());
			Survey status=service.updateSurvey(surveyId, newSurvey);
			if(status!=null)
			{
				return new ResponseEntity<String>("Survey updated successfully",HttpStatus.OK);
				
			}
			else
			{
				return new ResponseEntity<String>("Survey not updated",HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		else
		{
			return new ResponseEntity<String>("Survey with the given surveyid is not present",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	@GetMapping(value="/showAllSurvey")
	public ResponseEntity<?> showAllSurvey() throws UserException
	{
		List<Survey> listOfSurvey=service.getSurveyList();
		if(listOfSurvey!=null)
		{
			return new ResponseEntity<List>(listOfSurvey,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("No survey found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/enableSurvey")
	public ResponseEntity<?> enableSurvey(@RequestParam("surveyId")String id)
	{
		BigInteger surveyId=service.validateSurveyId(id);
		if(surveyId!=null)
		{
			Survey survey=service.enableSurvey(surveyId);
			if(survey!=null)
			{
				return new ResponseEntity<String>("Survey enabled successfully",HttpStatus.OK);
				
			}
			else
			{
				return new ResponseEntity<String>("Survey not enabled",HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		}
		else
		{
			return new ResponseEntity<String>("Survey with the given surveyid is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/disableSurvey")
	public ResponseEntity<?> disableSurvey(@RequestParam("surveyId") String id)
	{
		BigInteger surveyId=service.validateSurveyId(id);
		if(surveyId!=null)
		{
			Survey survey=service.disableSurvey(surveyId);
			if(survey!=null)
			{
				return new ResponseEntity<String>("Survey disabled successfully",HttpStatus.OK);
				
			}
			else
			{
				return new ResponseEntity<String>("Survey not disabled",HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		}
		else
		{
			return new ResponseEntity<String>("Survey with the given surveyid is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/addQuestion")
	public ResponseEntity<?> addQuestion(@RequestParam("surveyId")String id,@RequestBody Questions question) throws UserException
	{
		BigInteger surveyId=service.validateSurveyId(id);
		if(surveyId!=null)
		{
			if(question!=null)
			{
				Questions newQuestion=service.addQuestion(surveyId, question);
				if(newQuestion!=null)
				{
					return new ResponseEntity<String>("Question added successfully",HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Question not added",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else
			{
				return new ResponseEntity<String>("Question is null",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("Survey with given survey id is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(value="/deleteQuestion")
	public ResponseEntity<?> deleteQuestion(@RequestParam("surveyId") String sId,@RequestParam("questionId") String qId) throws UserException
	{
		BigInteger surveyId=service.validateSurveyId(sId);
		BigInteger questionId=service.validateQuestionId(qId);
		if(surveyId!=null && questionId!=null)
		{
			boolean isDeleted = service.deleteQuestion(surveyId, questionId);
			if(isDeleted)
			{
				return new ResponseEntity<String>("Question deleted successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Question not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("Survey or question is null",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(value="/updateQuestion")
	public ResponseEntity<?> updateQuestion(@RequestParam("surveyId") String sId,@RequestParam("questionId") String qId,
			Questions question) throws UserException
		{
		BigInteger surveyId=service.validateSurveyId(sId);
		BigInteger questionId=service.validateQuestionId(qId);
		if(surveyId!=null && questionId!=null)
		{
			Questions updatedQuestion=service.updateQuestion(surveyId, questionId, question);
			
			if(updatedQuestion!=null)
			{
				return new ResponseEntity<String>("Question updated successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Question not updated",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("Survey or question is null",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	@PostMapping(value="/addOption")
	public ResponseEntity<?> addOption(@RequestParam("questionId")String qId,@RequestBody Options option)
	{
		BigInteger questionId=service.validateQuestionId(qId);
		if(questionId!=null)
		{
			if(option!=null)
			{
				Options result=service.addOption(questionId,option);
				if(result!=null)
				{
					return new ResponseEntity<String>("Option added successfully",HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<String>("Option not added",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			else
			{
				return new ResponseEntity<String>("Option is null",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("Question with the given question id is not present",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping(value="/deleteOption")
	public ResponseEntity<?> deleteOption(@RequestParam("questionId") String qId,@RequestParam("optionId") String oId)
	{
		BigInteger questionId = service.validateQuestionId(qId);
		BigInteger optionId = service.validateOptionId(oId);
		if(questionId!=null && optionId!=null)
		{
			boolean isDeleted = service.deleteOptions(questionId,optionId);
			if(isDeleted)
			{
				return new ResponseEntity<String>("option deleted successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Option not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("questionId or optionId is null",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping(value="/updateOption")
	public ResponseEntity<?> updateOption(@RequestParam("questionId")String qId,@RequestParam("optionId") String oId,
	@RequestBody Options option)
			{
				BigInteger questionId= service.validateQuestionId(qId);
				BigInteger optionId = service.validateOptionId(oId);
				if(questionId!=null && optionId!=null)
				{
					Options newOption=service.updateOption(questionId,optionId,option);
					if(newOption!=null)
					{
						return new ResponseEntity<String>("Option updated successfully",HttpStatus.OK);
					}
					else
					{
						return new ResponseEntity<String>("Option not updated ",HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				else
				{
					return new ResponseEntity<String>("question id or option id is null",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
	
	@PostMapping(value="/assignSurvey")
	public ResponseEntity<?> assignSurvey(@RequestParam("userId")String uId,@RequestParam("surveyId")String sId) throws UserException
	{
		BigInteger userId = service.validateUserId(uId);
		BigInteger surveyId = service.validateSurveyId(sId);
		if(userId!=null && surveyId!=null)
		{
			boolean isAssigned = service.distributeSurvey(userId, surveyId);
			if(isAssigned)
			{
				return new ResponseEntity<String>("survey assigned successfully",HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("Survey not assigned",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else
		{
			return new ResponseEntity<String>("userid or surveyid is null",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}