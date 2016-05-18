package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.helper.EntityOrder;
import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.impl.SurveyOption;
import com.prodyna.pac.vothing.model.impl.User;
import com.prodyna.pac.vothing.model.remote.ObjectConverterHelper;
import com.prodyna.pac.vothing.model.remote.SurveyRemote;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.security.PermissionAnn;
import com.prodyna.pac.vothing.service.SurveyOptionService;
import com.prodyna.pac.vothing.service.SurveyService;

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
@VothingMonitoring
public class SurveyRemoteServiceHelper {

    @Inject
    private SurveyService surveyService;

    @Inject
    private SurveyOptionService surveyOptionService;

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

        // add permissions for each entity
        for (Survey survey : surveys) {
            SurveyRemote surveyRemote = getRemoteSurveyFromSurvey(survey);
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
    public SurveyRemote updateSurvey(Survey survey) {
        SurveyRemote surveyRemote = null;

        // get existing element from db
        Survey surveyDB = surveyService.getElement(survey.getId());

        // copy attributes
        // TODO check permissions
        surveyDB.setName(survey.getName());
        surveyDB.setModifiedDate(new Date());
        //surveyDB.setUser(vothing.getUser());
        surveyDB.setSurveyOptions(survey.getSurveyOptions());

        for(SurveyOption option : surveyDB.getSurveyOptions()) {
            option.setSurvey(surveyDB);
            option.setName(option.getName());
            if(option.getId() == 0) {
                option = surveyOptionService.addElement(option);
            } else {
                option = surveyOptionService.updateElement(option);
            }
        }

        // persist
        Survey surveyDB2 = surveyService.updateElement(survey);
        surveyRemote = getRemoteSurveyFromSurvey(surveyDB2);

        return surveyRemote;
    }

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
        User userContext = vothing.getUser();
        SurveyRemote surveyRemote = ObjectConverterHelper.toSurveyRemote(survey);
        List<String> permissions = vothing.getSecurityService().getUserSurveyPermissions(userContext, survey);
        surveyRemote.setUsersPermissions(permissions);
        return surveyRemote;
    }

    @GET
    @Path("/delete/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_DELETE)
    public void deleteVote(@PathParam("surveyId") long surveyId) {
        surveyService.deleteElement(surveyId);
    }
}
