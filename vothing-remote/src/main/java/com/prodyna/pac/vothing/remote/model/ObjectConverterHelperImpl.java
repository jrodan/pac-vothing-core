package com.prodyna.pac.vothing.remote.model;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.api.service.SecurityService;
import com.prodyna.pac.vothing.api.service.SurveyService;
import com.prodyna.pac.vothing.core.model.SurveyImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
@RequestScoped
public class ObjectConverterHelperImpl implements ObjectConverterHelper {

	@Inject
	private SurveyService surveyService;

	@Inject
	private SecurityService securityService;

	@Inject
	private Vothing vothing;

	@Override
	public Survey toSurvey(SurveyRemote surveyRemote) {

		Survey survey = new SurveyImpl();

		survey.setId(surveyRemote.getId());
		survey.setSurveyOptions(surveyRemote.getSurveyOptions());
		survey.setName(surveyRemote.getName());
		survey.setUser(surveyRemote.getUser());
		survey.setCreateDate(surveyRemote.getCreateDate());
		survey.setModifiedDate(surveyRemote.getModifiedDate());

		return survey;
	}

	@Override
	public SurveyRemote toSurveyRemote(long surveyId) {

		User user = vothing.getUser();
		Survey survey = surveyService.getElement(surveyId);
		SurveyRemote surveyRemote = new SurveyRemote();

		surveyRemote.setId(survey.getId());
		surveyRemote.setSurveyOptions(survey.getSurveyOptions());
		surveyRemote.setName(survey.getName());
		surveyRemote.setUser(survey.getUser());
		surveyRemote.setCreateDate(survey.getCreateDate());
		surveyRemote.setModifiedDate(survey.getModifiedDate());

		List<SurveyOptionRemote> surveyOptions = new ArrayList<>();

		// count votes and set if user has already voted
		int votes = 0;
		boolean hasUserVoted = false;
		for (SurveyOption surveyOptionLocal : survey.getSurveyOptions()) {
			boolean hasUserVotedThisOption = false;
			votes += surveyOptionLocal.getSurveyOptionRatings().size();
			for (SurveyOptionRating surveyOptionRating : surveyOptionLocal.getSurveyOptionRatings()) {
				if (surveyOptionRating.getUser().getId() == user.getId()) {
					hasUserVoted = true;
					hasUserVotedThisOption = true;
					break;
				}
			}
			SurveyOptionRemote surveyOptionRemote = new SurveyOptionRemote(surveyOptionLocal);
			surveyOptionRemote.setUserVotedThisOption(hasUserVotedThisOption);
			surveyOptions.add(surveyOptionRemote);
		}
		surveyRemote.setSurveyOptionsRemote(surveyOptions);
		surveyRemote.setSurveyOptions(new ArrayList<>());
		surveyRemote.setVotes(votes);
		surveyRemote.setUserVoted(hasUserVoted);

		// set permissions to object
		List<String> permissions = securityService.getUserSurveyPermissions(user, survey);
		surveyRemote.setUsersPermissions(permissions);

		return surveyRemote;
	}


}
