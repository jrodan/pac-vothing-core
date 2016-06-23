package com.prodyna.pac.vothing.remote.service.impl;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.model.SurveyOption;
import com.prodyna.pac.vothing.api.model.SurveyOptionRating;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.api.service.SurveyOptionRatingService;
import com.prodyna.pac.vothing.api.service.SurveyOptionService;
import com.prodyna.pac.vothing.api.service.SurveyService;
import com.prodyna.pac.vothing.core.annotion.PermissionAnn;
import com.prodyna.pac.vothing.core.model.SurveyOptionRatingImpl;
import com.prodyna.pac.vothing.remote.helper.SurveyConverter;
import com.prodyna.pac.vothing.remote.model.SurveyRemote;
import com.prodyna.pac.vothing.remote.service.SurveyOptionRatingRemoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/restricted/surveyoptionrating")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoringAnn
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
	private SurveyConverter surveyConverter;

	@GET
	@Path("/add/{surveyOptionId}")
	@PermissionAnn(permission = PermissionEnum.SURVEYOPTIONRATING_UPDATE)
	@Override
	public SurveyRemote addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId) {

		User userContext = vothing.getUser();
		SurveyOption surveyOptionDB = surveyOptionService.getElement(surveyOptionId);
		boolean hasVoted = false;
		long surveyId = surveyOptionDB.getSurvey().getId();

		if (!hasUserVoted(surveyOptionDB.getSurvey())) {
			SurveyOptionRating surveyOptionRating = new SurveyOptionRatingImpl();
			surveyOptionRating.setUser(userContext);
			surveyOptionRating.setSurveyOption(surveyOptionDB);
			surveyOptionRatingService.addElement(surveyOptionRating);
			hasVoted = true;
		} else {
			// TODO throw error
		}

		SurveyRemote surveyRemote = surveyConverter.toSurveyRemote(surveyId);
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
