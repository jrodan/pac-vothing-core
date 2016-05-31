package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.model.impl.SurveyOptionRating;

import javax.ws.rs.PathParam;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyOptionRatingRemoteService {

    SurveyOptionRating addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId);
}
