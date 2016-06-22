package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.api.model.Survey;

import javax.ws.rs.PathParam;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyOptionRatingRemoteService {

	Survey addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId);
}
