package com.sms.SurveyManagementSystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.SurveyManagementSystem.dto.AssignSurvey;

public interface AssignSurveyRepository extends JpaRepository<AssignSurvey,BigInteger>{

}
