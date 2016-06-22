package com.prodyna.pac.vothing.core.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.service.SurveyService;

import javax.ejb.Stateless;

@Stateless
@VothingMonitoringAnn
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {

}
