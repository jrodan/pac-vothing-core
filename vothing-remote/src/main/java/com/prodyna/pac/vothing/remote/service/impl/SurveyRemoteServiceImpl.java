package com.prodyna.pac.vothing.remote.service.impl;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.annotion.VothingMonitoringAnn;
import com.prodyna.pac.vothing.api.constants.EntityOrder;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.Survey;
import com.prodyna.pac.vothing.api.service.SurveyOptionService;
import com.prodyna.pac.vothing.api.service.SurveyService;
import com.prodyna.pac.vothing.core.annotion.PermissionAnn;
import com.prodyna.pac.vothing.remote.model.ObjectConverterHelper;
import com.prodyna.pac.vothing.remote.model.SurveyRemote;
import com.prodyna.pac.vothing.remote.service.SurveyRemoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Provider
@Path("/restricted/survey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoringAnn
public class SurveyRemoteServiceImpl implements SurveyRemoteService {

	@Inject
	private SurveyService surveyService;

	@Inject
	private SurveyOptionService surveyOptionService;

	@Inject
	private Vothing vothing;

	@Inject
	private ObjectConverterHelper objectConverterHelper;

	@Override
	@POST
	@Path("/add")
	@PermissionAnn(permission = PermissionEnum.SURVEY_ADD)
	public SurveyRemote createSurvey(Survey survey) {
		survey.setUser(vothing.getUser());
		Survey surveyDB = surveyService.addElement(survey);
		surveyDB = surveyOptionService.updateSurveyOptions(surveyDB.getId(), survey.getSurveyOptions());
		SurveyRemote surveyRemote = getRemoteSurveyFromSurvey(surveyDB);
		return surveyRemote;
	}

	@Override
	@GET
	@Path("/list")
	@PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
	public List<SurveyRemote> getSurveys(@PathParam("order") String order) {
		List<SurveyRemote> surveysReturn = new ArrayList<>();

		List<Survey> surveys = surveyService.getElements(new EntityOrder(order));

		// add permissions for each entity
		for (Survey survey : surveys) {
			SurveyRemote surveyRemote = getRemoteSurveyFromSurvey(survey);
			surveysReturn.add(surveyRemote);
		}

		return surveysReturn;
	}

	@Override
	@GET
	@Path("/list")
	@PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
	public List<SurveyRemote> getSurveys() {
		return this.getSurveys(null);
	}

	@Override
	@POST
	@Path("/update")
	@PermissionAnn(permission = PermissionEnum.SURVEY_UPDATE)
	public SurveyRemote updateSurvey(Survey survey) {
		SurveyRemote surveyRemote;

		// get existing element from db
		Survey surveyDB = surveyService.getElement(survey.getId());

		// copy attributes
		surveyDB.setName(survey.getName());
		surveyDB.setModifiedDate(new Date());
		surveyDB.setUser(vothing.getUser());
		surveyDB.setSurveyOptions(survey.getSurveyOptions());

		// update surveyOptions and survey
		Survey surveyDB2 = surveyOptionService.updateSurveyOptions(surveyDB.getId(), survey.getSurveyOptions());

		surveyDB.setSurveyOptions(surveyDB2.getSurveyOptions());
		surveyService.updateElement(surveyDB);

		surveyRemote = getRemoteSurveyFromSurvey(surveyDB);

		return surveyRemote;
	}

	@Override
	@GET
	@Path("/get/{surveyId}")
	@PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
	public SurveyRemote getSurvey(@PathParam("surveyId") long surveyId) {
		SurveyRemote surveyRemote = null;

		Survey surveyDB = surveyService.getElement(surveyId);

		if (surveyDB != null) {
			surveyRemote = getRemoteSurveyFromSurvey(surveyDB);
		}

		return surveyRemote;
	}

	private SurveyRemote getRemoteSurveyFromSurvey(Survey survey) {
		SurveyRemote surveyRemote = objectConverterHelper.toSurveyRemote(survey.getId());
		return surveyRemote;
	}

	@Override
	@PUT
	@Path("/delete/{surveyId}")
	@PermissionAnn(permission = PermissionEnum.SURVEY_DELETE)
	public void deleteVote(@PathParam("surveyId") long surveyId) {
		surveyService.deleteElement(surveyId);
	}
}