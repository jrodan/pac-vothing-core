package com.prodyna.pac.vothing.service.impl;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.service.SurveyService;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@VothingMonitoring
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public List<Survey> getSurveys() {
		return super.getElements();
	}

	@Override
	public Survey getSurvey(long surveyId) {
		return super.getElement(surveyId);
	}

	@Override
	public void deleteVote(long surveyId) {
		super.deleteElement(surveyId);
	}

	@Override
	public Survey updateSurvey(Survey survey) {
		return super.addElement(survey);
	}

	@Override
	public Survey createSurvey(Survey survey) {
		return super.addElement(survey);
	}


}
