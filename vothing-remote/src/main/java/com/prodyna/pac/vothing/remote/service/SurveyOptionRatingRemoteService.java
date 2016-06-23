package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.ws.rs.PathParam;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyOptionRatingRemoteService {

	SurveyRemote addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId);
}
