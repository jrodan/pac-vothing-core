package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.service.SurveyService;

import javax.ejb.Stateless;

@Stateless
@VothingMonitoring
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {

}
