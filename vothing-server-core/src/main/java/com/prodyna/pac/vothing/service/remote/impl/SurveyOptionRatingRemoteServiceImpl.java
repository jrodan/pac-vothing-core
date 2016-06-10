package com.prodyna.pac.vothing.service.remote.impl;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.model.impl.SurveyOptionRating;
import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.model.remote.ObjectConverterHelper;
import com.prodyna.pac.vothing.model.remote.SurveyRemote;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.security.PermissionAnn;
import com.prodyna.pac.vothing.service.SurveyOptionRatingService;
import com.prodyna.pac.vothing.service.SurveyOptionService;
import com.prodyna.pac.vothing.service.SurveyService;
import com.prodyna.pac.vothing.service.remote.SurveyOptionRatingRemoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/restricted/surveyoptionrating")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public class SurveyOptionRatingRemoteServiceImpl implements SurveyOptionRatingRemoteService {

	@Inject
	private SurveyService surveyService;

	@Inject
	private SurveyOptionService surveyOptionService;

	@Inject
	private SurveyOptionRatingService surveyOptionRatingService;

	@Inject
	private Vothing vothing;

	@Inject
	private ObjectConverterHelper objectConverterHelper;

	@GET
	@Path("/add/{surveyOptionId}")
	@PermissionAnn(permission = PermissionEnum.SURVEYOPTIONRATING_UPDATE)
	@Override
	public Survey addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId) {

		User userContext = vothing.getUser();
		SurveyOption surveyOptionDB = surveyOptionService.getElement(surveyOptionId);
		Survey survey = surveyOptionDB.getSurvey();
		boolean hasVoted = false;
		long surveyId = surveyOptionDB.getSurvey().getId();

		if (!hasUserVoted(surveyOptionDB.getSurvey())) {
			SurveyOptionRating surveyOptionRating = new SurveyOptionRating();
			surveyOptionRating.setUser(userContext);
			surveyOptionRating.setSurveyOption(surveyOptionDB);
			surveyOptionRatingService.addElement(surveyOptionRating);
			hasVoted = true;
		} else {
			// TODO throw error
		}

		SurveyRemote surveyRemote = objectConverterHelper.toSurveyRemote(surveyId);
		surveyRemote.setUserVoted(hasVoted);

		return surveyRemote;
	}

	private boolean hasUserVoted(Survey survey) {
		User userContext = vothing.getUser();
		boolean hasUserVoted = false;
		for (SurveyOption surveyOptionLocal : survey.getSurveyOptions()) {
			for (SurveyOptionRating surveyOptionRating : surveyOptionLocal.getSurveyOptionRatings()) {
				if (surveyOptionRating.getUser().getId() == userContext.getId()) {
					hasUserVoted = true;
					break;
				}
			}
		}

		return hasUserVoted;
	}

}
