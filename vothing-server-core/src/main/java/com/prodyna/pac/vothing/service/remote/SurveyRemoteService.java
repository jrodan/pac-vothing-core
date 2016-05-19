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
public interface SurveyRemoteService {

    SurveyRemote createSurvey(Survey survey);

    List<SurveyRemote> getSurveys(@PathParam("order") String order);

    List<SurveyRemote> getSurveys();

    SurveyRemote updateSurvey(Survey survey);

    SurveyRemote getSurvey(@PathParam("surveyId") long surveyId);

    void deleteVote(@PathParam("surveyId") long surveyId);
}
