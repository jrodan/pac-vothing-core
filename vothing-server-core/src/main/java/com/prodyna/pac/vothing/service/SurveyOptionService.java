package com.prodyna.pac.vothing.service;

import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;

import java.util.Collection;

@VothingMonitoring
public interface SurveyOptionService extends BaseService<SurveyOption>{

	Survey updateSurveyOptions(long surveyId, Collection<SurveyOption> surveyOptionList);
}
