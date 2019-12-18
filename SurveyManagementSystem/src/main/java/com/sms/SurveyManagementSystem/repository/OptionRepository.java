package com.sms.SurveyManagementSystem.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.SurveyManagementSystem.dto.Options;

public interface OptionRepository extends JpaRepository<Options, BigInteger>{

}
