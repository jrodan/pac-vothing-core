package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.helper.EntityOrder;
import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.model.remote.ObjectConverterHelper;
import com.prodyna.pac.vothing.model.remote.SurveyRemote;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.security.PermissionAnn;
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
    public SurveyRemote createSurvey(SurveyRemote survey) {
        return surveyService.addElement(survey);
    }

    @GET
    @Path("/list")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    public List<SurveyRemote> getSurveys(@PathParam("order") String order) {
        List<SurveyRemote> surveysReturn = new ArrayList<SurveyRemote>();

        List<Survey> surveys = surveyService.getElements(new EntityOrder(order));
        User userContext = vothing.getUser();

        // add permissions for each entity
        for (Survey survey : surveys) {
            List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, survey);
            SurveyRemote surveyRemote = ObjectConverterHelper.toSurveyRemote(survey);
            surveyRemote.setUsersPermissions(permissions);
            surveysReturn.add(surveyRemote);
        }

        return surveysReturn;
    }

    @GET
    @Path("/list")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    public List<SurveyRemote> getSurveys() {
        return this.getSurveys(null);
    }

    @POST
    @Path("/update")
    @PermissionAnn(permission = PermissionEnum.SURVEY_UPDATE)
    public SurveyRemote updateSurvey(SurveyRemote survey) {
        return surveyService.updateElement(survey);
    }

    @GET
    @Path("/get/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    public SurveyRemote getSurvey(@PathParam("surveyId") long surveyId) {
        SurveyRemote surveyRemote = null;
        User userContext = vothing.getUser();

        Survey surveyDB = surveyService.getElement(surveyId);

        if (surveyDB != null) {
            List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, surveyDB);
            surveyRemote = ObjectConverterHelper.toSurveyRemote(surveyDB);
            surveyRemote.setUsersPermissions(permissions);
        }

        return surveyRemote;
    }

    @GET
    @Path("/delete/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_DELETE)
    public void deleteVote(@PathParam("surveyId") long surveyId) {
        surveyService.deleteElement(surveyId);
    }
}
