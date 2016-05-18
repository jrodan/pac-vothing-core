package com.prodyna.pac.vothing.service.remote;

import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.impl.Survey;
import com.prodyna.pac.vothing.model.remote.SurveyRemote;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.security.PermissionAnn;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Created by jrodan on 18/05/16.
 */
@Provider
@Path("/restricted/survey")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public interface SurveyRemoteService {

    @POST
    @Path("/add")
    @PermissionAnn(permission = PermissionEnum.SURVEY_ADD)
    SurveyRemote createSurvey(SurveyRemote survey);

    @GET
    @Path("/list")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    List<SurveyRemote> getSurveys(@PathParam("order") String order);

    @GET
    @Path("/list")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    List<SurveyRemote> getSurveys();

    @POST
    @Path("/update")
    @PermissionAnn(permission = PermissionEnum.SURVEY_UPDATE)
    SurveyRemote updateSurvey(Survey survey);

    @GET
    @Path("/get/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_LIST)
    SurveyRemote getSurvey(@PathParam("surveyId") long surveyId);

    @GET
    @Path("/delete/{surveyId}")
    @PermissionAnn(permission = PermissionEnum.SURVEY_DELETE)
    void deleteVote(@PathParam("surveyId") long surveyId);
}
