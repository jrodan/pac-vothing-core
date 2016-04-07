package com.prodyna.pac.vothing.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.security.PermissionAnn;
import com.prodyna.pac.vothing.security.PermissionEnum;

@Path("/restricted/survey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public interface SurveyService extends BaseService<Survey>{
	
	@GET
	@Path("/add/{name}")
	Survey createSurvey(@PathParam("name") String name);
	
	@GET
	@Path("/list")
	@PermissionAnn(permission = PermissionEnum.SURVEY_ADD)
	List<Survey> getSurveys();
	
//	@GET
//	@Path("/list/{surveyId}")
	Survey getSurvey(@PathParam("surveyId") long surveyId);
	
	@GET
	@Path("/delete/{surveyId}")
	void deleteVote(@PathParam("surveyId") long surveyId);

}
