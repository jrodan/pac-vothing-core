package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOptionRating;

import javax.ws.rs.PathParam;

/**
 * Created by jrodan on 18/05/16.
 */
public interface SurveyOptionRatingRemoteService {

    Survey addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId);
}
