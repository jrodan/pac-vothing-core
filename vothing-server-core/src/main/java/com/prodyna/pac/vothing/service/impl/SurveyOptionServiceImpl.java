package com.prodyna.pac.vothing.service.impl;

import javax.ejb.Stateless;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.SurveyOption;
import com.prodyna.pac.vothing.service.SurveyOptionService;

@Stateless
@VothingMonitoring
public class SurveyOptionServiceImpl extends BaseServiceImpl<SurveyOption> implements SurveyOptionService {

}
