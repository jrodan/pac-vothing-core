package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.model.impl.SurveyOptionRating;
import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.service.SecurityService;
import com.prodyna.pac.vothing.service.SurveyService;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
@Provider
public class ObjectConverterHelper {

	@Inject
	private SurveyService surveyService;

	@Inject
	private SecurityService securityService;

	@Inject
	private Vothing vothing;

	public Survey toSurvey(SurveyRemote surveyRemote) {

		Survey survey = new Survey();

		survey.setId(surveyRemote.getId());
		survey.setSurveyOptions(surveyRemote.getSurveyOptions());
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
