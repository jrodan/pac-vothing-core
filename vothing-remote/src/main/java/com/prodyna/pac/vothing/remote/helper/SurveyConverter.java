package com.prodyna.pac.vothing.remote.helper;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.api.service.SecurityService;
import com.prodyna.pac.vothing.api.service.SurveyService;
import com.prodyna.pac.vothing.core.model.SurveyImpl;
import com.prodyna.pac.vothing.remote.model.SurveyOptionRemote;
import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
@RequestScoped
public class SurveyConverter  {

	@Inject
	private SurveyService surveyService;

	@Inject
	private SecurityService securityService;

	@Inject
	private Vothing vothing;

	@Inject
	private SurveyOptionConverter surveyOptionConverter;

	@Inject
	private SurveyOptionRatingConverter surveyOptionRatingConverter;

	public Survey toSurvey(SurveyRemote surveyRemote) {

		Survey survey = new SurveyImpl();

		survey.setId(surveyRemote.getId());
		survey.setSurveyOptions(surveyOptionConverter.toSurveyOptions(surveyRemote.getSurveyOptionsRemote()));
		survey.setName(surveyRemote.getName());
		survey.setUser(surveyRemote.getUser());
		survey.setCreateDate(surveyRemote.getCreateDate());
		survey.setModifiedDate(surveyRemote.getModifiedDate());

		return survey;
	}

	public SurveyRemote toSurveyRemote(long surveyId) {

		User user = vothing.getUser();
		Survey survey = surveyService.getElement(surveyId);
		SurveyRemote surveyRemote = new SurveyRemote();

		surveyRemote.setId(survey.getId());
		surveyRemote.setName(survey.getName());
		surveyRemote.setUser(survey.getUser());
		surveyRemote.setCreateDate(survey.getCreateDate());
		surveyRemote.setModifiedDate(survey.getModifiedDate());
		surveyRemote.setSurveyOptionsRemote(surveyOptionConverter
				.toSurveyOptionsRemote(user, survey.getSurveyOptions()));

		boolean hasUserVoted = false;
		long votes = 0;

		for(SurveyOptionRemote surveyOptionRemote : surveyRemote.getSurveyOptionsRemote()) {
			votes += surveyOptionRemote.getVotes();
			if(!hasUserVoted) {
				hasUserVoted = surveyOptionRemote.hasUserVoted();
			}
		}

		surveyRemote.setVotes(votes);
		surveyRemote.setUserVoted(hasUserVoted);

		// set permissions to object
		List<String> permissions = securityService.getUserSurveyPermissions(user, survey);
		surveyRemote.setUsersPermissions(permissions);

		return surveyRemote;
	}

}
