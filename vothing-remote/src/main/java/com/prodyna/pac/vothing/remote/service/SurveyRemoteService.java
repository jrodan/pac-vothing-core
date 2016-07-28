package com.prodyna.pac.vothing.remote.service;

import com.prodyna.pac.vothing.remote.model.SurveyRemote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by jrodan on 18/05/16.
 */
@Path("/restricted/survey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SurveyRemoteService {

	@POST
	@Path("/add")
	SurveyRemote createSurvey(SurveyRemote survey);

	@GET
	@Path("/list")
	List<SurveyRemote> getSurveys(@PathParam("order") String order);

	@GET
	@Path("/list")
	List<SurveyRemote> getSurveys();

	@POST
	@Path("/update")
	SurveyRemote updateSurvey(SurveyRemote survey);

	@GET
	@Path("/get/{surveyId}")
	SurveyRemote getSurvey(@PathParam("surveyId") long surveyId);

	@PUT
	@Path("/delete/{surveyId}")
	void deleteVote(@PathParam("surveyId") long surveyId);
}
