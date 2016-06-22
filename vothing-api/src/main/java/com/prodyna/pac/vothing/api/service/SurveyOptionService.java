package com.prodyna.pac.vothing.api.service;

import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOption;

import java.util.Collection;

@VothingMonitoringAnn
public interface SurveyOptionService extends BaseService<SurveyOption> {

	Survey updateSurveyOptions(long surveyId, Collection<SurveyOption> surveyOptionList);
}
