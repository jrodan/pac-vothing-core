package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.service.SurveyService;

@Stateless
@VothingMonitoring
public class SurveyServiceImpl implements SurveyService {

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
		logger.trace("Retrieving all Surveys from DB.");

		List<Survey> surveys = this.vothing
				.getEntityManager()
				.createNamedQuery(VothingConstants.SELECT_ALL_SURVEYS,
						Survey.class).getResultList();

		return surveys;
	}

	@Override
	public Survey getSurvey(long surveyId) {
		final Survey survey = this.vothing.getEntityManager().find(
				Survey.class, surveyId);
		if (survey == null) {
			throw new EntityNotFoundException(
					"Survey could not be found for given surveyId [" + surveyId
							+ "]");
		}

		return survey;
	}

	@Override
	public void deleteVote(long surveyId) {
		final Survey survey = this.vothing.getEntityManager().find(
				Survey.class, surveyId);
		this.vothing.getEntityManager().remove(survey);
	}

}
