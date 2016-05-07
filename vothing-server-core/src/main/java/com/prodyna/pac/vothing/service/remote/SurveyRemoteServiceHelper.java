package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Survey;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.security.PermissionAnn;
import com.prodyna.pac.vothing.security.PermissionEnum;
import com.prodyna.pac.vothing.service.SurveyService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
@Path("/restricted/survey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public class SurveyRemoteServiceHelper {

    @Inject
    private SurveyService surveyService;

    @Inject
    private Vothing vothing;

    @POST
    @Path("/add")
    @PermissionAnn(permission = PermissionEnum.SURVEY_ADD)
    public Survey createSurvey(Survey survey) {
        return surveyService.addElement(survey);
    }

    @GET
    @Path("/list")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    public List<Survey> getSurveys() {
        List<Survey> surveysReturn = new ArrayList<Survey>();

        List<Survey> surveys = surveyService.getElements();
        User userContext = vothing.getUser();

        // add permissions for each entity
        for (Survey survey : surveys) {
            List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, survey);
            survey.setUsersPermissions(permissions);
            surveysReturn.add(survey);
        }

        return surveysReturn;
    }

    @POST
    @Path("/update")
    @PermissionAnn(permission = PermissionEnum.SURVEY_UPDATE)
    public Survey updateSurvey(Survey survey) {
        return surveyService.updateElement(survey);
    }

    @GET
    @Path("/get/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    public Survey getSurvey(@PathParam("surveyId") long surveyId) {
        Survey survey = null;
        User userContext = vothing.getUser();

        Survey surveyDB = surveyService.getElement(surveyId);

        if (surveyDB != null) {
            List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, surveyDB);
            surveyDB.setUsersPermissions(permissions);
            survey = surveyDB;
        }

        return survey;
    }

    @GET
    @Path("/delete/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_DELETE)
    public void deleteVote(@PathParam("surveyId") long surveyId) {
        surveyService.deleteElement(surveyId);
    }
}
