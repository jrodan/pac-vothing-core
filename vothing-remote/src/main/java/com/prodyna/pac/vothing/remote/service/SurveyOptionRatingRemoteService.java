package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by jrodan on 18/05/16.
 */
@Path("/restricted/surveyoptionrating")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SurveyOptionRatingRemoteService {

	@GET
	@Path("/add/{surveyOptionId}")
	SurveyRemote addSurveyOptionRating(@PathParam("surveyOptionId") long surveyOptionId);
}
