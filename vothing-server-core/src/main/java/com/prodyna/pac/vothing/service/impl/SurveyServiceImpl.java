package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.service.SurveyService;

@Stateless
@VothingMonitoring
public class SurveyServiceImpl extends BaseServiceImpl<Survey> implements SurveyService {

	// TODO check permissions before action

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Survey createSurvey(String name) {

		Survey survey = new Survey();
		survey.setName(name);
		survey.setUser(this.vothing.getUser());
		this.vothing.getEntityManager().persist(survey);

		return survey;
	}

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

}
